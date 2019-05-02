package com.example.canadiandemocracy;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;

import java.util.ArrayList;

//Adapter for the recycler view
public class RepresentativeListAdapter extends RecyclerView.Adapter<RepresentativeListAdapter.ViewHolder> {
    private static final String LOG_TAG = RepresentativeListAdapter.class.getSimpleName();
    //
    private ArrayList<Representative> RepData;
    private Context Context;
    private ImageView RepImage;


    //Setting the fields with data
    RepresentativeListAdapter (Context context, ArrayList<Representative> repData) {
        this.RepData = repData;
        this.Context = context;

    }

    @Override
    public RepresentativeListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int id) {
        return new ViewHolder(LayoutInflater.from(Context).inflate(R.layout.rep_item, parent, false));
    }


    @Override
    //get the repItem for each card with index
    public void onBindViewHolder(RepresentativeListAdapter.ViewHolder holder, int pos){
        Representative currentRep = RepData.get(pos);

        holder.bindTo(currentRep);
    }



    public int getItemCount() {
        return RepData.size();
    }


    //View holder class, implements onClickListener
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name;
        private TextView party;
        private TextView url;
        private Representative currentRep;


        //set views for the card
        ViewHolder(View view) {
            super(view);

            name = itemView.findViewById(R.id.name);
            party = itemView.findViewById(R.id.party);
            url = itemView.findViewById(R.id.url);
            RepImage = itemView.findViewById(R.id.repPicture);


            //set the onClickListener so app knows when card is clicked
            view.setOnClickListener(this);

            //

        }

        @Override

        //when card is clicked
        public void onClick(View view) {
            //launch website with rep info, if no website show error

            if (currentRep.getUrl().equals("")) {
                displayToast("No website url for this Representative.");
            }
            else {
                Intent launchWebsite = new Intent(Intent.ACTION_VIEW, Uri.parse(currentRep.getUrl()));
                Context.startActivity(launchWebsite);
            }

        }


        public void displayToast(String message){
            Toast.makeText(Context, message,Toast.LENGTH_SHORT).show();
        }

        //binding the data from the rep object to card
        void bindTo (Representative rep) {
            this.currentRep = rep;
            name.setText(rep.getName());
            party.setText(rep.getParty());
            url.setText(rep.getUrl());

            Log.d(LOG_TAG, rep.getImageResource());

             Glide.with(Context).load(rep.getImageResource()).placeholder(R.drawable.ic_person).into(RepImage);
        }

    }

}
