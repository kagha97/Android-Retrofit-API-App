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
    FetchRepSet fetch;
    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpinner = (Spinner)findViewById(R.id.spinner);


       fetch = new FetchRepSet(this, mSpinner, url);
        fetch.getRepSets();

        mSpinner.setOnItemSelectedListener(this);







    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d(LOG_TAG, "onItem url: " + fetch.getUrlPos(position));
        new FetchRepMembers(this).fetchRepMembers(fetch.getUrlPos(position));

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
