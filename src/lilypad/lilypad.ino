#include <SoftwareSerial.h>
#include <HX711.h>
  // * Configuracion pines sensores | Component's pin config
    const int led = 5;
    const int buzzer = 6;
    const int light = A2;
    const int infraRed = 7;
    const int weightOut = 2;
    const int weightTrigger = 3;
    static int drinkingTimer = 0;
    
// Configuracion de los pines bluetooth
const int pinRX = 8; // * Pin recepcion lilypad (Conecta al TX del modulo) | Reception pin lilypad (TX HC05)
const int pinTx = 9; // * Pin envio lilypad (Conecta al RX del modulo) | Transmission pin lilypad (RX HC05)

SoftwareSerial bt (pinRX, pinTx);
HX711 scale;

float findWeight(HX711 scale){
  float weight = scale.get_units(1);
  Serial.print(weight);
  return weight;
}

float waterQuantity(float prevKg, float weight){
  float total = 0F;
  total = prevWeitgh - weight; // * El agua pesa 1g x cada ml (1Kg x cada litro) | Water weights 1g per ml (1kg per liter)

  if(total < 0){
    return 0;
  }else{
    return total;
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
// TODO Borrar esto cuando consigamos tarar bien la scale | Delete this when we tare the scale 
  // * Configuracion scale
  // * Scale config
      scale.set_scale();
      scale.tare();
      Serial.println("Pon peso conocido y lee Serial Monitor");
      
}

void loop() {
  //TODO Borrar esto cuando consigamos tarar bien la scale | Delete this when we tare the scale 
    if (scale.is_ready()) {
          float reading = scale.get_units(10);  
          Serial.print("Lectura raw: ");
          Serial.println(reading);
        }
  
  float currentWeight = findWeight(scale);
  float prevWeight = currentWeight;
  currentWeight = findWeight(scale);
  if(currentWeight < 0){
    do{
      currentWeight = findWeight(scale);
    }while(currentWeight <0);
      float totalWater = waterQuantity(prevWeight,currentWeight);
  }
  drinkingTimer++;
  delay(2000);
}

