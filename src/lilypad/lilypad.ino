#include <HX711.h>
#include <Adafruit_NeoPixel.h>
#include <math.h>

// * Configuracion pines
const int nightButton = 4;
const int LED_PIN     = 5;
const int buzzer      = 6;
const int infraRed    = 7;
const int WEIGHT_DT   = A2;
const int WEIGHT_SCK  = A3;

// * Variables globales
static float bottleCapacity  = 1.5;
static float currentCapacity = 1.5;
static float currentWeight   = 0;
static float prevWeight      = 1.5;
static float totalDrunk      = 0;

static unsigned long clock_ms       = 0UL;
static unsigned long lastActivity   = 0UL;
static unsigned long lastDrink      = 0UL;
static unsigned long lastLED        = 0UL;
static unsigned long lastLedBlink   = 0UL;
static unsigned long lastBuzzChange = 0UL;
static unsigned long lastBTSend     = 0UL;

static bool nightMode      = false;
static bool bottlePlaced   = false;
static bool ledBlinkState  = false;
static bool buzzerState    = false;

// * Constantes
const float DETECTION_THRESHOLD = 0.05;

// * Libreria HX711 (bogde) compatible con AVR
//TODO Descomentar cuando se conecte la bascula
// HX711 scale;

// * NeoPixel compatible con LilyPad AVR
Adafruit_NeoPixel pixel(1, LED_PIN, NEO_GRB + NEO_KHZ800);


// ---------------------------------------------------------------------------
// * Peso: devuelve kg leidos por la bascula
// ---------------------------------------------------------------------------
float findWeight() {
  // TODO Descomentar y calibrar con el factor correcto
  // if (scale.is_ready()) return scale.get_units(3);
  return 0.0;
}

// * Diferencia de peso (positivo = se ha bebido, negativo = se ha rellenado)
float weightDifference(float prev, float curr) {
  return prev - curr;
}

// * Detecta si se ha bebido o rellenado y actualiza estado
unsigned long drinkControl() {
  currentWeight    = findWeight();
  float diff       = weightDifference(prevWeight, currentWeight);

  if (diff > DETECTION_THRESHOLD) {
    currentCapacity -= diff;
    totalDrunk      += diff;
    lastActivity     = clock_ms;
  } else if (diff < -DETECTION_THRESHOLD) {
    currentCapacity += fabsf(diff);
  }

  prevWeight = currentWeight;
  return lastActivity;
}

// * Control del LED segun tiempo sin beber
void ledControl(unsigned long timeSinceLastDrink) {
  long hours = timeSinceLastDrink / 3600000L;

  if (hours <= 1) {
    pixel.setPixelColor(0, pixel.Color(0, 0, 0));
    pixel.show();
    ledBlinkState = false;

  } else if (hours < 4) {
    pixel.setPixelColor(0, pixel.Color(255, 255, 0)); // amarillo
    pixel.show();
    ledBlinkState = false;

  } else if (hours < 6) {
    pixel.setPixelColor(0, pixel.Color(255, 0, 0));   // rojo fijo
    pixel.show();
    ledBlinkState = false;

  } else {
    // rojo parpadeante cada 500 ms
    if (clock_ms - lastLedBlink > 500UL) {
      ledBlinkState = !ledBlinkState;
      pixel.setPixelColor(0, ledBlinkState
        ? pixel.Color(255, 0, 0)
        : pixel.Color(0, 0, 0));
      pixel.show();
      lastLedBlink = clock_ms;
    }
  }
}

// * Deteccion de botella por IR
bool irDetection() {
  bool detected = !digitalRead(infraRed);
  Serial.println(detected ? "Botella presente" : "Botella no colocada");
  return detected;
}

// * Modo noche (boton con INPUT_PULLUP: LOW = activo)
void nightControl() {
  nightMode = (digitalRead(nightButton) == LOW);
}

// * Control del zumbador segun tiempo sin beber
void buzzControl(unsigned long timeSinceLastDrink) {
  long hours = timeSinceLastDrink / 3600000L;

  if (hours <= 1) {
    noTone(buzzer);
    buzzerState = false;

  } else if (hours < 4) {
    tone(buzzer, 800, 300);

  } else if (hours < 6) {
    tone(buzzer, 1500, 500);

  } else {
    // pitido doble rapido
    unsigned long elapsed = clock_ms - lastBuzzChange;
    if (!buzzerState && elapsed > 400UL) {
      tone(buzzer, 2000, 300);
      buzzerState    = true;
      lastBuzzChange = clock_ms;
    } else if (buzzerState && elapsed > 300UL) {
      buzzerState    = false;
      lastBuzzChange = clock_ms;
    }
  }
}

// * Envia JSON por Bluetooth (Serial hardware = HC-05)
// Formato: {"capacity":1.50,"hoursSinceDrink":0,"totalDrunk":0.00,"bottlePlaced":true}
void bluetoothSend(unsigned long timeSinceLastDrink) {
  long hours = timeSinceLastDrink / 3600000L;

  String message = "{\"capacity\":"      + String(currentCapacity, 2) +
                   ",\"hoursSinceDrink\":" + String(hours) +
                   ",\"totalDrunk\":"     + String(totalDrunk, 2) +
                   ",\"bottlePlaced\":"   + String(bottlePlaced ? "true" : "false") +
                   "}";

  Serial.println(message);
}



// ---------------------------------------------------------------------------
void setup() {
  pinMode(buzzer,      OUTPUT);
  pinMode(infraRed,    INPUT);
  pinMode(nightButton, INPUT_PULLUP);

  // Serial hardware = pin 0/1 = HC-05 Bluetooth
  // 9600 baud es el default del HC-05; si lo cambiaste con AT usa ese valor
  Serial.begin(9600);

  // TODO Descomentar cuando se conecte la bascula
  // scale.begin(WEIGHT_DT, WEIGHT_SCK);
  // scale.set_scale(/* factor_calibracion */);
  // scale.tare();

  pixel.begin();
  pixel.setPixelColor(0, pixel.Color(0, 0, 0));
  pixel.show();

  lastActivity = millis();
  Serial.println(F("Sistema de hidratacion iniciado"));
}


void loop() {
  clock_ms = millis();

  nightControl();
  bottlePlaced = irDetection();

  if (bottlePlaced) {
    lastActivity = drinkControl();
  }

  lastDrink = clock_ms - lastActivity;

  // LED y buzzer cada 10 segundos
  if (clock_ms - lastLED > 10000UL) {
    ledControl(lastDrink);
    lastLED = clock_ms;

    if (!nightMode) {
      buzzControl(lastDrink);
    } else {
      noTone(buzzer);
    }
  }

  // Envio Bluetooth cada 2 segundos
  if (clock_ms - lastBTSend > 2000UL) {
    bluetoothSend(lastDrink);
    lastBTSend = clock_ms;
  }
}
