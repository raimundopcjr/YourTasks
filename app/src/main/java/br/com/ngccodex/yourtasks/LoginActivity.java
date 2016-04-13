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
