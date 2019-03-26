package com.example.canadiandemocracy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner mSpinner;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    RetrofitFetch retrofitFetch = new RetrofitFetch();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpinner = (Spinner)findViewById(R.id.spinner);



        Log.d(LOG_TAG, "MainActivtiy JSON: " + retrofitFetch.getRepSetString());
        new FetchRepSet(this, mSpinner).getRepSets();


        mSpinner.setOnItemSelectedListener(this);







    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        new FetchRepMembers(this).execute(parent.getItemAtPosition(position).toString());

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
