package com.jstechnologies.usermanagement;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/*Base fragment for protected Fragment. extend this fragment when need to implement authentication protection*/
abstract public class AuthProtectedFragment<VM extends ViewModel> extends BaseAuthFragment<VM> {

    protected abstract void onSignOut(); //called after successful signout

    //global sign out method to sign out a user
    protected void signOut(){
        UserManagement.getInstance(this.getContext()).signOut(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                onSignOut();
            }
        });
    }
}
