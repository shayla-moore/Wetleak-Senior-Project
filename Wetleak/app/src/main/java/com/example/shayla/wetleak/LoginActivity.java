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
 * This class is the main activity page for the whole application. It provides a way to securely log
 * in to the application, a way to retrieve a lost password by emailing the developer, and also
 * a way to go to the Register activity in order to register if you don't have an account with
 * WetLeak. On the page is two EditTexts for username and password entry, one button to log in, and
 * two links to either Register or send an email. This class uses volley which isn't used entirely
 * throughout the program as it isn't practical for reading in sensor data and processing it.
 *
 * @author Shayla Moore
 */
public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final TextView tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);
        final Button bLogin = (Button) findViewById(R.id.bSignIn);
        final TextView tvEmailLink = (TextView)findViewById(R.id.tvEmailLink);

        tvEmailLink.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
                //Creating email intent for getting a lost password
                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                //The developers email for sending the Username to
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"samoore1@loyola.edu"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Requesting my password for WetLeak");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Type your Username you need your password for here: ");
                emailIntent.setType("message/rfc822");

                //Allows the Caretaker to choose their email client
                startActivity(Intent.createChooser(emailIntent, "Choose your email client.."));

             }
        });

        //This Link links to the Register Page
        tvRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tvRegisterLink:
                        Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                        LoginActivity.this.startActivity(registerIntent);
                }
            }
        });

        //This button allows the Caretaker to be logged in, if not, it throws an alert
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            // Check if log in was a success
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                // Get the caretaker name and title from the JSONObject
                                String cname = jsonResponse.getString("cname");
                                String title = jsonResponse.getString("title");

                                //Send the information with an intent to UserAreaActivity
                                Intent intent = new Intent(LoginActivity.this, UserAreaActivity.class);
                                intent.putExtra("cname", cname);
                                intent.putExtra("title", title);


                                LoginActivity.this.startActivity(intent);
                            } else {
                                //If log in failed, throw an alert
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                //Send a LoginRequest using Volley
                LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }
}