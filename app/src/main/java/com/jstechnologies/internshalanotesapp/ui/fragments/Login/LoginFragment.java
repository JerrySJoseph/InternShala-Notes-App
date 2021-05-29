package com.jstechnologies.internshalanotesapp.ui.fragments.Login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.jstechnologies.internshalanotesapp.R;
import com.jstechnologies.internshalanotesapp.ui.fragments.DashBoard.DashBoardFragment;
import com.jstechnologies.usermanagement.AuthFragment;
import com.jstechnologies.usermanagement.User;
import com.jstechnologies.usermanagement.UserManagement;

/*Login Fragment to implement Auth flow.
* Extends base AuthFragment class to implement auth-flow behind the scenes*/
public class LoginFragment extends AuthFragment implements View.OnClickListener {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.gSign).setOnClickListener(this::onClick);
    }

    @Override
    protected void onSignInSuccess(User user) {
       showToast("Welcome "+user.getName()+"!");
       navigateTo(R.id.fragment_container_view,new DashBoardFragment()); //navigate to dashboard on Successful login
    }

    @Override
    protected void onSignInFailed(String reason) {
        showToast(reason);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gSign:signInWithGoogle();break;  //sign in with google on button click
        }
    }


    @Override
    protected ViewModel createViewModel() {
        return null;
    }

    @Override
    protected void observe() {

    }
}