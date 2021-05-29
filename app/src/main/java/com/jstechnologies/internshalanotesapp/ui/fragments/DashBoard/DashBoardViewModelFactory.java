package com.jstechnologies.internshalanotesapp.ui.fragments.DashBoard;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.jstechnologies.internshalanotesapp.data.repository.NotesRepository;

public class DashBoardViewModelFactory implements ViewModelProvider.Factory {

    NotesRepository repository;

    public DashBoardViewModelFactory(NotesRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DashBoardViewModel.class)) {
            return (T) new DashBoardViewModel(repository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
