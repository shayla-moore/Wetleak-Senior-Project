package com.example.shayla.wetleak;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
* This class allows a patient to register by entering in their name and age. It allows them to do
 * all of this from a some EditText boxes and a button. It also is one of the classes that use
* Volley before AsyncTask was decided on as better for this program, because of time constraints,
* it couldn't be change to just one thread handler.
*
* @author Shayla Moore
*/
public class RegisterPatientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_patient);

        final EditText etPatientName = (EditText) findViewById(R.id.etPatientName);
        final EditText etPatientAge = (EditText) findViewById(R.id.etPatientAge);

        final Button bPatientRegister = (Button) findViewById(R.id.bPatientRegister);


        bPatientRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                try {
                    //Get patient name and their age from the EditText as a String
                    final String patientName = etPatientName.getText().toString();
                    final int age = Integer.parseInt(etPatientAge.getText().toString());


                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                //If success, patient is registered
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterPatientActivity.this);
                                    builder.setMessage("Successfully Registered")
                                            .create()
                                            .show();
                                    //Sends caretaker back to UserAreaActivity to search created patient
                                    Intent intent = new Intent(RegisterPatientActivity.this, UserAreaActivity.class);

                                    RegisterPatientActivity.this.startActivity(intent);
                                } else {
                                    //Throws an alert if register failed
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterPatientActivity.this);
                                    builder.setMessage("Register Failed")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    //Creates new volley object
                    NewPatientRequest registerPatientRequest = new NewPatientRequest(patientName, age, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(RegisterPatientActivity.this);
                    queue.add(registerPatientRequest);
                }catch(NumberFormatException ie){
                    ie.printStackTrace();
                }
            }


        });

    }

}