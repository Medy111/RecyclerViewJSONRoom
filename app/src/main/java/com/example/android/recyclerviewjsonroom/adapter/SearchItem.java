package com.example.android.recyclerviewjsonroom.adapter;

/**
 * Created by Medy on 13.03.2018.
 */

// A List Item inside Main Activity - Recyclerview containing :
// IMAGEURL
// Creator


public class SearchItem {

private String mImageUrl;
private String mCreator;

// ALT + Insert create Constructor
    public SearchItem(String mImageUrl, String mCreator) {
        this.mImageUrl = mImageUrl;
        this.mCreator = mCreator;

    }

    // ALT + Insert Getters
    public String getmImageUrl() {
        return mImageUrl;
    }

    public String getmCreator() {
        return mCreator;
    }


}
