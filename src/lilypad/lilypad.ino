#include <SoftwareSerial.h>
#include <HX711.h>
  // * Configuracion pines sensores | Component's pin config
    const int led= 5;
    const int buzzer = 6;
    const int infraRed = 7;
    const int weightOut = 2;
    const int weightTrigger = 3;
    const int light = A2;

// * Variables estáticas | Static variables
  static int drinkingTimer = 0;
  static float bottleCapacity = 1.5;
  static float currentCapacity = 1.5;

// * Configuracion de los pines bluetooth
  const int pinRX = 8; // * Pin recepcion lilypad (Conecta al TX del modulo) | Reception pin lilypad (TX HC05)
  const int pinTx = 9; // * Pin envio lilypad (Conecta al RX del modulo) | Transmission pin lilypad (RX HC05)

SoftwareSerial bt (pinRX, pinTx);
HX711 scale;
// * Metodos | Methods
  float findWeight(HX711 scale){
    float weight = scale.get_units(1);
    Serial.print(weight);
    return weight;
  }

  float waterQuantity(float prevWeight, float weight){
    float total = 0;
    total = prevWeight - weight; // * El agua pesa 1g x cada ml (1Kg x cada litro) | Water weights 1g per ml (1kg per liter)
      return total;
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
  // TODO Borrar esto cuando consigamos tarar bien la bascula | Delete this when we tare the scale 
    // * Configuracion scale | Scale config
        scale.set_scale();
        scale.tare();
        Serial.println("Pon peso conocido y lee Serial Monitor");
}

void loop() {
  //TODO Borrar esto cuando consigamos tarar bien la bascula | Delete this when we tare the scale 
    if (scale.is_ready()) {
          float reading = scale.get_units(10);  
          Serial.print("Lectura raw: ");
          Serial.println(reading);
        }

    // ledControl(drinkingTimer);
  
  float currentWeight = findWeight(scale);
  float prevWeight = currentWeight;
  currentWeight = findWeight(scale);
  if(currentWeight < 0){
    do{
      currentWeight = findWeight(scale);
    }while(currentWeight <0);
      float drinkenWater = waterQuantity(prevWeight,currentWeight); // * Se calcula el agua bebida | Drinken Water is calculated
      if(drinkenWater < 0){ // * Si <0(Se ha rellenado la botella) actualizar capacidad | If <0 (bottle was refilled) update Capacity
        int currentCapacity = currentCapacity + (drinkenWater*-1); 
      }
  }
  drinkingTimer++;
  drinkingTimer++;
  delay(2000);
}

