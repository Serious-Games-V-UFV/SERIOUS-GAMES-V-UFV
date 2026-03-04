//TODO Modificar la logica para que quede acorde al producto final, debuggear con el lilypad
#include <HX711.h>
#include <Adafruit_NeoPixel.h>
#include <math.h>
#include <BluetoothSerial.h>

// * Configuracion pines sensores | Component's pin config
const int nightButton = 4;
const int led = 5;
const int buzzer = 6;
const int infraRed = 7;
const int weight = A2;
const int weightSCK = A3;

// * Variables | variables
static float bottleCapacity = 1.5;
static float currentCapacity = 1.5;
static unsigned long clock = 0L;
static unsigned long lastActivity = 0L;
static unsigned long lastDrink = 0L;
static float currentWeight = 0;
static float prevWeight = 1.5;
static float totalDrunk = 0;
static unsigned long lastLED = 0;
static bool nightMode = false;
static bool bottlePlaced = false;
static bool ledBlinkState = false;
static unsigned long lastLedBlink = 0;
static bool buzzerState = false;
static unsigned long lastBuzzChange = 0;
static unsigned long lastBluetoothSend = 0;

// * Constantes | Constants
  const float DETECTION_THRESHOLD = 0.05; // * Umbral unificado para deteccion | Unified threshold for detection
// * Configuracion de los pines bluetooth
BluetoothSerial SerialBT(Serial, true);
//TODO Descomentar linea siguiente cuando conectemos bascula
  // HX711 scale;
Adafruit_NeoPixel pixel(1, led, NEO_GRB + NEO_KHZ800);

// * Metodos | Methods
//TODO Descomentar lineas cuando conectemos bascula
// * Devuelve el peso medido en la bascula | Returns weight measured in the scale
float findWeight() {
  // float weight = scale.get_units(1);
  // Serial.print(weight);
  // return weight;
  return 0.0; // Updatear con las taras
}

// * Devuelve la diferencia de peso | Returns the weight difference
float weightDifference(float prevWeight, float weight) {
  float total = 0;
  total = prevWeight - weight; // * El agua pesa 1g x cada ml (1Kg x cada litro) | Water weights 1g per ml (1kg per liter)
  return total;
}

// * Controla cuando se bebe agua, devuelve cuando se ha bebido | Controls when water is consumed, returns the moment of consumption
unsigned long drinkControl() {
  currentWeight = findWeight();
  float drunk = weightDifference(prevWeight, currentWeight);
 
  if (drunk > DETECTION_THRESHOLD) {
    // * Se ha bebido agua | Water consumed
    currentCapacity -= drunk;
    totalDrunk += drunk;
    lastActivity = clock;
  } else if (drunk < -DETECTION_THRESHOLD) {
    // * Se ha rellenado la botella | Bottle refilled
    currentCapacity += fabsf(drunk);
  }
  
  prevWeight = currentWeight;
  return lastActivity;
}

// * Controla el led en funcion de la hora de la ultima actividad | Controls the led with the last activity time
void ledControl(long lastDrink) {
  long hours = lastDrink / 3600000L;
  if (hours <= 1) {
    // * LED apagado | LED off
    pixel.setPixelColor(0, pixel.Color(0, 0, 0));
    pixel.show();
    ledBlinkState = false;
  } else if ((hours > 1) && (hours < 4)) {
    // * LED amarillo fijo | Fixed yellow LED
    pixel.setPixelColor(0, pixel.Color(255, 255, 0));
    pixel.show();
    ledBlinkState = false;
  } else if ((hours >= 4) && (hours < 6)) {
    // * LED rojo fijo | Fixed red LED
    pixel.setPixelColor(0, pixel.Color(255, 0, 0));
    pixel.show();
    ledBlinkState = false;
  } else {
    // * LED rojo parpadeante (6+ horas) | Blinking red LED (6+ hours)
    if (clock - lastLedBlink > 500) {
      ledBlinkState = !ledBlinkState;
      if (ledBlinkState) {
        pixel.setPixelColor(0, pixel.Color(255, 0, 0));
      } else {
        pixel.setPixelColor(0, pixel.Color(0, 0, 0));
      }
      pixel.show();
      lastLedBlink = clock;
    }
  }
}

