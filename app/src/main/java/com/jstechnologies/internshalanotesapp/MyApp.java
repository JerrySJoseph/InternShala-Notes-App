package com.jstechnologies.internshalanotesapp;

import android.app.Application;

import com.jstechnologies.usermanagement.UserManagement;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        UserManagement.Init("518136382026-dr1ovv7hrnvkb09teda54liocflvkimo.apps.googleusercontent.com","BY2bAZWhe95QFIQ7JbMXJ4rv");
    }
}
