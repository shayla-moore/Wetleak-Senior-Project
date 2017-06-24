package com.example.shayla.wetleak;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class allows a Caretaker to register by entering in their name, username, password, title,
 * and email. It allows them to do all of this from a some EditText boxes and a button. It also
 * is one of the classes that use Volley before AsyncTask was decided on as better for this program,
 * because of time constraints, it couldn't be change to just one thread handler.
 *
 * @author Shayla Moore
 */
public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etTitle = (EditText) findViewById(R.id.etTitle);
        final Button bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                try {
                    //Get Caretaker fields from EditText boxes as Strings
                    final String username = etUsername.getText().toString();
                    final String password = etPassword.getText().toString();
                    final String cname = etName.getText().toString();
                    final String email = etEmail.getText().toString();
                    final String title = etTitle.getText().toString();


                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                //If success, caretaker is registered
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                    builder.setMessage("Successfully Registered")
                                            .create()
                                            .show();
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    RegisterActivity.this.startActivity(intent);
                                } else {
                                    //If register failed, throw an alert
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
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
                    //Send volley object RegisterRequest
                    RegisterRequest registerRequest = new RegisterRequest(username, password, cname, title, email, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                    queue.add(registerRequest);
                }catch(NumberFormatException ie){
                    ie.printStackTrace();
                }
            }


        });
    }
}