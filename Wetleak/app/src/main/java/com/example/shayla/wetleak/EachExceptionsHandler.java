package com.example.shayla.wetleak;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

/**
 * This class handles exceptions for the generic AsyncTask class and is not written by me and all
 * credit is completely attributed to Oum Saokosal, the author of KosalGeek on 9/6/15.
 *
 * @author Oum Saokosal
 */

public interface EachExceptionsHandler {
    void handleIOException(IOException e);
    void handleMalformedURLException(MalformedURLException e);
    void handleProtocolException(ProtocolException e);
    void handleUnsupportedEncodingException(UnsupportedEncodingException e);
}