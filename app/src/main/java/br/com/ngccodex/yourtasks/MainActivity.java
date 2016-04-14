package br.com.ngccodex.yourtasks;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

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

    }

    public void clickLogout(View view) {
        myFirebaseRef.unauth();
        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);
        // close this activity
        finish();
    }
}
