package com.jstechnologies.internshalanotesapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jstechnologies.internshalanotesapp.R;
import com.jstechnologies.internshalanotesapp.ui.fragments.Login.LoginFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateUi(savedInstanceState);
    }

    void updateUi(Bundle savedInstance){
        if(savedInstance==null){
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, LoginFragment.class,savedInstance)
                    .commit();
        }
    }
}