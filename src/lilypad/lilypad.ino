// Sistema de hidratacion - LilyPad Arduino
#include <HX711.h>
#include <Adafruit_NeoPixel.h>
#include <SoftwareSerial.h>
#include <math.h>

// * Configuracion pines | Pin config
const int nightButton = 4;
const int led         = 5;
const int buzzer      = 6;
const int infraRed    = 7;
const int weightDAT   = A2;
const int weightSCK   = A3;

// * Bluetooth por SoftwareSerial (HC-05/HC-06)
// * RX del modulo → pin 2 Arduino | TX del modulo → pin 3 Arduino
SoftwareSerial SerialBT(2, 3); // RX, TX

// * Bascula
HX711 scale;

// * LED NeoPixel
Adafruit_NeoPixel pixel(1, led, NEO_GRB + NEO_KHZ800);

// * Variables
static float bottleCapacity  = 1.5;
static float currentCapacity = 1.5;
static unsigned long clock_ms    = 0UL;
static unsigned long lastActivity = 0UL;
static unsigned long lastDrink    = 0UL;
static float currentWeight = 0.0;
static float prevWeight    = 1.5;
static float totalDrunk    = 0.0;
static unsigned long lastLED          = 0UL;
static unsigned long lastBluetoothSend = 0UL;
static unsigned long lastLedBlink     = 0UL;
static unsigned long lastBuzzChange   = 0UL;
static bool nightMode      = false;
static bool bottlePlaced   = false;
static bool ledBlinkState  = false;
static bool buzzerState    = false;

// * Constantes
const float DETECTION_THRESHOLD = 0.05; // kg (equivale a 50ml)
// IMPORTANTE: ajusta este valor tras calibrar la bascula
const float SCALE_FACTOR = 1.0; 

// ─────────────────────────────────────────────
// * Devuelve el peso actual en kg
float findWeight() {
  if (scale.is_ready()) {
    return scale.get_units(3); // promedio de 3 lecturas
  }
  return prevWeight; // si no esta lista, devuelve el ultimo peso conocido
}

// * Diferencia de peso entre lectura anterior y actual
float weightDifference(float prev, float current) {
  return prev - current; // positivo = se bebio, negativo = se relleno
}

// * Controla cuando se bebe agua
unsigned long drinkControl() {
  currentWeight = findWeight();
  float drunk = weightDifference(prevWeight, currentWeight);

  if (drunk > DETECTION_THRESHOLD) {
    // Se ha bebido agua
    currentCapacity -= drunk;
    totalDrunk      += drunk;
    lastActivity     = clock_ms;
  } else if (drunk < -DETECTION_THRESHOLD) {
    // Se ha rellenado la botella
    currentCapacity += fabsf(drunk);
  }

  prevWeight = currentWeight;
  return lastActivity;
}

// * Controla el LED segun tiempo sin beber
void ledControl(unsigned long timeSinceLastDrink) {
  unsigned long hours = timeSinceLastDrink / 3600000UL;

  if (hours < 1) {
    // LED apagado
    pixel.setPixelColor(0, pixel.Color(0, 0, 0));
    pixel.show();
    ledBlinkState = false;

  } else if (hours < 4) {
    // LED amarillo fijo
    pixel.setPixelColor(0, pixel.Color(255, 255, 0));
    pixel.show();
    ledBlinkState = false;

  } else if (hours < 6) {
    // LED rojo fijo
    pixel.setPixelColor(0, pixel.Color(255, 0, 0));
    pixel.show();
    ledBlinkState = false;

  } else {
    // LED rojo parpadeante (6+ horas)
    if (clock_ms - lastLedBlink > 500UL) {
      ledBlinkState = !ledBlinkState;
      pixel.setPixelColor(0, ledBlinkState ? pixel.Color(255, 0, 0) : pixel.Color(0, 0, 0));
      pixel.show();
      lastLedBlink = clock_ms;
    }
  }
}

// * Deteccion de botella por infrarrojo
bool irDetection() {
  bool detected = !digitalRead(infraRed); // LOW = botella presente (sensor activo bajo)
  Serial.println(detected ? "Botella presente" : "Botella no colocada");
  return detected;
}

// * Lee el boton de modo noche
void nightControl() {
  nightMode = (digitalRead(nightButton) == LOW); // INPUT_PULLUP: LOW = pulsado
}

// * Controla el zumbador segun tiempo sin beber
void buzzControl(unsigned long timeSinceLastDrink) {
  unsigned long hours = timeSinceLastDrink / 3600000UL;

  if (hours < 1) {
    noTone(buzzer);
    buzzerState = false;

  } else if (hours < 4) {
    tone(buzzer, 800, 300);

  } else if (hours < 6) {
    tone(buzzer, 1500, 500);

  } else {
    // Pitidos rapidos continuos (6+ horas)
    unsigned long elapsed = clock_ms - lastBuzzChange;
    if (!buzzerState && elapsed > 700UL) {
      tone(buzzer, 2000, 300);
      buzzerState = true;
      lastBuzzChange = clock_ms;
    } else if (buzzerState && elapsed > 300UL) {
      buzzerState = false;
      lastBuzzChange = clock_ms;
    }
  }
}

// * Envia datos como JSON por Bluetooth
void bluetoothSend(unsigned long timeSinceLastDrink) {
  unsigned long hours = timeSinceLastDrink / 3600000UL;

  String msg = "{\"capacity\":"       + String(currentCapacity, 2) +
               ",\"hoursSinceDrink\":" + String(hours) +
               ",\"totalDrunk\":"      + String(totalDrunk, 2) +
               ",\"bottlePlaced\":"    + String(bottlePlaced ? "true" : "false") + "}";

  SerialBT.println(msg);
  Serial.println(msg);
}

// ─────────────────────────────────────────────
void setup() {
  pinMode(buzzer,      OUTPUT);
  pinMode(infraRed,    INPUT);
  pinMode(nightButton, INPUT_PULLUP);

  Serial.begin(9600);       // Monitor serie
  SerialBT.begin(9600);     // HC-05/HC-06 (debe estar configurado a 9600 por defecto)

  pixel.begin();
  pixel.setBrightness(80);  // 0-255, ajusta segun necesites
  pixel.setPixelColor(0, pixel.Color(0, 0, 0));
  pixel.show();

  // Inicializar bascula
  scale.begin(weightDAT, weightSCK);
  scale.set_scale(SCALE_FACTOR); // <-- cambia SCALE_FACTOR tras calibrar
  scale.tare();                  // pone a cero con la botella vacia

  lastActivity = millis();

  Serial.println("Sistema de hidratacion iniciado");
}

void loop() {
  clock_ms = millis();

  nightControl();

  // Control LED y zumbador cada 10 segundos
  if (clock_ms - lastLED > 10000UL) {
    ledControl(lastDrink);

    if (!nightMode) {
      buzzControl(lastDrink);
    } else {
      noTone(buzzer);
    }

    lastLED = clock_ms;
  }

  bottlePlaced = irDetection();

  if (bottlePlaced) {
    lastActivity = drinkControl();
  }

  lastDrink = clock_ms - lastActivity;

  // Envio Bluetooth cada 2 segundos
  if (clock_ms - lastBluetoothSend > 2000UL) {
    bluetoothSend(lastDrink);
    lastBluetoothSend = clock_ms;
  }
}
