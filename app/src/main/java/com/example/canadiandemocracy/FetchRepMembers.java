package com.example.canadiandemocracy;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FetchRepMembers extends AsyncTask<String, Void, String> {
    private static final String LOG_TAG = RetrofitFetch.class.getSimpleName();

    private Context context;
    private String reg;


    FetchRepMembers (Context context) {
        this.context = context;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            Log.d(LOG_TAG, s);
            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemsArray = jsonObject.getJSONArray("objects");

            Intent intent = new Intent (context, RepresentativeList.class);

            intent.putExtra("com.example.canadiandemocracy.REP_JSON", s);
            intent.putExtra("com.example.canadiandemocracy.NAME", reg);

            context.startActivity(intent);





        } catch (JSONException e) {


        }

    }


    @Override
    protected String doInBackground(String... strings) {
        reg = strings[0];
        return RetrofitFetch.getRepMembers(strings[0]);
    }
}