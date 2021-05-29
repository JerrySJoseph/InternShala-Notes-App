package com.jstechnologies.internshalanotesapp.ui.fragments.addNote;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.jstechnologies.internshalanotesapp.data.repository.NotesRepository;
import com.jstechnologies.internshalanotesapp.ui.fragments.DashBoard.DashBoardViewModel;

public class AddNoteViewModelFactory implements ViewModelProvider.Factory {
    NotesRepository repository;

    public AddNoteViewModelFactory(NotesRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AddNoteViewModel.class)) {
            return (T) new AddNoteViewModel(repository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
