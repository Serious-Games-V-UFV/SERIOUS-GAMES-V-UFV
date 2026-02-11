#include <SoftwareSerial.h>
#include <HX711.h>
#include <Adafruit_NeoPixel.h>
#include <Math.h>
// * Configuracion pines sensores | Component's pin config
    const int led= 5;
    const int buzzer = 6;
    const int infraRed = 7;
    const int weightOut = 2;
    const int weightTrigger = 3;
    const int light = A2;
    

// * Variables | variables
  static float bottleCapacity = 1.5;
  static float currentCapacity = 1.5;
  static unsigned long clock = 0L;
  static unsigned long lastActivity = millis();
  static unsigned long lastDrink = 0L;
  static float currentWeight = 0;
  static float prevWeight = 1.5;
  static float totalDrunk = 0;
  


// * Configuracion de los pines bluetooth
  const int pinRX = 8; // * Pin recepcion lilypad (Conecta al TX del modulo) | Reception pin lilypad (TX HC05)
  const int pinTx = 9; // * Pin envio lilypad (Conecta al RX del modulo) | Transmission pin lilypad (RX HC05)

SoftwareSerial bt (pinRX, pinTx);
HX711 scale;
Adafruit_NeoPixel pixel(1, led, NEO_GRB + NEO_KHZ800);

// * Metodos | Methods

  // * Devuelve el peso medido en la bascula | Returns weight measured in the scale
  float findWeight(){
    float weight = scale.get_units(1);
    Serial.print(weight);
    return weight;
  }

  // * Devuelve la cantidad de agua bebida o rellenada | Returns the water consumed or refilled
  float waterQuantity(float prevWeight, float weight){
    float total = 0;
    total = prevWeight - weight; // * El agua pesa 1g x cada ml (1Kg x cada litro) | Water weights 1g per ml (1kg per liter)
    return total;
  }

  // * Controla el led en funcion de la hora de la ultima actividad | Controls the led in order of the last activity
  void ledControl (long lastDrink){
    long hours = lastDrink/3600000L;
    if(hours<=1){
        pixel.setPixelColor(0, pixel.Color(0, 0, 0));
        pixel.show();
      }else if((hours>1) && (hours<4)){
        pixel.setPixelColor(0, pixel.Color(255, 255, 0));
        pixel.show();
      }else if((hours>=4) && (hours<6)){
        pixel.setPixelColor(0, pixel.Color(255, 0, 0));
        pixel.show();
      }
      else{
        pixel.setPixelColor(0, pixel.Color(255, 0, 0));
        pixel.show();
        delay(500);
        pixel.setPixelColor(0, pixel.Color(255, 0, 0));
        pixel.show();
        delay(500);
        pixel.setPixelColor(0, pixel.Color(255, 0, 0));
        pixel.show();
      }
  }
  
void setup() {
  pinMode(led,OUTPUT);
  pinMode(buzzer,OUTPUT);
  pinMode(infraRed,INPUT);
  pinMode(light,INPUT);
  
  // * Inicializacion de componentes seriales | Serial components initialize
  scale.begin(weightOut,weightTrigger);
  bt.begin(9600);
  Serial.begin(9600);
  pixel.begin();
  
    // * Configuracion scale | Scale config
        scale.set_scale();
        scale.tare();
        // TODO Borrar esto cuando consigamos tarar bien la bascula | Delete this when we tare the scale 
          Serial.println("Pon peso conocido y lee Serial Monitor");
}

void loop() {
  clock = millis();
  lastDrink = (clock - lastActivity);
  //TODO Borrar esto cuando consigamos tarar bien la bascula | Delete this when we tare the scale 
    if (scale.is_ready()) {
          float reading = scale.get_units(10);  
          Serial.print("Lectura raw: ");
          Serial.println(reading);
        }
  
  // * Control de los led por tiempo sin beber | Led control with time of last drink
    ledControl(lastDrink);


  // * Control de la vibración | Vibration control


  
  // * Agua bebida y modificacion capacidad actual de la botella | Drunk water and modify current bottle's capacity
    currentWeight = findWeight();
    float drunk = waterQuantity(prevWeight,currentWeight);
    if(drunk > 0.01){
      currentCapacity -= drunk;
        totalDrunk += drunk;
      lastActivity = clock;
    }else if(drunk <-0.1){
      currentCapacity += fabs(drunk); // * Si se rellena la capacidad aumenta (fabs = valor absoluto) | If there is a refill, capacity increases (fabs = absolute value)
    }
    prevWeight = currentWeight;
    





  // * Deteccion del sensor infrarrojo | IR sensor detection


  

  // * Modo noche | Night mode
  
  
  

}
