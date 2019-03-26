package com.example.canadiandemocracy;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitFetch {
    //log
    private static final String LOG_TAG = RetrofitFetch.class.getSimpleName();
    //base url
    private static final String REP_BASE_URL = "https://represent.opennorth.ca/representative-sets/?";
    // result limit
    private static final String MAX_RESULTS = "limit";
    //

    private static final String BASE_URL = "https://represent.opennorth.ca/representatives";


    private String JSONREPSETS;


    public void getRepSets() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://represent.opennorth.ca");
        Retrofit retrofit = builder.build();

        repSets client = retrofit.create(repSets.class);


        Call<ResponseBody> call = client.apiResponse();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    JSONREPSETS = response.body().string();
                    Log.d(LOG_TAG, "JSON RESPONSE RETROFIT: "  + JSONREPSETS);
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    public String getRepSetString() {
        return JSONREPSETS;
    }


    static String getRepMembers(String rep) {
        rep = rep.toLowerCase();
        String loc = rep.replace(' ', '-') + "/";
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String repSetJSONString = null;


        try {
            Uri builtURI = Uri.parse(BASE_URL).buildUpon()
                    .appendPath(loc)
                    .appendQueryParameter(MAX_RESULTS, "1000")
                    .build();

            URL requestURL = new URL(builtURI.toString());
            Log.d(LOG_TAG, "URL: " + builtURI);
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

        Log.d(LOG_TAG, "JSON RSP mem");
        Log.d(LOG_TAG, repSetJSONString);
        return repSetJSONString;
    }




}

