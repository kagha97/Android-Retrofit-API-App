package com.example.canadiandemocracy;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class FetchRepSet extends AsyncTask<String, Void, String> {
    private Spinner mSpinner;
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    private ArrayList<String> repSetList = new ArrayList<>();
    private Context mContext;


    public FetchRepSet (Context context, Spinner mSpinner) {
        this.mSpinner = mSpinner;
        this.mContext = context;

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            Log.d(LOG_TAG, s);
            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemsArray = jsonObject.getJSONArray("objects");
            Log.d(LOG_TAG, itemsArray.toString());

            int i = 0;

            while (i < itemsArray.length()) {
                // Get the current item information.
                JSONObject repSet = itemsArray.getJSONObject(i);
                Log.d(LOG_TAG, itemsArray.getString(i));
                String repName = repSet.get("name").toString();

                Log.d(LOG_TAG, "Name: " +  repName);
                repSetList.add(repName);

                i++;

            }


           mSpinner.setAdapter(new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, repSetList));




        } catch (JSONException e) {


        }

    }


    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getRepSet();
    }
}