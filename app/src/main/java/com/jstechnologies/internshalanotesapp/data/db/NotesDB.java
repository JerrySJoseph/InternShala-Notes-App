package com.jstechnologies.internshalanotesapp.data.db;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.jstechnologies.internshalanotesapp.data.models.Note;
import com.jstechnologies.internshalanotesapp.utils.ApiCallback;
import com.jstechnologies.internshalanotesapp.utils.DatabaseExecutor;
import com.jstechnologies.usermanagement.UserManagement;

import java.util.List;

@Database(entities = Note.class ,exportSchema = false, version = 1)
abstract public class NotesDB extends RoomDatabase {

    private static String DB_NAME="notes_app_local_db";
    private static NotesDB mInstance;
    private static Context mContext;
    public static synchronized NotesDB getInstance(Context context){
        if(mInstance==null)
        {
            mContext=context;
            mInstance= Room.databaseBuilder(context.getApplicationContext(),NotesDB.class,DB_NAME).fallbackToDestructiveMigration().build();
        }
        return mInstance;
    }
    protected abstract NotesDao notesDao();

    public void getAll(ApiCallback<Note> mCallBack)
    {
        new DatabaseExecutor().execute(() -> {
            try{
                List<Note> models=notesDao().fetchAllNotes(UserManagement.getInstance(mContext).getUser().getId());
                post(()->mCallBack.onSuccess(models));

            }catch (Exception e)
            {
                post(()->mCallBack.onError(1003,e.getMessage()));
            }
        });
    }

    public void update(Note note,ApiCallback<Note> callback){
        new DatabaseExecutor().execute(() -> {
            try{
                notesDao().updateNote(note);
                getAll(callback);

            }catch (Exception e)
            {
                post(()->callback.onError(1003,e.getMessage()));
            }
        });
    }
    public void insert(Note note,ApiCallback<Note> callback){
        new DatabaseExecutor().execute(() -> {
            try{
                notesDao().insertNote(note);
                getAll(callback);
            }catch (Exception e)
            {
                post(()->callback.onError(1003,e.getMessage()));
            }
        });
    }
    public void delete(int id,ApiCallback<Note> callback){
        new DatabaseExecutor().execute(() -> {
            try{
                notesDao().deleteNote(id);
                getAll(callback);

            }catch (Exception e)
            {
                post(()->callback.onError(1003,e.getMessage()));
            }
        });
    }
    void post(Runnable runnable)
    {
        new Handler(Looper.getMainLooper()).post(runnable);
    }
}
