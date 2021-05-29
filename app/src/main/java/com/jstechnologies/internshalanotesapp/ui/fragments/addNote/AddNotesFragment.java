package com.jstechnologies.internshalanotesapp.ui.fragments.addNote;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.jstechnologies.internshalanotesapp.R;
import com.jstechnologies.internshalanotesapp.data.models.Note;
import com.jstechnologies.internshalanotesapp.data.repository.NotesRepository;
import com.jstechnologies.internshalanotesapp.ui.fragments.DashBoard.DashBoardFragment;
import com.jstechnologies.internshalanotesapp.ui.fragments.DashBoard.DashBoardViewModel;
import com.jstechnologies.internshalanotesapp.ui.fragments.DashBoard.DashBoardViewModelFactory;
import com.jstechnologies.internshalanotesapp.ui.fragments.Login.LoginFragment;
import com.jstechnologies.usermanagement.AuthProtectedFragment;
import com.jstechnologies.usermanagement.UserManagement;


public class AddNotesFragment extends AuthProtectedFragment<AddNoteViewModel> implements View.OnClickListener {


    boolean editMode=false;
    Switch isImportant;
    EditText title,content;
    Note note=new Note();

    /*Create View model for this fragment*/
    @Override
    protected AddNoteViewModel createViewModel() {
        AddNoteViewModelFactory viewModelFactory= new AddNoteViewModelFactory(NotesRepository.getInstance());
        return new ViewModelProvider(this,viewModelFactory).get(AddNoteViewModel.class);
    }

    /*implementing observables*/
    @Override
    protected void observe() {
        viewmodel.getMessage().observe(this,(message)->showToast(message));
        viewmodel.getDone().observe(this,(done)->{
            if(done)
                navigateTo(R.id.fragment_container_view,new DashBoardFragment());
        });
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
        title=view.findViewById(R.id.et_name);
        content=view.findViewById(R.id.et_desc);
        isImportant=view.findViewById(R.id.sw_isimportant);
        view.findViewById(R.id.saveBtn).setOnClickListener(this::onClick);

        Bundle args=this.getArguments();
        if(args!=null)
        {
            editMode=true;
            updateUI((Note)args.getSerializable("note_data"));
        }

    }
    void updateUI(Note note)
    {
        this.note=note;
        title.setText(note.getTitle());
        content.setText(note.getNotecontent());
        isImportant.setChecked(note.isImportant());
    }
    //Save or update
    void saveOrUpdate(){

        note.setTitle(title.getText().toString().trim());
        note.setNotecontent(content.getText().toString().trim());
        note.setImportant(isImportant.isChecked());
        note.setDateTime(System.currentTimeMillis());
        note.setAuthor(UserManagement.getInstance(this.getContext()).getUser().getId());
        if(editMode)
            viewmodel.update(note);
        else
            viewmodel.save(note);
    }

    @Override
    protected void onSignOut() {
        navigateTo(R.id.fragment_container_view,new LoginFragment());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.saveBtn:saveOrUpdate();break;
        }
    }
}