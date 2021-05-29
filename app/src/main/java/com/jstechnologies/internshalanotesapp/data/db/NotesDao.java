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

/*Data Access Object for implementing CRUD operation in Database*/
@Dao
public interface NotesDao {

    /*Insert a new Note and replace if exists with same id*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(Note note);

    /*List all notes of an existing user*/
    @Query("SELECT * FROM data_notes WHERE author=:author ORDER BY created_at desc")
    List<Note> fetchAllNotes(String author);

    /*Gets a single note of given id*/
    @Query("SELECT * FROM data_notes WHERE id =:noteId")
    Note getNote(int noteId);

    /*update an existing node*/
    @Update
    void updateNote(Note note);

    /*delete a node which matches with given node*/
    @Delete
    void deleteNote(Note note);

    /*Delete all notes from database*/
    @Query("DELETE FROM data_notes")
    void deleteAllNote();

    /*Deletes a node with given id*/
    @Query("DELETE FROM data_notes WHERE id =:id")
    void deleteNote(int id);
}
