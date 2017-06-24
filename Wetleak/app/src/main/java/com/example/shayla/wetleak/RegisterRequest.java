package com.example.shayla.wetleak;

/**
 * Created by Shayla on 5/2/2017.
 */

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://smoore.cs.loyola.edu/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String username, String password, String cname, String title, String email, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        params.put("cname", cname);
        params.put("title", title);
        params.put("email" , email);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}