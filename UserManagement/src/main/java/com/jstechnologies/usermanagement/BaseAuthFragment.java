package com.jstechnologies.usermanagement;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

/*Base fragment for authentication and auth protection. Implements some basic Viewmodel creation*/
abstract public class BaseAuthFragment<VM extends ViewModel> extends Fragment {

    protected VM viewmodel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewmodel=createViewModel();
        observe();
    }

    abstract protected VM createViewModel();

    abstract protected void observe();

    protected void showToast(String message){
        Toast.makeText(this.getContext(),message,Toast.LENGTH_SHORT).show();
    }

    protected void navigateTo(@IdRes int fragmaentContainerID, Fragment fragment){
        this.getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(fragmaentContainerID,fragment)
                .commit();
    }
    protected void navigateToWithBackStack(@IdRes int fragmaentContainerID, Fragment fragment){
        this.getActivity().getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(fragment.getClass().getName())
                .replace(fragmaentContainerID,fragment)
                .commit();
    }
}
