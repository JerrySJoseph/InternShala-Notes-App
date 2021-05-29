package com.jstechnologies.internshalanotesapp.ui.fragments.DashBoard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.jstechnologies.internshalanotesapp.R;
import com.jstechnologies.internshalanotesapp.data.models.Note;
import com.jstechnologies.internshalanotesapp.data.repository.NotesRepository;
import com.jstechnologies.internshalanotesapp.ui.adapters.NotesAdapter;
import com.jstechnologies.internshalanotesapp.ui.fragments.Login.LoginFragment;
import com.jstechnologies.internshalanotesapp.ui.fragments.addNote.AddNotesFragment;
import com.jstechnologies.usermanagement.AuthProtectedFragment;
import com.jstechnologies.usermanagement.UserManagement;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/*DashBoard Fragment to display notes and user data after successful sign In*/
public class DashBoardFragment extends AuthProtectedFragment<DashBoardViewModel> implements View.OnClickListener ,NotesAdapter.OnNoteItemClick {

    CircleImageView avatar;     //profile image
    TextView name;              //name
    AlertDialog.Builder alert;  //Instance of alert dialog
    RecyclerView recyclerView;  //recyclerVIew for displaying list of notes
    NotesAdapter adapter;       //Notes adapter for recyclerView
    TextInputEditText search;   //Search bar


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
        recyclerView=view.findViewById(R.id.recycler);
        adapter=new NotesAdapter();
        adapter.setOnNoteItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);
        search=view.findViewById(R.id.search);
        search.addTextChangedListener(textWatcher);
        viewmodel.loadData();
        updateUI();
    }

    //Observe changes in searchbox
    TextWatcher textWatcher= new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            filter(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

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
            case R.id.fab:navigateToWithBackStack(R.id.fragment_container_view,new AddNotesFragment());break;
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

    /*Create View model for this fragment*/
    @Override
    protected DashBoardViewModel createViewModel() {
        DashBoardViewModelFactory viewModelFactory= new DashBoardViewModelFactory(NotesRepository.getInstance());
        return new ViewModelProvider(this,viewModelFactory).get(DashBoardViewModel.class);
    }

    /*implementing observables*/
    @Override
    protected void observe() {
        viewmodel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.setModels(notes);
            }
        });
        viewmodel.getMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String message) {
                showToast(message);
            }
        });
    }


    @Override
    public void onEditClick(Note model) {
        Bundle bundle= new Bundle();
        bundle.putSerializable("note_data",model);
        AddNotesFragment fragment= new AddNotesFragment();
        fragment.setArguments(bundle);
        navigateToWithBackStack(R.id.fragment_container_view,fragment);
    }

    @Override
    public void onDeleteClick(int id) {
        alert=new AlertDialog.Builder(this.getContext());
        alert.setTitle("Delete");
        alert.setMessage("Are you sure you want to delete this note?");
        alert.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                viewmodel.deleteNote(id);
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

    private void filter(String query)
    {
        query=query.toLowerCase();
        List<Note> newModels=new ArrayList<>();
        for(Note model:viewmodel.getAllNotes().getValue())
        {
            if(model.getTitle().toLowerCase().contains(query)||model.getNotecontent().contains(query))
                newModels.add(model);

        }
        adapter.setModels(newModels);
    }
}