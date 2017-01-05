#include<Wire.h>
#include <ESP8266WiFi.h>
#include <ArduinoJson.h>
#include <SPI.h>
#include <SD.h>
#include <TimeLib.h>

String deviceId = "4";
int serverLed = 0;
int changeType = 10;
int wifiLed = 16;
File myFile;

const char* ssid = "M@N";
const char* password = "123456712";
const char* host = "192.168.43.150";
const char* streamId   = "....................";
const char* privateKey = "....................";
const int MPU = 0x68; // I2C address of the MPU-6050
int16_t AcX, AcY, AcZ, Tmp, GyX, GyY, GyZ, count;

int arrayIndex = 0;
int dataIndex = 0;
long accTimeout = 0;
boolean initialize = false;

DynamicJsonBuffer jsonBuffer;
JsonObject& root2 = jsonBuffer.createObject();
JsonArray& dataX = root2.createNestedArray("xAxis");
JsonArray& dataY = root2.createNestedArray("yAxis");
JsonArray& dataZ = root2.createNestedArray("zAxis");

void setup() {
  pinMode(wifiLed, OUTPUT);
  pinMode(serverLed, OUTPUT);
  pinMode(changeType, INPUT);
  //pinMode(successLed, OUTPUT);
  Serial.begin(9600);
  Serial.print("Initializing SD card...");

  if (!SD.begin(2)) {
    Serial.println("initialization failed!");
    return;
  }
  Serial.println("initialization done.");
  Wire.begin(4, 5);
  Wire.beginTransmission(MPU);
  Wire.write(0x6B); // PWR_MGMT_1 register
  Wire.write(0); // set to zero (wakes up the MPU-6050)
  Wire.endTransmission(true);
  WiFi.begin(ssid, password);
  /*while (WiFi.status() != WL_CONNECTED) {
    digitalWrite(wifiLed, LOW);
    delay(500);
    digitalWrite(wifiLed, HIGH);
    Serial.println(".");
    Serial.println("Connecting to ");
    Serial.println(ssid);
    }*/
  digitalWrite(wifiLed, HIGH);
  myFile = SD.open("datalog.txt", FILE_WRITE);


}
void loop() {
  Wire.beginTransmission(MPU);
  Wire.write(0x3B); // starting with register 0x3B (ACCEL_XOUT_H)
  Wire.endTransmission(true);
  Wire.requestFrom(MPU, 14, true); // request a total of 14 registers
  AcX = Wire.read() << 8 | Wire.read(); // 0x3B (ACCEL_XOUT_H) & 0x3C (ACCEL_XOUT_L)
  AcY = Wire.read() << 8 | Wire.read(); // 0x3D (ACCEL_YOUT_H) & 0x3E (ACCEL_YOUT_L)
  AcZ = Wire.read() << 8 | Wire.read(); // 0x3F (ACCEL_ZOUT_H) & 0x40 (ACCEL_ZOUT_L)
  delay(5);
  if (true) {
    digitalWrite(serverLed, LOW);
    dataIndex++;
    if (!myFile) {
      myFile = SD.open("datalog.txt", FILE_WRITE);
    }
    writeData();
    if (dataIndex == 1) {
      myFile.close();
      dataIndex = 0;
    }
  } else {
    myFile.close();    
    digitalWrite(serverLed, HIGH);
    //dataX.prettyPrintTo(Serial);
    if (!initialize) {
      dataX.add(AcX);
      dataY.add(AcY);
      dataZ.add(AcZ);
    } else {
      dataX.set(arrayIndex, AcX);
      dataY.set(arrayIndex, AcY);
      dataZ.set(arrayIndex, AcZ);
    }
    arrayIndex++;
    if (arrayIndex == 100) {
      Serial.print("sever");
      initialize = true;
      long time = millis() - accTimeout;
      if (WiFi.status() != WL_CONNECTED) {
        while (WiFi.status() != WL_CONNECTED) {
          digitalWrite(wifiLed, LOW);
          delay(500);
          digitalWrite(wifiLed, HIGH);
          Serial.println(".");
          Serial.println("Connecting to ");
          Serial.println(ssid);
        }
        digitalWrite(wifiLed, HIGH);
      }
      sendData(root2);
      accTimeout = millis();
      arrayIndex = 0;
    }
  }
}

void sendData(JsonObject& root)
{
  WiFiClient client;
  const int httpPort = 8082;
  if (!client.connect(host, httpPort)) {
    Serial.println("connection failed");
    digitalWrite(serverLed, HIGH);
    delay(200);
    digitalWrite(serverLed, LOW);
    return;
  }
  digitalWrite(serverLed, HIGH);
  // We now create a URI for the request
  String url = "/vector/rest/vibrationData/3";
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
    if (millis() - timeout > 10000) {
      Serial.println(">>> Client Timeout !");
      client.stop();
      return;
    }
  }
}

void writeData() {
  // if the file is available, write to it:
  if (myFile) {
    myFile.print(AcX);
    myFile.print(" ");
    myFile.print(AcY);
    myFile.print(" ");
    myFile.print(AcZ);
    myFile.print(" ");
    myFile.println(millis());
    //myFile.close();
  }
  else {
    Serial.println("error opening datalog.txt");
  }
}

