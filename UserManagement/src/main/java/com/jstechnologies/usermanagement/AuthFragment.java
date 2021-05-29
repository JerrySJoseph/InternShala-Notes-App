package com.jstechnologies.usermanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

abstract public class AuthFragment<VM extends ViewModel> extends BaseAuthFragment<VM> {



    private static int RC_SIGN_IN=108;

    GoogleSignInAccount account;

    String TAG="User Management".concat(getClass().getName());

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        account= UserManagement.getInstance(this.getContext()).getAccount();
        if(account!=null && !account.isExpired()){
            handleGoogleSignInResult(account);
        }

    }

    protected abstract void onSignInSuccess(User user);
    protected abstract void onSignInFailed(String reason);


    //Google Sign In flow
    protected void signInWithGoogle(){

        if(account!=null && !account.isExpired()){
            handleGoogleSignInResult(account);
            return;
        }
            Intent signInIntent = UserManagement.getInstance(this.getContext()).getmGoogleSignInClient().getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);

    }
    private void handleGoogleSignInResult(@NonNull GoogleSignInAccount account) {
            User user=new User(account.getId(),account.getDisplayName(),account.getEmail(),account.getPhotoUrl().toString());
            UserManagement.getInstance(this.getContext()).setUser(user);
            onSignInSuccess(user);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == RC_SIGN_IN && resultCode== Activity.RESULT_OK) {
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                handleGoogleSignInResult(task.getResult(ApiException.class));
            }
        }catch (Exception e)
        {
            Log.w(TAG, "handleSignInResult:error", e);
            onSignInFailed(e.getMessage());
        }

    }
}
