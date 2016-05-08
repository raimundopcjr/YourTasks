package br.com.ngccodex.yourtasks.storage.firebase;

import android.content.Context;

import com.firebase.client.Firebase;


/**
 * Created by tg8g on 06/05/16.
 */
public class FirebaseRef {

    private Firebase firebaseRef;
    private String curNode;


    public FirebaseRef(Context context){
        FirebaseBaseMain.initFirebase(context.getApplicationContext());
        firebaseRef = FirebaseBaseMain.getFirebase();
    }
    public Firebase getRef() {
        return firebaseRef;
    }

    public void setUID(String uid) {
        FirebaseBaseMain.setUID(uid);
    }

    public boolean hasUserAuthenticated(){
        return (firebaseRef.getAuth() != null);
    }

    public static boolean checkUser(String userPassword){
        // TODO : checar se usuário existe
        return (userPassword.length() == 0);
    }

    public void setChild(String child){
        firebaseRef = FirebaseBaseMain.setChild(child);
    }

}
