package com.example.android.recyclerviewjsonroom.adapter;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

// in Case our device rotates , or other activity also uses the same instance
// you can pretty much use this code for all your singleton needs
// https://code.tutsplus.com/tutorials/android-design-patterns-the-singleton-pattern--cms-29153
public class VolleySingleton {
    private static VolleySingleton mInstance;
    private RequestQueue mRequestQueue;

    private VolleySingleton(Context context) {
        //gets context for whole application , not only for 1 activityy
        mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    // this method will check if there already is a instance

    // meaning if lets say some fragment  or activity already created it
    // then no new instance will be created , instead the existing one will be used.

    // synchronized makes sure no 2 threads at the same time can acces it .
    public static synchronized VolleySingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleySingleton(context);
        }
// if there already is a instance , we return the existing one
        return mInstance;
    }

    // this method will
    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }
}