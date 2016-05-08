package br.com.ngccodex.yourtasks.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import br.com.ngccodex.yourtasks.R;
import br.com.ngccodex.yourtasks.storage.firebase.FirebaseRef;

/**
 * Created by tg8g on 10/04/16.
 */
public class SplashActivity extends Activity {

    private FirebaseRef fbRef;

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        fbRef = new FirebaseRef(this);

        if (fbRef.hasUserAuthenticated()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashActivity.this, UserListActivity.class);
                    startActivity(i);
                    finish();
                }
            }, SPLASH_TIME_OUT);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }


    }
}
