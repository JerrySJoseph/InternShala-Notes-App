package com.jstechnologies.internshalanotesapp;

import android.app.Application;

import com.jstechnologies.usermanagement.UserManagement;

//*
// Base application class*/
public class MyApp extends Application {

    private static MyApp mInstance;

    public static synchronized MyApp getInstance(){
        return mInstance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;

        //intialiizing user management engine
        UserManagement.Init("YOUR_CLIENT_ID","YOUR_CLIENT_SECRET");
    }
}
