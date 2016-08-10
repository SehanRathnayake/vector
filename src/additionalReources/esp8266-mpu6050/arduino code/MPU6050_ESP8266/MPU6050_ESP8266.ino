#include<Wire.h>
#include <ESP8266WiFi.h>
#include <ArduinoJson.h>

const char* ssid = "M@N";
const char* password = "123456712";
const char* host = "192.168.43.150";
const char* streamId   = "....................";
const char* privateKey = "....................";
const int MPU = 0x68; // I2C address of the MPU-6050
int16_t AcX, AcY, AcZ, Tmp, GyX, GyY, GyZ, count;

void setup() {
  Wire.begin(4, 5);
  Wire.beginTransmission(MPU);
  Wire.write(0x6B); // PWR_MGMT_1 register
  Wire.write(0); // set to zero (wakes up the MPU-6050)
  Wire.endTransmission(true);
  Serial.begin(9600);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.println(".");
    Serial.println("Connected to ");
    Serial.println(ssid);
  }
}
void loop() {
  StaticJsonBuffer<2000> jsonBuffer;
  JsonObject& root2 = jsonBuffer.createObject();
  JsonArray& data = root2.createNestedArray("xAxis");

for(int x=0;x<=250;x++){
  Wire.beginTransmission(MPU);
  Wire.write(0x3B); // starting with register 0x3B (ACCEL_XOUT_H)
  Wire.endTransmission(false);
  Wire.requestFrom(MPU, 14, true); // request a total of 14 registers
  AcX = Wire.read() << 8 | Wire.read(); // 0x3B (ACCEL_XOUT_H) & 0x3C (ACCEL_XOUT_L)
  AcY = Wire.read() << 8 | Wire.read(); // 0x3D (ACCEL_YOUT_H) & 0x3E (ACCEL_YOUT_L)
  AcZ = Wire.read() << 8 | Wire.read(); // 0x3F (ACCEL_ZOUT_H) & 0x40 (ACCEL_ZOUT_L)
  Tmp = Wire.read() << 8 | Wire.read(); // 0x41 (TEMP_OUT_H) & 0x42 (TEMP_OUT_L)
  GyX = Wire.read() << 8 | Wire.read(); // 0x43 (GYRO_XOUT_H) & 0x44 (GYRO_XOUT_L)
  GyY = Wire.read() << 8 | Wire.read(); // 0x45 (GYRO_YOUT_H) & 0x46 (GYRO_YOUT_L)
  GyZ = Wire.read() << 8 | Wire.read(); // 0x47 (GYRO_ZOUT_H) & 0x48 (GYRO_ZOUT_L)
  data.add(AcX);
}
  // Serial.print("Requesting URL: ");
  // Serial.println(url);

  //creating the json object

//  JsonObject& root = jsonBuffer.createObject();
//  root["xAxis"] = AcX;
//  root["yAxis"] = AcY;
//  root["zAxis"] = AcZ;



  // This will send the request to the server
  sendData(root2);
  
  // Serial.print("AcX = "); Serial.print(AcX);
  //Serial.print(" | AcY = "); Serial.print(AcY);
  //Serial.print(" | AcZ = "); Serial.print(AcZ);
  // Serial.print(" | Tmp = "); Serial.print(Tmp / 340.00 + 36.53); //equation for temperature in degrees C from datasheet
  // Serial.print(" | GyX = "); Serial.print(GyX);
  // Serial.print(" | GyY = "); Serial.print(GyY);
  // Serial.print(" | GyZ = "); Serial.println(GyZ);
  // Serial.println(WiFi.localIP());
  //delay(333);
}
void sendData(JsonObject& root)
{
  WiFiClient client;
  const int httpPort = 8080;
  if (!client.connect(host, httpPort)) {
    Serial.println("connection failed");
    return;
  }
  // We now create a URI for the request
  String url = "/vector/rest/vibrationData/1";
  client.print(String("POST ") + url + " HTTP/1.1\r\n" +
               "Host: " + host + "\r\n"
               "Connection: close\r\n");
  client.println("Cache-Control: no-cache");
  client.println("Content-Type: application/json");
  client.print("Content-Length: ");
  int length = root.measureLength();
  client.println(length);
  client.println();
  String out;
  root.printTo(out);
  client.println(out);
  unsigned long timeout = millis();
  while (client.available() == 0) {
    if (millis() - timeout > 5000) {
      Serial.println(">>> Client Timeout !");
      client.stop();
      return;
    }
  }
}

