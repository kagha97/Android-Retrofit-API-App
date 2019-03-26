package com.example.canadiandemocracy;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FetchRepMembers {
    private static final String LOG_TAG = FetchRepMembers.class.getSimpleName();

    private Context context;



    FetchRepMembers (Context context) {
        this.context = context;
    }



        public void getRepMembers(String s) {

        try {
            Log.d(LOG_TAG, s);
            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemsArray = jsonObject.getJSONArray("objects");

            Intent intent = new Intent (context, RepresentativeList.class);

            intent.putExtra("com.example.canadiandemocracy.REP_JSON", s);
            intent.putExtra("com.example.canadiandemocracy.NAME", itemsArray.getJSONObject(0).get("representative_set_name").toString());

            context.startActivity(intent);





        } catch (JSONException e) {


        }

    }


    public void fetchRepMembers(String rep) {
        //   rep = rep.toLowerCase();
        //  String loc = rep.replace(' ', '-') + "/";
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://represent.opennorth.ca");
        Retrofit retrofit = builder.build();

        repMembers client = retrofit.create(repMembers.class);


        Call<ResponseBody> call = client.apiResponse(rep);

        Log.d(LOG_TAG, "requesting: " + call.request().url().toString());

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    getRepMembers(response.body().string());
                    Log.d(LOG_TAG, "JSON RESPONSE RETROFIT MEMBERS: " + response.body().string());
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

}