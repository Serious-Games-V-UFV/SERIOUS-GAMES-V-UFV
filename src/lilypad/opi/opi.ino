#include <HX711.h>

/*** PINES ***/
const byte IR_PIN  = 7;
const byte HX_DT   = A2;
const byte HX_SCK  = A3;

/*** CONSTANTES ***/
#define FILTER_SAMPLES   5
#define DRINK_THRESHOLD  2.0
#define SEND_INTERVAL    5000

/*** OBJETOS ***/
HX711 scale;

/*** VARIABLES ***/
bool  bottlePlaced = false;
bool  scaleReady   = false;
float capacity     = 750.0;
float totalDrunk   = 0.0;

float prevWeight   = 0.0;
float weightBuf[FILTER_SAMPLES];
byte  bufIndex     = 0;

unsigned long lastDrinkTime = 0;
unsigned long lastSendTime  = 0;

/*** PESO FILTRADO ***/
float getWeight() {
  if (!scaleReady || !scale.is_ready()) return prevWeight;

  weightBuf[bufIndex] = scale.get_units(3);
  bufIndex = (bufIndex + 1) % FILTER_SAMPLES;

  float sum = 0;
  for (byte i = 0; i < FILTER_SAMPLES; i++) sum += weightBuf[i];
  return sum / FILTER_SAMPLES;
}

/*** LOGICA SIMPLE (SIN ESTADOS) ***/
void updateDrink() {
  bottlePlaced = digitalRead(IR_PIN) == LOW;
  float currWeight = getWeight();
  float diff = prevWeight - currWeight;

  // Solo actúa si botella detectada (estable)
  if (bottlePlaced && abs(diff) > DRINK_THRESHOLD) {
    if (diff > 0) {  // Bebida
      totalDrunk += diff;
      capacity -= diff;
      if (capacity < 0) capacity = 0;
      lastDrinkTime = millis();
    } else {         // Añadida
      capacity -= diff;
    }
  }

  prevWeight = currWeight;
}

/*** BLUETOOTH ***/
void sendData(unsigned long timeSinceDrink) {
  unsigned long minutes = timeSinceDrink / 60000UL;

  char capStr[9], drunkStr[9];
  dtostrf(capacity,   6, 2, capStr);
  dtostrf(totalDrunk, 6, 2, drunkStr);

  char msg[100];
  snprintf(msg, sizeof(msg),
           "{\"capacity\":%s,\"minutesSinceDrink\":%lu,"
           "\"totalDrunk\":%s,\"bottlePlaced\":%s}",
           capStr, minutes, drunkStr,
           bottlePlaced ? "true" : "false");
  Serial.println(msg);
}

/*** HX711 ***/
void initScale() {
  scale.begin(HX_DT, HX_SCK);
  scale.set_scale(-7050);
  scale.tare();
  delay(1500);

  if (!scale.is_ready()) return;

  prevWeight = scale.get_units(10);
  scaleReady = true;
  for (byte i = 0; i < FILTER_SAMPLES; i++) weightBuf[i] = prevWeight;
}

void setup() {
  Serial.begin(9600);
  pinMode(IR_PIN, INPUT_PULLUP);
  initScale();
  lastDrinkTime = millis();
}

void loop() {
  if (scaleReady) updateDrink();

  unsigned long now = millis();
  if (now - lastSendTime >= SEND_INTERVAL) {
    sendData(now - lastDrinkTime);
    lastSendTime = now;
  }
  delay(80);
}
