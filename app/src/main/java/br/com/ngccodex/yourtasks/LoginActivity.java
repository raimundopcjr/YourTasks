package br.com.ngccodex.yourtasks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

/**
 * Created by tg8g on 10/04/16.
 */
public class LoginActivity extends Activity {
    Firebase myFirebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myFirebaseRef = new Firebase("https://your-tasks.firebaseio.com/");

    }

    public void btnLoginClick(View view) {
        TextView email = (TextView) findViewById(R.id.editEmail);
        TextView password = (TextView) findViewById(R.id.editPassword);
        myFirebaseRef.authWithPassword(email.getText().toString(), password.getText().toString(), new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                // close this activity
                finish();
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                Toast toast = Toast.makeText(getApplicationContext(), "Erro Login" , 1);
                toast.show();
            }
        });
    }

    public void btnNewUser(View view) {
        Toast toast = Toast.makeText(this, "New User" , 1);
        toast.show();
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