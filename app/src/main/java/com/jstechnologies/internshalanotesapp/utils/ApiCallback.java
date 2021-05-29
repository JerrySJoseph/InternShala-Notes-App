package com.jstechnologies.internshalanotesapp.utils;

import java.util.List;

/*Base interface to implemetn all api callbacks*/
public interface ApiCallback<T> {
    void onSuccess(List<T> models);  //invoked on success
    void onError(int errorCode,String errorMessage);  //invoked on Error
}
