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
int16_t accelCount[3];    

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
  Wire.endTransmission(false);
  readAccelData(accelCount);
  delay(5);
  if (true) {
    digitalWrite(serverLed, LOW);
    dataIndex++;
    if (!myFile) {
      myFile = SD.open("datalog.txt", FILE_WRITE);
    }
    writeData();
    if(accelCount[0]==-1 && accelCount[1]==-1 && accelCount[2]==-1){
      Serial.println("error");
           
    }
    if (dataIndex == 100 || dataIndex == 0) {
      myFile.close();
      dataIndex = 0;
      Serial.print(second());
      Serial.print(" ");
      Serial.print(minute());
      Serial.print(" ");
      Serial.print(hour());
      Serial.print(" ");
      Serial.print(day());
      Serial.print(" ");
      Serial.print(month());
      Serial.print(" ");
      Serial.print(year());
      Serial.println();
      myFile = SD.open("datalog.txt", FILE_WRITE);
    }
  } else {
    myFile.close();    
    digitalWrite(serverLed, HIGH);
    //dataX.prettyPrintTo(Serial);
    if (!initialize) {
      dataX.add(accelCount[0]);
      dataY.add(accelCount[1]);
      dataZ.add(accelCount[2]);
    } else {
      dataX.set(arrayIndex, accelCount[0]);
      dataY.set(arrayIndex, accelCount[1]);
      dataZ.set(arrayIndex, accelCount[2]);
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
    myFile.print(accelCount[0]);
    myFile.print(" ");
    myFile.print(accelCount[1]);
    myFile.print(" ");
    myFile.print(accelCount[2]);
    myFile.print(" ");
    myFile.println(millis());
    //myFile.close();
  }
  else {
    Serial.println("error opening datalog.txt");
  }
}
void readAccelData(int16_t * destination)
{
  uint8_t rawData[6];  // x/y/z accel register data stored here
  readBytes(0x68, 0x3B, 6, &rawData[0]);  // Read the six raw data registers into data array
  destination[0] = (int16_t)((rawData[0] << 8) | rawData[1]) ;  // Turn the MSB and LSB into a signed 16-bit value
  destination[1] = (int16_t)((rawData[2] << 8) | rawData[3]) ;  
  destination[2] = (int16_t)((rawData[4] << 8) | rawData[5]) ; 
}
  void readBytes(uint8_t address, uint8_t subAddress, uint8_t count, uint8_t * dest)
{  
  Wire.beginTransmission(address);   // Initialize the Tx buffer
  Wire.write(subAddress);            // Put slave register address in Tx buffer
  Wire.endTransmission(false);       // Send the Tx buffer, but send a restart to keep connection alive
  uint8_t i = 0;
        Wire.requestFrom(address, count);  // Read bytes from slave register address 
  while (Wire.available()) {
        dest[i++] = Wire.read(); }         // Put read results in the Rx buffer
}

