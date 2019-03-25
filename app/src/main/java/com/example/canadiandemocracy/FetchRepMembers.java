package com.example.canadiandemocracy;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FetchRepMembers extends AsyncTask<String, Void, String> {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    private Context context;


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

            context.startActivity(intent);





        } catch (JSONException e) {


        }

    }


    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getRepMembers(strings[0]);
    }
}