package com.jstechnologies.internshalanotesapp.data.api;

import android.content.Context;

import com.jstechnologies.internshalanotesapp.MyApp;
import com.jstechnologies.internshalanotesapp.data.db.NotesDB;
import com.jstechnologies.internshalanotesapp.data.models.Note;
import com.jstechnologies.internshalanotesapp.utils.ApiCallback;

public class LocalNotesApi {
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

    public void fetchAll(ApiCallback<Note>callback){
        NotesDB.getInstance(mContext).getAll(callback);
    }
    public void deleteNote(int id,ApiCallback<Note>callback){
        NotesDB.getInstance(mContext).delete(id,callback);
    }
    public void updateNote(Note note,ApiCallback<Note>callback){
        NotesDB.getInstance(mContext).update(note,callback);
    }
    public void insert(Note note,ApiCallback<Note>callback){
        NotesDB.getInstance(mContext).insert(note,callback);
    }
}
