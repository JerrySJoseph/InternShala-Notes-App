package com.jstechnologies.internshalanotesapp.ui.fragments.addNote;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jstechnologies.internshalanotesapp.R;
import com.jstechnologies.usermanagement.AuthProtectedFragment;


public class AddNotesFragment extends AuthProtectedFragment<AddNoteViewModel> {


    @Override
    protected AddNoteViewModel createViewModel() {
        return null;
    }

    @Override
    protected void observe() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar toolbar= view.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


    }


    @Override
    protected void onSignOut() {

    }
}