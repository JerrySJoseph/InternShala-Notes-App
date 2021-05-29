package com.jstechnologies.internshalanotesapp.data.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.jstechnologies.usermanagement.UserManagement;

import java.io.Serializable;

/*
-----------------------------------------------------------------
* Note Model:
* This is the schema of a typical note object.
* id: int (Primary Key) -> Unique number
* noteContent : String -> Content of the note
* datetime: long -> for storing current timestamp
* priority: int -> priority of the note on a scale of (1-5)
* isImportant :int -> true if the note is important or otherwise
*-----------------------------------------------------------------
* TableName= data_notes
*
*/
@Entity(tableName = "data_notes")
public class Note implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int id;

    String Notecontent;
    @ColumnInfo(name = "created_at")
    long dateTime;
    int priority;
    boolean isImportant=false;

    String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotecontent() {
        return Notecontent;
    }

    public void setNotecontent(String notecontent) {
        Notecontent = notecontent;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return id==(note.id);
    }

}
