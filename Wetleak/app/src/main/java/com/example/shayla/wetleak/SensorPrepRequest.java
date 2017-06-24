package com.example.shayla.wetleak;

        import com.android.volley.Response;
        import com.android.volley.toolbox.StringRequest;

        import java.util.HashMap;
        import java.util.Map;

        import com.android.volley.Response;
        import com.android.volley.toolbox.StringRequest;

        import java.util.HashMap;
        import java.util.Map;

public class SensorPrepRequest extends StringRequest {
    private static final String SENSOR_PREP_REQUEST_URL = "http://smoore.cs.loyola.edu/SensorPrep.php";
    private Map<String, String> params;

    public SensorPrepRequest(String pname, Response.Listener<String> listener) {
        super(Method.POST, SENSOR_PREP_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("pname", pname);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}