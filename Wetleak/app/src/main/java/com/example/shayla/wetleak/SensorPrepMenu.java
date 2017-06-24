package com.example.shayla.wetleak;

import android.app.AlertDialog;
import android.content.Intent;
import android.hardware.Sensor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

/**
 * This class provides the UI menu that tells the Caretaker to get the sensors ready and adjusted
 * before clicking the button and having the patient's sensors read. This is so the application
 * doesn't throw pop ups right away just because the sensors are not connected properly.
 *
 * @author Shayla Moore
 */
public class SensorPrepMenu extends AppCompatActivity {

    /**
     * This onCreate is responsible for creating the UI and creates a Welcome Message on the menu as
     * well as an Explanation on what to do next, and also a button in order to start reading sensor
     * values.
     *
     * @param savedInstanceState    A saved state of the instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_prep_menu);


        TextView tvSensorWelcomeMsg = (TextView) findViewById(R.id.tvSensorWelcomeMsg);
        TextView tvExplanation = (TextView) findViewById(R.id.tvExplanation);
        final Button bStartReading = (Button) findViewById(R.id.bStartReading);

        //Creating a patient for setting patient values on this screen with intents
        final Patient patient = new Patient();

        //Receiving intents from UserAreaActivity
        Intent intent = getIntent();

        String patientName = intent.getStringExtra("pname");
        patient.setPname(patientName);

        String message = "Patient Name: " + patient.getPname();
        tvSensorWelcomeMsg.setText(message);

        String explanation = "On the next page, the patient will have their sensors read. Prep your"
                + " sensors now and press the button to start reading the patient's sensors.";
        tvExplanation.setText(explanation);

        bStartReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.bStartReading:
                        Intent intent = new Intent(SensorPrepMenu.this, SensorReadingActivity.class);
                        intent.putExtra("pname", patient.getPname());
                        SensorPrepMenu.this.startActivity(intent);
                }
            }
        });

    }
}
