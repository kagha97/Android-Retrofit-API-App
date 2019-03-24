package com.example.canadiandemocracy;

public class Representative {

    //Fields for rep info
    private String name;
    private String party;
    private String url;
    private final int imageResource;


    public Representative(String name, String party, String url, int imageResource) {
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
        return party;
    }

    public String getUrl() {
        return url;
    }

    public int getImageResource() {
        return imageResource;
    }


}
