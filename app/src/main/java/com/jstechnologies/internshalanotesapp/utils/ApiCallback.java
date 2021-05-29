package com.jstechnologies.internshalanotesapp.utils;

import java.util.List;

public interface ApiCallback<T> {
    void onSuccess(List<T> models);
    void onError(int errorCode,String errorMessage);
}
