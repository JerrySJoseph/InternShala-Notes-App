package com.jstechnologies.internshalanotesapp.ui.fragments.addNote;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jstechnologies.internshalanotesapp.data.api.LocalNotesApi;
import com.jstechnologies.internshalanotesapp.data.models.Note;
import com.jstechnologies.internshalanotesapp.data.repository.NotesRepository;
import com.jstechnologies.internshalanotesapp.utils.ApiCallback;

import java.util.List;

public class AddNoteViewModel extends ViewModel {
    NotesRepository repository;
    MutableLiveData<Boolean> done;
    MutableLiveData<String> message;
    public AddNoteViewModel(NotesRepository repository) {
        this.repository = repository;
        done=new MutableLiveData<>();
        message=new MutableLiveData<>();
    }


    public LiveData<Boolean> getDone() {
        return done;
    }

    public LiveData<String> getMessage() {
        return message;
    }

    public void update(Note note){
        LocalNotesApi.getInstance().updateNote(note, new ApiCallback<Note>() {
            @Override
            public void onSuccess(List<Note> models) {
                done.setValue(true);
            }

            @Override
            public void onError(int errorCode, String errorMessage) {
                message.setValue(errorMessage);
            }
        });
    }
    public void save(Note note){
        LocalNotesApi.getInstance().insert(note, new ApiCallback<Note>() {
            @Override
            public void onSuccess(List<Note> models) {
                done.setValue(true);
            }

            @Override
            public void onError(int errorCode, String errorMessage) {
                message.setValue(errorMessage);
            }
        });
    }
}
