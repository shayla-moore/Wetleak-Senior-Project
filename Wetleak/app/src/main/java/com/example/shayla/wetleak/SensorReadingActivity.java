package com.example.shayla.wetleak;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class consists of methods exclusively for processing sensor data for the patient.
 * It contains a timer in order to call the AsyncTask every five seconds, a way to make alert pop
 * ups and also to set the thresholds in which those alert pop ups will be executed.
 *
 * <p>It should be noted here that this is the only class that uses AsyncTask as not enough
 * documentation for Volley was found online in order to call a Thread with a timer. </p>
 *
 * @author Shayla Moore
 *
 */

public class SensorReadingActivity extends AppCompatActivity implements AsyncResponse {

    /**
     * lvSensors is of type ListView and provides a simple ListView for the UI that will
     * hold sensor data.
     */
    ListView lvSensors;

    /**
     * This is a void method that performs that action of creating the UI. It also calls the other
     * methods in this class through thecallAsynchronousTask() which is responsible for querying
     * the database every five seconds for the sensor data.
     * @param savedInstanceState    This saves an instance of the state.
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_reading);

        lvSensors = (ListView) findViewById(R.id.lvSensorList);

        callAsynchronousTask();
    }

    /**
     * Returns void and is part of the required methods for a new PostResponseAsyncTask object. When
     * a new PostResponseAsyncTask object is called, it will call this method. It's main
     * functionality in this class is to process the sensor data from the php file and MySQL server
     * into a ListView using Json.
     *
     * @param result    passes through the information from responseTask.execute()
     */
    @Override
    public void processFinish(String result) {
        //Let the Caretaker know that the page is refreshing with new sensor readings
        Toast.makeText(SensorReadingActivity.this, "Refresh", Toast.LENGTH_LONG).show();

        //Allows SensorRequest objects to be parsed with the JsonConverter
        ArrayList<SensorRequest> sensorList = new JsonConverter<SensorRequest>()
                .toArrayList(result, SensorRequest.class);

        ArrayList<String> sensors = new ArrayList<String>();

        //Putting all of the SensorRequest objects into an ArrayList
        for(SensorRequest value: sensorList) {

            sensors.add("pname " + value.pname);
            sensors.add("Temp: " + value.temp);
            sensors.add("Humidity " + value.humidity);
            sensors.add("Ph " + value.ph);
            sensors.add("MovementX " + value.movementx);
            sensors.add("MovementY " + value.movementy);
            sensors.add("MovementZ " + value.movementz);

            //Checking the values for a threshold breach
            thresholdCheck(value.temp, value.humidity, value.ph);

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SensorReadingActivity.this,
                android.R.layout.simple_list_item_1, sensors);


        //Setting the ArrayAdapter and new sensor readings to the lvSensor ListView field.
        lvSensors.setAdapter(adapter);

    }


    /**
     * Returns void and is responsible for calling the PostResponseAsyncTask thread every five
     * seconds. It does this by utilizing handler, a timer, and then calling Runnable. Inside this
     * class a new instance of PostResponseAsyncTask is called every time because a new instance
     * of this object is supposed to be created every time or the application will crash.
     *
      */
    public void callAsynchronousTask() {
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            //Make new PostResponseAsyncTask object for this time instance
                            PostResponseAsyncTask responseTask = new PostResponseAsyncTask(
                                    SensorReadingActivity.this, SensorReadingActivity.this);

                            //This is the php responsible for reading in Sensors from the database
                            responseTask.execute("http://smoore.cs.loyola.edu/SensorReading.php");

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 5000); //execute every 5 seconds
    }

    /**
     * Returns void and is responsible for creating an alertDialogBuilder for when a threshold is
     * breached. When a pop up is created, it can be exited with a click of a button and will appear
     * if the threshold is breached again.
     *
     * @param thresholdBreachValue  This is the sensor and the reading in which the pop up was
     *                              created for during getting the readings.
     */
    public void callAlert(String thresholdBreachValue) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SensorReadingActivity.this);

        //Set Title
        alertDialogBuilder.setTitle("WARNING: CHECK SENSORS A THRESHOLD IS BREACHED");
        String alertMessage = "Check the " + thresholdBreachValue + " sensor!";

        alertDialogBuilder.setMessage(alertMessage)
                .setCancelable(false)
				.setPositiveButton("Okay",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                //If button is pressed, cancel pop up
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    /**
     * Returns void and is responsible for the logic between pop ups and also actually calling the
     * pop ups when a threshold is breached. Because all sensor readings start at zero before data
     * is read in, 0 will be ignored when it is the current value. Movement wasn't able to be
     * processed due to time contraints, but the alerts for temperature, humidity, and ph have been
     * completed.
     *
     * @param temp  This is the temperature sensor reading to be checked.
     * @param humidity  This is the humidity sensor reading to be checked.
     * @param ph    This is the ph sensor reading to be checked.
     */
    public void thresholdCheck(double temp, double humidity, double ph) {

        //If the temperature is below 97 degrees or above 98.9 degrees throw an alert
        if(temp > 0 && (temp < 97.0 || temp > 98.9)) {
            callAlert("Temp " + temp);
        }

        // If the humidity is above 60% throw an alert.
        if(humidity > 0 && humidity > 60.0) {
            callAlert("Humidity " + humidity);
        }

        //If the pH is above 6.0 throw an alert.
        if(ph > 6.0) {
            callAlert("pH " + ph);
        }

    }
}