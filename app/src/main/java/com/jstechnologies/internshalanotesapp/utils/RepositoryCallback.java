package com.jstechnologies.internshalanotesapp.utils;

/*Base interface to implement all repository callbacks*/
public interface RepositoryCallback {
    void onSuccess(Object result);
    void onFailure(String reason);
}
