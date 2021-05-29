package com.jstechnologies.internshalanotesapp.utils;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/*Custom class implementation of SingleThread Executor as an alternate to AsyncTask*/
public class DatabaseExecutor implements Executor {

    //Executor Instance
    private final Executor executor;

    public DatabaseExecutor() {
        this.executor = Executors.newSingleThreadExecutor();
    }

    @Override
    public void execute(@NonNull Runnable runnable) {
        executor.execute(runnable);
    }
}