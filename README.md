# WetLeak Android Application for Senior Project

The application being developed is for an Android stand-alone device which functions as a visualinterface and alert system. The product works with an Adafruit Arduino Uno, an Adafruit FeatherM0 and sensors in order to take the readings from the patient that it needs. The sensors trackhumidity, temperature, and movement on a Patient. The Feather M0 allows the sensor data to besent via a secure WiFi connection to a MySQL database on the Computer Science servers at LoyolaUniversity Maryland. The MySQL database also handles login functionality for the Caretaker andthe Patient. Currently, there is no device, which combines all functionalities the way Wet Leakdoes. Therefore, it is a prototype of a new product whose hardware and functionality can be usedin the future.

## Demo Pictures

Registering a new Caretaker

![alt text](http://i1067.photobucket.com/albums/u430/shayla_m/PAGE1_REGISTER_NEW_CARETAKER_zpsn0kdtba5.jpg)

Logging in as a Caretaker

![alt text](http://i1067.photobucket.com/albums/u430/shayla_m/PAGE2_LOGIN_WITH_THE_CARETAKER_zpsha7u86ec.jpg)

The Caretaker Menu (Allows you to Register or Query for a Previous Patient)

![alt text](http://i1067.photobucket.com/albums/u430/shayla_m/PAGE3_CARETAKERS_PATIENT_MENU_zpsfd3jcy3w.jpg)

Registering a New Patient

![alt text](http://i1067.photobucket.com/albums/u430/shayla_m/PAGE4_CREATING_A_NEW_PATIENT_zpseg90ceo6.jpg)

Patient Hardware Prep Message

![alt text](http://i1067.photobucket.com/albums/u430/shayla_m/PAGE6_SENSORPREP_MENU_WITH_PATIENT_zpsloywo8mu.jpg)

Patient Sensor Readings

![alt text](http://i1067.photobucket.com/albums/u430/shayla_m/PAGE8_SENSORREADING_PAGE_FIRST_SET_OF_READINGS_zpsrjaxrigq.jpg)

Sample Alert Pop-up on Sensor Reading Interface

![alt text](http://i1067.photobucket.com/albums/u430/shayla_m/PHALERT_AT_8.8PH_zps4rp9xokh.jpg)

## Done but Needs to be Added to this Repository

1. PHP files that allow the application to talk to the MySQL database.

## Work To Do

1. Add a GraphView of the changes in Sensor Readings over time.

2. Convert fully to Asynctask and remove remaining calls to Volley.

3. Change PHP files to handle SQL injection security issues.

4. Improve the UI

## Authors

* **Shayla Moore** - *Initial work*

## Acknowledgments

*Inspiration goes to this being a project for my senior project.