// * Deteccion de la botella por sensor infrarrojo | Bottle detection with infrared sensor
bool irDetection() {
  bool bottleDetected = !digitalRead(infraRed);
  if (bottleDetected) {
    Serial.println("Botella presente");
    return true;
  } else {
    Serial.println("Botella no colocada");
    return false;
  }
}

// * Alterna el modo noche | Toggles night mode
void nightControl() {
  nightMode = !digitalRead(nightButton);
}

// * Controla el zumbador en base a la ultima actividad  | Controls the buzzer with the last activity time 
void buzzControl(long lastDrink) {
  long hours = lastDrink / 3600000L;
  
  if (hours <= 1) {
    noTone(buzzer);
    buzzerState = false;
  } else if ((hours > 1) && (hours < 4)) {
    tone(buzzer, 800, 300);
  } else if ((hours >= 4) && (hours < 6)) {
    tone(buzzer, 1500, 500);
  } else {
    unsigned long timeSinceLastBuzz = clock - lastBuzzChange;
    if (!buzzerState && timeSinceLastBuzz > 400) {
      tone(buzzer, 2000, 300);
      buzzerState = true;
      lastBuzzChange = clock;
    } else if (buzzerState && timeSinceLastBuzz > 300) {
      buzzerState = false;
      lastBuzzChange = clock;
    }
  }
}

// * Envia los datos como JSON | Sends the data as JSON
void bluetoothSend(long lastDrinkTime) {
  long hours = lastDrinkTime / 3600000L;
  
  // * Formato JSON estructurado | Structured JSON format
  String message = "{\"capacity\":" + String(currentCapacity) + 
                   ",\"hoursSinceDrink\":" + String(hours) + 
                   ",\"totalDrunk\":" + String(totalDrunk) + 
                   ",\"bottlePlaced\":" + String(bottlePlaced ? "true" : "false") + "}";
  
  SerialBT.writeSerial(message);
  Serial.println(message);
}

void setup() {
  pinMode(buzzer, OUTPUT);
  pinMode(infraRed, INPUT);
  pinMode(nightButton, INPUT_PULLUP);
  
  // * Inicializacion de componentes seriales | Serial components initialize
  //TODO Descomentar linea siguiente cuando conectemos bascula | Delete following comment when the scale is connected
  // scale.begin(weight, weightSCK);
  Serial.begin(115200);
  SerialBT.begin("Hidratacion");
  pixel.begin();
  
  // * Configuracion scale | Scale config
  // scale.set_scale();
  // scale.tare();
  
  // * Inicializacion correcta del tiempo | Correct time initialization
  lastActivity = millis();
  
  Serial.println("Sistema de hidratacion iniciado");
}

void loop() {
  clock = millis();
  
  Serial.print("Tiempo del programa: ");
  Serial.println(clock);
  
  nightControl();
  
  //TODO Borrar esto cuando consigamos tarar bien la bascula | Delete this when we tare the scale
  // if (scale.is_ready()) {
  //   float reading = scale.get_units(10);
  //   Serial.print("Lectura raw: ");
  //   Serial.println(reading);
  // }
  
  // * Control de los led y vibracion por tiempo sin beber (cada 10 segundos) | Led and buzzer control with time of last drink (every 10 seconds)
  if (clock - lastLED > 10000UL) {
    ledControl(lastDrink);
    lastLED = clock;
    
    // * Control del zumbador | Buzzer control
    if (!nightMode) {
      buzzControl(lastDrink);
    } else {
      noTone(buzzer);
    }
  }
  
  bottlePlaced = irDetection();
  
  if (bottlePlaced) {
    lastActivity = drinkControl();
  }
  
  lastDrink = (clock - lastActivity);
  
  // * Envio Bluetooth  (cada 2 segundos) | Bluetooth send (every 2 seconds)
  if (clock - lastBluetoothSend > 2000UL) {
    bluetoothSend(lastDrink);
    lastBluetoothSend = clock;
  }
  
}
