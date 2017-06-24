package com.example.shayla.wetleak;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class is what the Caretaker sees when they log in properly. It allows them to create a new
 * patient or query a previous patient from a EditText box. It also is one of the classes that use
 * Volley before AsyncTask was decided on as better for this program, because of time constraints,
 * it couldn't be change to just one thread handler.
 *
 * @author Shayla Moore
 */

public class UserAreaActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final TextView welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMsg);
        final Button bNewPatient = (Button) findViewById(R.id.bNewPatient);
        final EditText etPreviousPatient = (EditText) findViewById(R.id.etPreviousPatient);
        final Button bPreviousPatient = (Button) findViewById(R.id.bPreviousPatient);


        //Get the caretaker name and title from an intent from the Log In page
        Intent intent = getIntent();
        String cname = intent.getStringExtra("cname");
        String title = intent.getStringExtra("title");
        final Caretaker careTaker = new Caretaker();

        //Set intent information into new caretaker object
        careTaker.setCname(cname);
        careTaker.setTitle(title);

        //Display welcome message to the screen
        String message = careTaker.getTitle() + " " + careTaker.getCname() + " Welcome! Choose or create a new patient!";
        welcomeMessage.setText(message);


        //Click this button and go to the patient register page
        bNewPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.bNewPatient:
                        Intent PatientRegisterIntent = new Intent(UserAreaActivity.this, RegisterPatientActivity.class);
                        PatientRegisterIntent.putExtra("cname", careTaker.getCname());
                        PatientRegisterIntent.putExtra("title", careTaker.getTitle());
                        UserAreaActivity.this.startActivity(PatientRegisterIntent);
                }
            }
        });

        //Search for a previous patient, if one doesn't exist, an alert is thrown
        bPreviousPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String pname = etPreviousPatient.getText().toString();

                Patient patient = new Patient();
                patient.setPname(pname);
                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                //If success and patient is found, go to Sensor Prep page
                                AlertDialog.Builder builder = new AlertDialog.Builder(UserAreaActivity.this);
                                builder.setMessage("Found Patient")
                                        .create()
                                        .show();

                                //Carry the patient name through an intent to the Sensor Prep page
                                Intent intent = new Intent(UserAreaActivity.this, SensorPrepMenu.class);
                                intent.putExtra("pname", pname);

                                UserAreaActivity.this.startActivity(intent);

                            } else {
                                //If patient is not found throw an alert
                                AlertDialog.Builder builder = new AlertDialog.Builder(UserAreaActivity.this);
                                builder.setMessage("Patient Not Found")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                //Use Volley to request the previous patient
                PreviousPatientRequest previousPatientRequest = new PreviousPatientRequest(pname, responseListener);
                RequestQueue queue = Volley.newRequestQueue(UserAreaActivity.this);
                queue.add(previousPatientRequest);
            }



    });


}
}
