package com.jstechnologies.internshalanotesapp.utils;

public interface RepositoryCallback {
    void onSuccess(Object result);
    void onFailure(String reason);
}
