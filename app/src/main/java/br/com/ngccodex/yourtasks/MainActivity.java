package br.com.ngccodex.yourtasks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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
        myFirebaseRef.child("3278e6d1-9ba3-4781-a08e-cdabaac1d317").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String email = snapshot.child("email").getValue(String.class);
                TextView tv = (TextView) findViewById(R.id.editText2);
                tv.setText(email);
                String name = snapshot.child("name").getValue(String.class);
                TextView tv1 = (TextView) findViewById(R.id.editText);
                tv1.setText(name);
            }
            @Override public void onCancelled(FirebaseError error) { }
        });
    }
}
