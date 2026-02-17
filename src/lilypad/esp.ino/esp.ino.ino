#include <WiFi.h>
#include "BluetoothSerial.h"

BluetoothSerial SerialBT;

void setup() {
  Serial.begin(115200);
  SerialBT.begin("ESP32_BT_WiFi");  // Nombre del dispositivo BT
  WiFi.begin("tuSSID", "tuPassword");  // Conecta a WiFi
  while (WiFi.status() != WL_CONNECTED) delay(500);
  Serial.println(WiFi.localIP());  // IP asignada
}

void loop() {
  if (Serial.available()) SerialBT.write(Serial.read());  // Serial a BT
  if (SerialBT.available()) Serial.write(SerialBT.read());  // BT a Serial
}
