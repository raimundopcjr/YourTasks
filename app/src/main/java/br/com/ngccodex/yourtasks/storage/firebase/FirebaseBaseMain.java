package br.com.ngccodex.yourtasks.storage.firebase;

import android.app.Application;
import android.content.Context;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.Logger;

/**
 * Created by tg8g on 20/04/16.
 */
public final class FirebaseBaseMain {

    final static String firebaseRoot = "https://your-tasks.firebaseio.com/";
    private static String currentUID;
    public static final String EXTRA_CURRENT_USER_NODE = "EXTRA_CURRENT_NODE";
    private static Context appContext;

    private FirebaseBaseMain(){
    }

    public static void initFirebase(Context context){
        // Config Firebase
        if(appContext == null) {
            appContext = context;
            Firebase.setAndroidContext(context);
            Firebase.getDefaultConfig().setPersistenceEnabled(true);
            Firebase.getDefaultConfig().setLogLevel(Logger.Level.DEBUG);
        }
    }

    public static Firebase getFirebase(){
        return new Firebase(firebaseRoot);
    }

    public static Firebase setChild(String child){
        if(!currentUID.isEmpty()) {
            //fbRef = fbRef.getRoot().child(new StringBuilder().append(currentUID).append("/").append(child).toString());
            //return fbRef;
            return new Firebase(firebaseRoot).child(new StringBuilder().append(currentUID).append("/").append(child).toString());
        } else {
            return null;
        }
    }

    public static void setUID(String uid) {
        currentUID = uid;
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