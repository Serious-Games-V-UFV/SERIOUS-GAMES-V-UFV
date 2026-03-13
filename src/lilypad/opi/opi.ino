#include <HX711.h>

/*========== PINES ==========*/
const byte IR_PIN = 7;
const byte HX_DT  = A2;
const byte HX_SCK = A3;

/*========== CONSTANTES ==========*/
#define FILTER_SAMPLES 5
#define DRINK_THRESHOLD 2.0
#define SEND_INTERVAL 5000
#define SETTLE_READINGS 3

/*========== ESTADOS ==========*/
enum BottleState {
  IDLE,
  LIFTED
};

BottleState state = IDLE;

/*========== OBJETOS ==========*/
HX711 scale;

/*========== VARIABLES ==========*/
bool bottlePlaced = false;
bool scaleReady = false;

float capacity = 750.0;
float totalDrunk = 0.0;

float prevWeight = 0.0;
float currentWeight = 0.0;
float liftedWeight = 0.0;

float weightBuffer[FILTER_SAMPLES];
byte weightIndex = 0;
byte settleCount = 0;

unsigned long lastDrinkTime = 0;
unsigned long lastSendTime = 0;

/*========== PESO RAW ==========*/
float readWeight() {

  if (!scaleReady) return prevWeight;

  if (scale.is_ready())
    return scale.get_units(3);

  return prevWeight;

}

/*========== FILTRO ==========*/
float filteredWeight() {

  float w = readWeight();

  weightBuffer[weightIndex] = w;
  weightIndex++;

  if (weightIndex >= FILTER_SAMPLES)
    weightIndex = 0;

  float sum = 0.0;

  for (byte i = 0; i < FILTER_SAMPLES; i++)
    sum += weightBuffer[i];

  return sum / FILTER_SAMPLES;

}

/*========== IR ==========*/
bool detectBottle() {

  return digitalRead(IR_PIN) == LOW;

}

/*========== MAQUINA DE ESTADOS ==========*/
void stateMachine() {

  bottlePlaced = detectBottle();
  currentWeight = filteredWeight();

  switch(state) {

    /* BOTELLA QUIETA */
    case IDLE:

      if (!bottlePlaced) {
        liftedWeight = prevWeight;
        settleCount = 0;
        state = LIFTED;
      }

    break;

    /* BOTELLA LEVANTADA */
    case LIFTED:

      if (bottlePlaced) {

        /* Esperar que el filtro se estabilice */
        if (settleCount < SETTLE_READINGS) {
          settleCount++;
        }

        float diff = liftedWeight - currentWeight;

        /* USUARIO BEBE */
        if (diff > DRINK_THRESHOLD) {
          totalDrunk += diff;
          capacity -= diff;
          if (capacity < 0.0) capacity = 0.0;
          lastDrinkTime = millis();
        }

        /* USUARIO AÑADE AGUA */
        if (diff < -DRINK_THRESHOLD) {
          capacity += (-diff);
        }

        state = IDLE;

      }

    break;

  }

  prevWeight = currentWeight;

}

/*========== BLUETOOTH ==========*/
void sendBluetooth(unsigned long timeSinceDrink) {
    unsigned long minutes = timeSinceDrink / 3600000UL;

    char capStr[9];
    char drunkStr[9];
    dtostrf(capacity,   6, 2, capStr);
    dtostrf(totalDrunk, 6, 2, drunkStr);

    char msg[100];
    snprintf(msg, sizeof(msg),
        "{\"capacity\":%s,\"minutesSinceDrink\":%lu,\"totalDrunk\":%s,\"bottlePlaced\":%s}",
        capStr,
        minutes,
        drunkStr,
        bottlePlaced ? "true" : "false");

    Serial.println(msg);
}

/*========== HX711 ==========*/
void initScale() {

  scale.begin(HX_DT, HX_SCK);
  scale.set_scale(-7050);
  scale.tare();
  delay(1500);

  if (scale.is_ready()) {

    prevWeight = scale.get_units(10);
    scaleReady = true;

    for (byte i = 0; i < FILTER_SAMPLES; i++)
      weightBuffer[i] = prevWeight;

  }

}

/*========== SETUP ==========*/
void setup() {

  Serial.begin(9600);
  pinMode(IR_PIN, INPUT_PULLUP);
  initScale();
  lastDrinkTime = millis();

}

/*========== LOOP ==========*/
void loop() {

  if (scaleReady)
    stateMachine();

  unsigned long now = millis();

  if (now - lastSendTime >= SEND_INTERVAL) {
    sendBluetooth(now - lastDrinkTime);
    lastSendTime = now;
  }

  delay(80);

}