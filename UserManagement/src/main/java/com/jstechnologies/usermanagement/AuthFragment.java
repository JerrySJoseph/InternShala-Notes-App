package com.jstechnologies.usermanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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

/*Base fragment for authentication. extend this fragment when need to implement authentication flow*/
abstract public class AuthFragment<VM extends ViewModel> extends BaseAuthFragment<VM> {

    private static int RC_SIGN_IN=108; //Request Code

    GoogleSignInAccount account;    //Google Account

    String TAG="User Management".concat(getClass().getName());

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        account= UserManagement.getInstance(this.getContext()).getAccount();
        if(account!=null && !account.isExpired()){
            handleGoogleSignInResult(account);
        }
    }

    //Abstract method to pass auth results to UI
    protected abstract void onSignInSuccess(User user);
    protected abstract void onSignInFailed(String reason);


    //Google Sign In flow
    protected void signInWithGoogle(){

        if(account!=null && !account.isExpired()){
            handleGoogleSignInResult(account);
            return;
        }
            Intent signInIntent = UserManagement.getInstance(this.getContext()).getmGoogleSignInClient().getSignInIntent();
            launcher.launch(signInIntent);

    }

    //handle response from Auth flow
    private void handleGoogleSignInResult(@NonNull GoogleSignInAccount account) {
            User user=new User(account.getId(),account.getDisplayName(),account.getEmail(),account.getPhotoUrl().toString());
            UserManagement.getInstance(this.getContext()).setUser(user);
            onSignInSuccess(user);
    }

    ActivityResultLauncher<Intent> launcher= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode()==Activity.RESULT_OK){
                try {
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(result.getData());
                    handleGoogleSignInResult(task.getResult(ApiException.class));
                }catch (Exception e)
                {
                    Log.w(TAG, "handleSignInResult:error", e);
                    onSignInFailed(e.getMessage());
                }
            }
        }
    });


}
