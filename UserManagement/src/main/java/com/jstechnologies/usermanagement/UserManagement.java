package com.jstechnologies.usermanagement;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


/*Main EntryPoint to this module. This is a singleton class created to implement single line sign in and signout for users*/
public class UserManagement {

    private Context mContext;
    private static UserManagement mInstance;
    private static String CLIENT_ID,CLIENT_SECRET;
    private User user=null;
    GoogleSignInOptions gso =null;
    GoogleSignInClient mGoogleSignInClient;

    //Initialize the UserManagement Engine by pasing clientID and ClientSecret provided by Google
    public static void Init(String clientID, String clientSecret){
        CLIENT_ID=clientID;
        CLIENT_SECRET=clientSecret;
    }

    //Default constructor, should not be used to create object of this class
    private UserManagement(Context context){
        this.mContext=context;
    }

    //Right method to spawn object of this class
    public static synchronized UserManagement getInstance(Context context){
        if(CLIENT_ID==null || CLIENT_SECRET==null)
            throw new NullPointerException("Make sure you have initialized User Management System using init() method");
        if(mInstance==null)
            mInstance=new UserManagement(context);
        return mInstance;
    }

    public String getClientId() {
        return CLIENT_ID;
    }

    public String getClientSecret() {
        return CLIENT_SECRET;
    }

    //create a GoogleSignInClient object if not exists
    public GoogleSignInClient getmGoogleSignInClient() {
        if(mGoogleSignInClient==null)
            mGoogleSignInClient=GoogleSignIn.getClient(mContext,getGso());
        return mGoogleSignInClient;
    }

    public GoogleSignInAccount getAccount() {
        return GoogleSignIn.getLastSignedInAccount(mContext);
    }

    //GSO - Google Sign In Options specifies all the set of data we require ( Email, phone, name, etc.)
    public GoogleSignInOptions getGso() {
        String clientID=UserManagement.getInstance(mContext).getClientId();

        if(clientID==null || clientID.isEmpty())
            throw new IllegalArgumentException("Invalid Google Client ID provided. Please set Google Client ID in Application class where you initiate JSCloudApp");
        if(gso==null)
            gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(clientID)
                .requestEmail()
                .build();
        return gso;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //Signout the user
    public void signOut(OnCompleteListener<Void> callback){
        getmGoogleSignInClient().signOut().addOnCompleteListener(callback);
    }
}
