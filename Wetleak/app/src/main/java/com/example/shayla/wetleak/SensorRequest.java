package com.example.shayla.wetleak;


import com.google.gson.annotations.SerializedName;

/**
 * This Class uses Gson to help make the Json Array into objects. Each field is part of a
 * SensorRequest object and are each attribute on the MYSQL sensor database table.
 *
 * @author Shayla Moore
 */

public class SensorRequest {
    @SerializedName("sensorid")
    public int sensorid;

    @SerializedName("temp")
    public double temp;

    @SerializedName("humidity")
    public double humidity;

    @SerializedName("ph")
    public double ph;

    @SerializedName("movementx")
    public double movementx;

    @SerializedName("movementy")
    public double movementy;

    @SerializedName("movementz")
    public double movementz;

    @SerializedName("pname")
    public String pname;

}