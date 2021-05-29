package com.jstechnologies.internshalanotesapp.ui.fragments.DashBoard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jstechnologies.internshalanotesapp.data.api.LocalNotesApi;
import com.jstechnologies.internshalanotesapp.data.models.Note;
import com.jstechnologies.internshalanotesapp.data.repository.NotesRepository;
import com.jstechnologies.internshalanotesapp.utils.ApiCallback;

import java.util.List;

public class DashBoardViewModel extends ViewModel {

    NotesRepository repository;

    public DashBoardViewModel(NotesRepository repository) {
        this.repository = repository;
    }

    public void loadData(){
        repository.getAll();
    }
    public LiveData<List<Note>> getAllNotes(){
        return  repository.getNotesLiveData();
    }
    public LiveData<String> getMessage(){
        return  repository.getRepositoryMessage();
    }
    public void deleteNote(int id){
        LocalNotesApi.getInstance().deleteNote(id, new ApiCallback<Note>() {
            @Override
            public void onSuccess(List<Note> models) {
                loadData();
            }

            @Override
            public void onError(int errorCode, String errorMessage) {
                repository.pushMessage(errorMessage);
            }
        });
    }
}
