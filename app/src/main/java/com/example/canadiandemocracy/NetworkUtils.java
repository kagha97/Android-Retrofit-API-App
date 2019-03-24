package com.example.canadiandemocracy;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NetworkUtils {
    //log
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    //base url
    private static final String REP_BASE_URL = "https://represent.opennorth.ca/representative-sets/?";
    // result limit
    private static final String MAX_RESULTS = "limit";
    //




    static String getRepSet() {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String repSetJSONString = null;


        try {
            Uri builtURI = Uri.parse(REP_BASE_URL).buildUpon()
                    .appendQueryParameter(MAX_RESULTS, "1000")
                    .build();

            URL requestURL = new URL(builtURI.toString());

            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Get the InputStream.
            InputStream inputStream = urlConnection.getInputStream();

            // Create a buffered reader from that input stream.
            reader = new BufferedReader(new InputStreamReader(inputStream));

            // Use a StringBuilder to hold the incoming response.
            StringBuilder builder = new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);

                builder.append("\n");
            }

            if (builder.length() == 0) {
                // Stream was empty. No point in parsing.
                return null;
            }

            repSetJSONString = builder.toString();

        } catch (IOException e) {

        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Log.d(LOG_TAG, "JSON rsp");
        Log.d(LOG_TAG, repSetJSONString);
        return repSetJSONString;
    }




}

