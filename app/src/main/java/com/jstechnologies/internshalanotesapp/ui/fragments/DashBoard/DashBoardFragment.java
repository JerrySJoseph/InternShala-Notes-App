package com.jstechnologies.internshalanotesapp.ui.fragments.DashBoard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jstechnologies.internshalanotesapp.R;
import com.jstechnologies.internshalanotesapp.ui.fragments.Login.LoginFragment;
import com.jstechnologies.internshalanotesapp.ui.fragments.addNote.AddNotesFragment;
import com.jstechnologies.usermanagement.AuthProtectedFragment;
import com.jstechnologies.usermanagement.UserManagement;

import de.hdodenhof.circleimageview.CircleImageView;


public class DashBoardFragment extends AuthProtectedFragment<DashBoardViewModel> implements View.OnClickListener {

    CircleImageView avatar;
    TextView name;
    AlertDialog.Builder alert;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dash_board, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.signOut).setOnClickListener(this::onClick);
        view.findViewById(R.id.fab).setOnClickListener(this::onClick);
        name=view.findViewById(R.id.name);
        avatar=view.findViewById(R.id.avatar);
        updateUI();
    }

    void updateUI(){
        String _name=UserManagement.getInstance(this.getContext()).getUser().getName();
        _name=_name.split(" ")[0];
        name.setText(_name+"!");
        Glide.with(this).load(UserManagement.getInstance(this.getContext()).getUser().getPhotoUrl()).into(avatar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signOut:signOut();break;
            case R.id.fab:navigateToWithBackStack(R.id.fragment_container_view,new AddNotesFragment());
        }
    }

    @Override
    protected void onSignOut() {
        navigateTo(R.id.fragment_container_view,new LoginFragment());

    }

    @Override
    protected void signOut() {
        alert=new AlertDialog.Builder(this.getContext());
        alert.setTitle("Sign Out");
        alert.setMessage("Are you sure you want to signOut?");
        alert.setPositiveButton("Sign out", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DashBoardFragment.super.signOut();
                dialog.dismiss();
            }
        });
        alert.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               dialog.dismiss();
            }
        });
        alert.show();
    }

    @Override
    protected ViewModel createViewModel() {
        return null;
    }

    @Override
    protected void observe() {

    }
}