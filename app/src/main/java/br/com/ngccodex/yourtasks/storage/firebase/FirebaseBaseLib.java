package br.com.ngccodex.yourtasks.storage.firebase;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

/**
 * Created by tg8g on 20/04/16.
 */
public final class FirebaseBaseLib {

    private static Firebase myFirebaseRef;
    final static String firebaseRoot = "https://your-tasks.firebaseio.com/";

    private FirebaseBaseLib(){
    }

    public static void initFirebase(){
        // Initialize Firebase
        if( myFirebaseRef == null ){
            myFirebaseRef = new Firebase(firebaseRoot);
            Firebase.getDefaultConfig().setPersistenceEnabled(true);
        }
        return;
    }

    public static Firebase getFirebase(){
        return (myFirebaseRef);
    }

    public static boolean hasUserAuthenticated(){
        AuthData authData = myFirebaseRef.getAuth();
        return authData != null;
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