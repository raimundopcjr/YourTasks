package br.com.ngccodex.yourtasks.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import br.com.ngccodex.yourtasks.R;
import br.com.ngccodex.yourtasks.storage.firebase.FirebaseRef;

/**
 * Created by tg8g on 10/04/16.
 */
public class LoginActivity extends Activity {

    FirebaseRef fbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fbRef = new FirebaseRef(this);
    }

    public void btnLoginClick(View view) {
        TextView email = (TextView) findViewById(R.id.editEmail);
        TextView password = (TextView) findViewById(R.id.editPassword);
        TextView userPassword = (TextView) findViewById(R.id.editUserPassword);

        final String strUserPassword = userPassword.getText().toString();

        fbRef.getRef().authWithPassword(email.getText().toString(), password.getText().toString(), new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                // Admin logged
                fbRef.setUID(authData.getUid());
                if(strUserPassword.length() == 0) {
                    Intent i = new Intent(LoginActivity.this, UserListActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    if (fbRef.checkUser(strUserPassword)) { // User exists
                        Intent i = new Intent(LoginActivity.this, UserListActivity.class);
                        startActivity(i);
                        finish();
                    } else { // User dont exists
                        fbRef.getRef().unauth();
                        Toast toast = Toast.makeText(getApplicationContext(), "@string/message_user_logerror", 1);
                        toast.show();
                    }
                }
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                    Toast toast = Toast.makeText(getApplicationContext(), "@string/message_logerror", 1);
                    toast.show();
                }
            });
    }

    public void btnNewUser(View view) {
        Toast toast = Toast.makeText(this, "New User" , 1);
        toast.show();
    }
}

