package com.example.canadiandemocracy;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FetchRepSet {
    private Spinner mSpinner;
    private static final String LOG_TAG = FetchRepSet.class.getSimpleName();

    //keep track of the names of rep sets
    private ArrayList<String> repSetList = new ArrayList<>();
    //keep all the rep url's
    private ArrayList<String> repSetUrl = new ArrayList<>();
    private Context mContext;
    private String url;



    //constructor
    public FetchRepSet (Context context, Spinner mSpinner, String url) {
        this.mSpinner = mSpinner;
        this.mContext = context;
        this.url = url;

    }

    //get all the rep sets using retrofit from api
    public void getRepSets() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://represent.opennorth.ca");
        Retrofit retrofit = builder.build();

        //use repSets interface for GET req
        repSets client = retrofit.create(repSets.class);
        Call<ResponseBody> call = client.apiResponse();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    //pass the json string to function so lists can be populated
                    fetchRepSets(response.body().string());
                } catch (Exception e) {
                    Log.d("Retrofit Error: ", e.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    public String getUrlPos(int i) {
       // Collections.sort(repSetUrl);
        return repSetUrl.get(i);
    }

    protected void fetchRepSets(String s) {

        try {

            //get data out of json objects
            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemsArray = jsonObject.getJSONArray("objects");
            Log.d(LOG_TAG, itemsArray.toString());

            int i = 0;

            //go through data and add to lists
            while (i < itemsArray.length()) {
                // Get the current item information.
                JSONObject repSet = itemsArray.getJSONObject(i);
                Log.d(LOG_TAG, itemsArray.getString(i));
                String repName = repSet.get("name").toString();
                url = repSet.getJSONObject("related").get("representatives_url").toString();
                Log.d(LOG_TAG, i + " URL: " +  url);
                repSetUrl.add(url);
                repSetList.add(repName);

                i++;

            }


            //add data to to spinner
           mSpinner.setAdapter(new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, repSetList));

        } catch (JSONException e) {
            Log.d(LOG_TAG, e.getMessage());

        }

    }


}