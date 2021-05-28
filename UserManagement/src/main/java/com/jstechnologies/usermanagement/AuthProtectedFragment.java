package com.jstechnologies.usermanagement;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

abstract public class AuthProtectedFragment<VM extends ViewModel> extends BaseAuthFragment {

    protected abstract void onSignOut();

    protected void signOut(){
        UserManagement.getInstance(this.getContext()).signOut(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                onSignOut();
            }
        });
    }
}
