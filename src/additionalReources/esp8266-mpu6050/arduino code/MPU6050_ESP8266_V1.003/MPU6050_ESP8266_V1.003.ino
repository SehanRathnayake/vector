#include "SoftwareSerial.h"
String ssid = "M@N";

String password = "123456712";

SoftwareSerial esp(4, 5);// RX, TX

String data;

String server = "http://localhost"; // www.example.com

String uri = "/vector/rest/vibrationData/1";// our example is /esppost.php

int DHpin = 6;//sensor pin

byte dat [5];

String temp , hum;



//reset the esp8266 module

void reset() {

  esp.println("AT+RST");

  delay(1000);

  if (esp.find("OK") ) Serial.println("Module Reset");

}

//connect to your wifi network

void connectWifi() {

  String cmd = "AT+CWJAP=\"" + ssid + "\",\"" + password + "\"";

  esp.println(cmd);

  delay(4000);

  if (esp.find("OK")) {

    Serial.println("Connected!");

  }

  else {

    connectWifi();

    Serial.println("Cannot connect to wifi");
  }

}
void setup() {

  pinMode (DHpin, OUTPUT);

  esp.begin(9600);

  Serial.begin(9600);

  reset();

  connectWifi();

}


void loop () {

 

}

void httppost () {

  esp.println("AT+CIPSTART=\"TCP\",\"" + server + "\",8080");//start a TCP connection.

  if ( esp.find("OK")) {

    Serial.println("TCP connection ready");

  } delay(1000);

  String postRequest =

    "POST " + uri + " HTTP/1.0\r\n" +

    "Host: " + server + "\r\n" +

    "Accept: *" + "/" + "*\r\n" +

    "Content-Length: " + data.length() + "\r\n" +

    "Content-Type: application/x-www-form-urlencoded\r\n" +

    "\r\n" + data;

  String sendCmd = "AT+CIPSEND=";//determine the number of caracters to be sent.

  esp.print(sendCmd);

  esp.println(postRequest.length() );

  delay(500);

  if (esp.find(">")) {
    Serial.println("Sending.."); esp.print(postRequest);

    if ( esp.find("SEND OK")) {
      Serial.println("Packet sent");

      while (esp.available()) {

        String tmpResp = esp.readString();

        Serial.println(tmpResp);

      }

      // close the connection

      esp.println("AT+CIPCLOSE");

    }

  }
}
