package com.jstechnologies.internshalanotesapp.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.jstechnologies.internshalanotesapp.data.api.LocalNotesApi;
import com.jstechnologies.internshalanotesapp.data.models.Note;
import com.jstechnologies.internshalanotesapp.utils.ApiCallback;
import com.jstechnologies.internshalanotesapp.utils.RepositoryCallback;

import java.util.List;

public class NotesRepository {


    private static NotesRepository mInstance;
    private MutableLiveData<String> repositoryMessage;
    private MutableLiveData<List<Note>> notesLiveData;

    public static synchronized NotesRepository getInstance(){
        if(mInstance==null)
            mInstance=new NotesRepository();
        return mInstance;
    }

    public NotesRepository() {
        this.repositoryMessage=new MutableLiveData<>();
        this.notesLiveData=new MutableLiveData<>();
    }

    public LiveData<String> getRepositoryMessage() {
        return repositoryMessage;
    }

    public LiveData<List<Note>> getNotesLiveData() {
        return notesLiveData;
    }

    public void pushMessage(String message){
        this.repositoryMessage.setValue(message);
    }

    public void getAll(){
        LocalNotesApi.getInstance().fetchAll(new ApiCallback<Note>() {
            @Override
            public void onSuccess(List<Note> models) {
                notesLiveData.setValue(models);
            }

            @Override
            public void onError(int errorCode, String errorMessage) {
                repositoryMessage.setValue(errorMessage);
            }
        });
    }
    public void insert(Note note){
        LocalNotesApi.getInstance().insert(note, new ApiCallback<Note>() {
            @Override
            public void onSuccess(List<Note> models) {
               notesLiveData.setValue(models);
            }

            @Override
            public void onError(int errorCode, String errorMessage) {
                repositoryMessage.setValue(errorMessage);
            }
        });
    }
    public void delete(int id){
        LocalNotesApi.getInstance().deleteNote(id, new ApiCallback<Note>() {
            @Override
            public void onSuccess(List<Note> models) {
                notesLiveData.setValue(models);
            }

            @Override
            public void onError(int errorCode, String errorMessage) {
                repositoryMessage.setValue(errorMessage);
            }
        });
    }
    public void update(Note note){
        LocalNotesApi.getInstance().updateNote(note, new ApiCallback<Note>() {
            @Override
            public void onSuccess(List<Note> models) {
                notesLiveData.setValue(models);
            }

            @Override
            public void onError(int errorCode, String errorMessage) {
                repositoryMessage.setValue(errorMessage);
            }
        });
    }

}
