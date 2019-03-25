package com.example.canadiandemocracy;

import android.net.Uri;

public class Representative {

    //Fields for rep info
    private String name;
    private String party;
    private String url;
    private final String imageResource;


    public Representative(String name, String party, String url, String imageResource) {
        this.name = name;
        this.party = party;
        this.url = url;
        this.imageResource = imageResource;
    }


    //Getters
    public String getName() {
        return name;
    }

    public String getParty() {
        if (party.equals("")) {
            return "No party details.";
        }
            return party;
    }

    public String getUrl() {
        return url;
    }

    public String getImageResource() {
        return imageResource;
    }


}
