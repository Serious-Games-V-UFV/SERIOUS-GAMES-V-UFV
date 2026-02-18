//TODO Modificar la logica para que quede acorde al producto final, debuggear con el lilypad
#include <SoftwareSerial.h>
#include <HX711.h>
#include <Adafruit_NeoPixel.h>
#include <math.h>
#include <BluetoothSerial.h>
// * Configuracion pines sensores | Component's pin config
    const int nightButton = 4;
    const int led= 5;
    const int buzzer = 6;
    const int infraRed = 7;
    const int weight = A2;
    const int weightSCK = A3;
    
    

// * Variables | variables
  static float bottleCapacity = 1.5;
  static float currentCapacity = 1.5;
  static unsigned long clock = 0L;
  static unsigned long lastActivity = millis();
  static unsigned long lastDrink = 0L;
  static float currentWeight = 0;
  static float prevWeight = 1.5;
  static float totalDrunk = 0;
  static unsigned long lastLED = 0;
  static boolean nightMode = false;
  
  


// * Configuracion de los pines bluetooth
  const int pinRX = 8; // * Pin recepcion lilypad (Conecta al TX del modulo) | Reception pin lilypad (TX HC05)
  const int pinTx = 9; // * Pin envio lilypad (Conecta al RX del modulo) | Transmission pin lilypad (RX HC05)

  SoftwareSerial bt (pinRX, pinTx);
//TODO Descomentar linea 36 cuando conectemos bascula
  // HX711 scale;
  Adafruit_NeoPixel pixel(1, led, NEO_GRB + NEO_KHZ800);

// * Metodos | Methods
  //TODO Descomentar lineas cuando conectemos bascula
    // * Devuelve el peso medido en la bascula | Returns weight measured in the scale
    float findWeight(){
      // float weight = scale.get_units(1);
      // Serial.print(weight);
      // return weight;
      return 0.0; // Updatear con las taras
    }

  // * Devuelve la cantidad de agua bebida o rellenada | Returns the water consumed or refilled
  float waterQuantity(float prevWeight, float weight){
    float total = 0;
    total = prevWeight - weight; // * El agua pesa 1g x cada ml (1Kg x cada litro) | Water weights 1g per ml (1kg per liter)
    return total;
  }

  unsigned long drinkControl(){
    currentWeight = findWeight();
    float drunk = waterQuantity(prevWeight,currentWeight);
    if(drunk > 0.01){
      currentCapacity -= drunk;
        totalDrunk += drunk;
      lastActivity = clock;
    }else if(drunk <-0.1){
      currentCapacity += fabsf(drunk); // * Si se rellena la capacidad aumenta (fabsf = valor absoluto float) | If there is a refill, capacity increases (fabs = absolute float value)
    }
    prevWeight = currentWeight;
    return lastActivity;
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

  void irDetection(){
    bool bottleDetected = !digitalRead(infraRed);
    if(bottleDetected){
      Serial.print("Botella presente");
    }else{
      Serial.print("Botella no colocada");
    }
  }
  
  void nightControl(){
    nightMode = !digitalRead(nightButton);
  }

  void buzzControl(long lastDrink){
    long hours = lastDrink/3600000L;
    if(hours<=1){
      noTone(buzzer);
    }else if((hours>1) && (hours<4)){
      tone(buzzer, 800, 300);
    }else if((hours>=4) && (hours<6)){
      tone(buzzer, 1500, 500);
    }else{
      tone(buzzer, 2000, 300);
      delay(400);
      tone(buzzer, 2000, 300); 
      delay(400);
      tone(buzzer, 2000, 300);
    }
  }

  void bluetoothControl(){
    if (SerialBT.hasClient()) {
      pixel.setPixelColor(0, pixel.Color(0, 0, 255));
    } else {
      pixel.setPixelColor(0, pixel.Color(255, 0, 0));
    }
  delay(1000);
  }
  void bluetoothSend(long hours){
    long hours = lastDrink/3600L;
    bluetooth.print("Datos");
    bluetooth.print(currentCapacity);
    bluetooth.print();
    bluetooth.print("|");
    bluetooth.print(nivelLuz);
    bluetooth.println();
  }

void setup() {
  pinMode(buzzer,OUTPUT);
  pinMode(infraRed,INPUT);
  pinMode(nightButton,INPUT_PULLUP);
  
  // * Inicializacion de componentes seriales | Serial components initialize
  //TODO Descomentar linea 130 cuando conectemos bascula
  // scale.begin(weight,weightSCK);
  Serial.begin(9600);
 // SerialBT.begin("MiESP32");
  bt.begin(9600);
  pixel.begin();
  
    // * Configuracion scale | Scale config
       // scale.set_scale();
        // scale.tare();
        // TODO Borrar esto cuando consigamos tarar bien la bascula | Delete this when we tare the scale 
          // Serial.println("Pon peso conocido y lee Serial Monitor");
}

void loop() {
  Serial.println("Tiempo del programa");
  Serial.println(clock);

  // * Modo noche | Night mode
    nightControl();
  
  clock = millis();
  lastDrink = (clock - lastActivity);

  //TODO Borrar esto cuando consigamos tarar bien la bascula | Delete this when we tare the scale 
    // if (scale.is_ready()) {
          // float reading = scale.get_units(10);  
          // Serial.print("Lectura raw: ");
          // Serial.println(reading);
        //}
  
  // * Control de los led y vibracion por tiempo sin beber (cada 10 segundos) | Led and buzzer control with time of last drink (cada 10 segundos)
      if (clock - lastLED > 10000UL) {
        ledControl(lastDrink);
          lastLED = clock;
        // * Control del zumbador | Vibration control
          if(!nightMode){
            buzzControl(lastDrink);
          }
      }
        
  // * Agua bebida y modificacion capacidad actual de la botella | Drunk water and modify current bottle's capacity

    lastActivity = drinkControl();
  
  // * Deteccion del sensor infrarrojo | IR sensor detection

    irDetection();
  
  // * Bluetooth conectado

    bluetoothControl();

    bluetoothSend(lastDrink);
  delay(2000);
}
