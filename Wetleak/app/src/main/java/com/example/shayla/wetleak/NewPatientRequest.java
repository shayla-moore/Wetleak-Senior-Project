package com.example.shayla.wetleak;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shayla on 5/8/2017.
 */

public class NewPatientRequest extends StringRequest {
    private static final String PATIENT_REGISTER_REQUEST_URL = "http://smoore.cs.loyola.edu/RegisterNewPatient.php";
    private Map<String, String> params;

    public NewPatientRequest(String pname, int age, Response.Listener<String> listener) {
        super(Method.POST, PATIENT_REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("pname", pname);
        params.put("age", age + "");


    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
