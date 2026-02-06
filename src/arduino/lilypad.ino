#include <SoftwareSerial.h>
#include <HX711.h>
// Configuracion pines sensores
const int led = 6;
const int vibrador = 7;
const int luz = 8;
const int infraRed = 9;
const int pesoOut = 4;
const int pesoDisparador = 5;


// Configuracion de los pines bluetooth
const int pinRX = 10; // Pin recepcion lilypad (Conecta al TX del modulo)
const int pinTx = 11; // Pin envio lilypad (Conecta al RX del modulo)

SoftwareSerial bt (pinRX, pinTx);
HX711 bascula;
void setup() {
  

  // * Los de ASIR nos tienen que decir donde colocar todos los sensores, asi que estos pines deben cambiarse 
  pinMode(led,OUTPUT);
  pinMode(vibrador,OUTPUT);
  pinMode(infraRed,INPUT);
  pinMode(luz,INPUT);

  // * Inicializacion de componentes seriales
  bascula.begin(pesoOut,pesoDisparador);
  bt.begin(9600);
  Serial.begin(9600);

  // * Configuracion bascula
    // TODO Borrar esto cuando consigamos tarar bien la bascula
      bascula.set_scale();  // Sin factor 
      bascula.tare();       // Quita peso (tara)
      Serial.println("Pon peso conocido y lee Serial Monitor");
      
}

void loop() {
  //TODO Borrar esto cuando consigamos tarar bien la bascula
    if (bascula.is_ready()) {
          float reading = bascula.get_units(10);  // Promedio 10 lecturas
          Serial.print("Lectura raw: ");
          Serial.println(reading);
        }

  float valorPeso = leerPeso(bascula);
  

}

float leerPeso(HX711 bascula){
  
}