package com.jstechnologies.internshalanotesapp.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.jstechnologies.internshalanotesapp.data.models.Note;

import java.util.List;

@Dao
public interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(Note note);


    @Query("SELECT * FROM data_notes WHERE author=:author ORDER BY created_at desc")
    List<Note> fetchAllNotes(String author);


    @Query("SELECT * FROM data_notes WHERE id =:noteId")
    Note getNote(int noteId);


    @Update
    void updateNote(Note note);


    @Delete
    void deleteNote(Note note);

    @Query("DELETE FROM data_notes")
    void deleteAllNote();

    @Query("DELETE FROM data_notes WHERE id =:id")
    void deleteNote(int id);
}
