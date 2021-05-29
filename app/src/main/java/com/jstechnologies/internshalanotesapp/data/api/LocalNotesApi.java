package com.jstechnologies.internshalanotesapp.data.api;

import android.content.Context;

import com.jstechnologies.internshalanotesapp.MyApp;
import com.jstechnologies.internshalanotesapp.data.db.NotesDB;
import com.jstechnologies.internshalanotesapp.data.models.Note;
import com.jstechnologies.internshalanotesapp.utils.ApiCallback;

/*
* LocalNotesApi, acts as an API class to fetch Data from Local
* Database using Room persistance library.
* It uses a singleton patter to ensure only one instance is
* created throughout the application lifecycle.
*
* Reference to Only Application context, So no potential memory leaks
* */
public class LocalNotesApi {

    /*Singleton Instance*/
    private static LocalNotesApi mInstance;
    Context mContext;

    public static synchronized LocalNotesApi getInstance(){
        if(mInstance==null)
            mInstance=new LocalNotesApi(MyApp.getInstance().getApplicationContext());
        return mInstance;
    }
    LocalNotesApi(Context context)
    {
        this.mContext=context;
    }

    /*Fetch all notes available in Database*/
    public void fetchAll(ApiCallback<Note>callback){
        NotesDB.getInstance(mContext).getAll(callback);
    }

    /*Delete a specific note by id*/
    public void deleteNote(int id,ApiCallback<Note>callback){
        NotesDB.getInstance(mContext).delete(id,callback);
    }

    /*Update an existing note*/
    public void updateNote(Note note,ApiCallback<Note>callback){
        NotesDB.getInstance(mContext).update(note,callback);
    }

    /*Insert a new note to the database*/
    public void insert(Note note,ApiCallback<Note>callback){
        NotesDB.getInstance(mContext).insert(note,callback);
    }
}
