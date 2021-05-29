package com.jstechnologies.internshalanotesapp.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.jstechnologies.internshalanotesapp.data.api.LocalNotesApi;
import com.jstechnologies.internshalanotesapp.data.models.Note;
import com.jstechnologies.internshalanotesapp.utils.ApiCallback;

import java.util.List;

/*
* NotesRepository acts as a primary data-source for UI components.
* This provides observable LiveData which can be easily updated to
* push the new data whenever necessary. This controls the data-flow
* from Local and Network data-source to UI components thus introducing
* a level of abstraction.
* */
public class NotesRepository {

    private static NotesRepository mInstance;
    private MutableLiveData<String> repositoryMessage;  //LiveData for pushing new messages
    private MutableLiveData<List<Note>> notesLiveData;  //LiveData for pushing new notes

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

    //Push a custom message
    public void pushMessage(String message){
        this.repositoryMessage.setValue(message);
    }

    //get all notes
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

    //insert
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

    //delete
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

    //update
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
