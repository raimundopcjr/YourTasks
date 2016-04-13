package br.com.ngccodex.yourtasks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Firebase myFirebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myFirebaseRef = new Firebase("https://your-tasks.firebaseio.com/");
        AuthData authData = myFirebaseRef.getAuth();
        myFirebaseRef.child(authData.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String email = snapshot.child("email").getValue(String.class);
                TextView tv = (TextView) findViewById(R.id.editText2);
                tv.setText(email);
                String name = snapshot.child("name").getValue(String.class);
                TextView tv1 = (TextView) findViewById(R.id.editText);
                tv1.setText(name);
            }

            @Override
            public void onCancelled(FirebaseError error) {
            }
        });
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
    }

    public void clickLogout(View view) {
        myFirebaseRef.unauth();
        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);
        // close this activity
        finish();
    }
}
