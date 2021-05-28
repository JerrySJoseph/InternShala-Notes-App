package com.jstechnologies.internshalanotesapp.data.models;

import java.util.Objects;

public class Note {
    String userID;
    String Notecontent;
    long dateTime;
    int priority;
    boolean isImportant=false;

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

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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
        return userID.equals(note.userID);
    }

}
