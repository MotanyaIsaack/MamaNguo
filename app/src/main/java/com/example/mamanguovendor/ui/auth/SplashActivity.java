package com.example.mamanguovendor.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mamanguovendor.R;
import com.example.mamanguovendor.ui.components.Navigation_activity;
import com.example.mamanguovendor.util.PreferenceUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Check for first time launch
        launchLoginScreen();
    }

    private void launchLoginScreen() {
        String token = PreferenceUtils.getUserToken(this);
        if (token != null) {
            Intent intent = new Intent(this, Navigation_activity.class);
            startActivity(intent);
        } else {
            setContentView(R.layout.activity_splash);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }
            }, 3000);

        }
    }
}
