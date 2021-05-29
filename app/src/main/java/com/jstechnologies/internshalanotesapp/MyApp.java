package com.jstechnologies.internshalanotesapp;

import android.app.Application;

import com.jstechnologies.usermanagement.UserManagement;

public class MyApp extends Application {

    private static MyApp mInstance;

    public static synchronized MyApp getInstance(){

        return mInstance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
        UserManagement.Init("518136382026-dr1ovv7hrnvkb09teda54liocflvkimo.apps.googleusercontent.com","BY2bAZWhe95QFIQ7JbMXJ4rv");
    }
}
