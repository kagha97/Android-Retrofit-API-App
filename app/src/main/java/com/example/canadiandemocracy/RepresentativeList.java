package com.example.canadiandemocracy;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RepresentativeList extends AppCompatActivity {

    // variables
    private RecyclerView RecylerView;
    private ArrayList<Representative> reps;
    private RepresentativeListAdapter RepListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_representative_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle(getIntent().getStringExtra("com.example.canadiandemocracy.NAME"));


        //set up resycler view
        RecylerView = findViewById(R.id.recyclerView);

        RecylerView.setLayoutManager(new LinearLayoutManager(this));

        //Rep object array
        reps = new ArrayList<>();

        RepListAdapter = new RepresentativeListAdapter(this, reps);

        RecylerView.setAdapter(RepListAdapter);



        initializeData();

    }

    //update adapter with json data from intent
    private void initializeData() {
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(getIntent().getStringExtra("com.example.canadiandemocracy.REP_JSON"));
            JSONArray itemsArray = jsonObject.getJSONArray("objects");


            for (int i=0; i < itemsArray.length(); i++) {
                JSONObject rep = itemsArray.getJSONObject(i);

                reps.add(new Representative(rep.getString("name"), rep.getString("party_name"), rep.getString("personal_url"),rep.getString("photo_url")));
            }

            RepListAdapter.notifyDataSetChanged();



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
