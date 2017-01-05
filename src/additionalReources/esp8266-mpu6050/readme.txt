Installing with Boards Manager

Starting with 1.6.4, Arduino allows installation of third-party platform packages using Boards Manager. We have packages available for Windows, Mac OS, and Linux (32 and 64 bit).

    Install Arduino 1.6.8 from the Arduino website.
    Start Arduino and open Preferences window.
    Enter http://arduino.esp8266.com/stable/package_esp8266com_index.json into Additional Board Manager URLs field. You can add multiple URLs, separating them with commas.
    Open Boards Manager from Tools > Board menu and install esp8266 platform (and don't forget to select your ESP8266 board from Tools > Board menu after installation).

The best place to ask questions related to this core is ESP8266 community forum: http://www.esp8266.com/arduino. If you find this forum or the ESP8266 Boards Manager package useful, please consider supporting it with a donation. 

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
download mpu6050 and i2cdev libraries and copy them into the libraries folder in arduino folder

only changes:
#include <avr/pgmspace.h>
to
#ifndef ESP8266
#include <avr/pgmspace.h>
#else
#include <pgmspace.h>
#endif

inside MPU6050.h, MPU6050_6Axis_MotionApps20.h, MPU6050_9Axis_MotionApps41.h
- See more at: http://www.esp8266.com/viewtopic.php?f=29&t=6999#sthash.5ksSpoHy.dpuf
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
in the code:
wire.begin() 
to
wire.begin(4,5)
(wire.begin(sda,scl) above is for esp12) 

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
node mcu pin modes http://scottsnowden.co.uk/esp8266-developmentbreakout-board-arrived/
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
To normal boot
GPIO0-->high
GPIO2-->X
GPIO15-->low
CHIP_EN-->3.3v