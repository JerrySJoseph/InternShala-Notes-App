package com.jstechnologies.internshalanotesapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jstechnologies.internshalanotesapp.R;
import com.jstechnologies.internshalanotesapp.ui.fragments.Login.LoginFragment;
import com.jstechnologies.internshalanotesapp.ui.fragments.splashScreen.SplashScreen;

/* Main and only Activity of this application*/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateUi(savedInstanceState);
    }

    void updateUi(Bundle savedInstance){
        //if saved instance is not available, navigate to SplashScreen
        if(savedInstance==null){
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, new SplashScreen())
                    .commit();
        }
    }
}