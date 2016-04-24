package br.com.ngccodex.yourtasks.storage.firebase;

import android.content.Context;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.Logger;

/**
 * Created by tg8g on 20/04/16.
 */
public final class FirebaseBaseMain {

    private static Firebase myFirebaseRef;
    final static String firebaseRoot = "https://your-tasks.firebaseio.com/";
    private static String currentUID;
    private static String curNode;
    public static final String EXTRA_CURRENT_USER_NODE = "EXTRA_CURRENT_NODE";
    private FirebaseBaseMain(){
    }

    public static void initFirebase(Context context){
        // Initialize Firebase
        if( myFirebaseRef == null ){
            Firebase.getDefaultConfig().setPersistenceEnabled(true);
            Firebase.setAndroidContext(context);
            Firebase.getDefaultConfig().setLogLevel(Logger.Level.DEBUG);
            myFirebaseRef = new Firebase(firebaseRoot);
        }
    }

    public static Firebase getFirebase(){
        return (myFirebaseRef);
    }

    public static boolean hasUserAuthenticated(){
        AuthData authData = myFirebaseRef.getAuth();
        return (authData != null);
    }

    public static boolean checkUser(String userPassword){
        return (userPassword.length() == 0);
    }

    public static String getCurrentUID(){
        AuthData authData = myFirebaseRef.getAuth();
        if(authData != null) { //User authenticated
            currentUID = authData.getUid();
        } else {
            myFirebaseRef.unauth();
            currentUID = "";
        }
        return currentUID;
    }

    public static Firebase setChild(String childName){
        StringBuilder tempChild = new StringBuilder().append(getCurrentUID()).append("/").append(childName);
        myFirebaseRef = myFirebaseRef.getRoot().child(tempChild.toString());
        return myFirebaseRef;
    }
}

//myFirebaseRef.unauth();
        /*
        if (authData != null) {
            // user authenticated
        } else {
            // no user authenticated
        }
        myFirebaseRef.authWithPassword("raimundopcjr@gmail.com", "raifirebase", new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                // Authenticated successfully with payload authData
                // user is logged in

            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                // Authenticated failed with error firebaseError
                TextView tv1 = (TextView) findViewById(R.id.editText);
                switch (firebaseError.getCode()) {
                    case FirebaseError.USER_DOES_NOT_EXIST:
                        // handle a non existing user
                        tv1.setText("USER_DOES_NOT_EXIST.");
                        break;
                    case FirebaseError.INVALID_PASSWORD:
                        // handle an invalid password
                        tv1.setText("INVALID_PASSWORD.");
                        break;
                    default:
                        // handle other errors
                        tv1.setText(String.format("OTHER: " + firebaseError.getCode()));
                        break;

                }
            }
        });*/