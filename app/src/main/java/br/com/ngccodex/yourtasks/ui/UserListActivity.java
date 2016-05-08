package br.com.ngccodex.yourtasks.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.FirebaseListAdapter;

import br.com.ngccodex.yourtasks.R;
import br.com.ngccodex.yourtasks.model.User;
import br.com.ngccodex.yourtasks.storage.firebase.FirebaseRef;

public class UserListActivity extends AppCompatActivity {

    FirebaseRef fbRef;
    FirebaseListAdapter<User> mUserListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fbRef = new FirebaseRef(this);
        fbRef.setChild("user");
        if(fbRef.getRef() != null) {
            ListView listView = (ListView) findViewById(R.id.listView);

            mUserListAdapter = new FirebaseListAdapter<User>(this, User.class, R.layout.userlist_user, fbRef.getRef()) {
                @Override
                protected void populateView(View v, User objUser, int position) {
                    //super.populateView(v, object );
                    TextView textCode = (TextView) v.findViewById(R.id.textCode);
                    TextView textName = (TextView) v.findViewById(R.id.textName);
                    TextView textTaskList = (TextView) v.findViewById(R.id.textTaskList);
                    TextView textTotal = (TextView) v.findViewById(R.id.textTotal);
                    textCode.setText(objUser.getCode());
                    textName.setText(objUser.getName());
                    textTaskList.setText(objUser.getTasklist());
                    textTotal.setText(String.valueOf(objUser.getTotal()));
                }
            };
            listView.setAdapter(mUserListAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Adapter tempAdapter = parent.getAdapter();
                    User tempUser = (User) tempAdapter.getItem(position);
                    String tempNode = tempUser.getNode();

                    Intent i = new Intent(UserListActivity.this, UserViewActivity.class);
                    //i.putExtra(FirebaseBaseMain.EXTRA_CURRENT_USER_NODE, tempNode.toString());
                    startActivity(i);
                }
            });
        } else {
            Intent i = new Intent(UserListActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }
    }

}
