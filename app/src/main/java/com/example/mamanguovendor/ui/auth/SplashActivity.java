package com.example.mamanguovendor.ui.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.mamanguovendor.R;
import com.example.mamanguovendor.util.PrefManager;

public class SplashActivity extends AppCompatActivity {

    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Check for first time launch
        prefManager = new PrefManager(this);
        if (prefManager.isFirstTimeLaunch()){
            setContentView(R.layout.activity_splash);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    prefManager.setFirstTimeLaunch(false);
                    launchLoginScreen();
                }
            }, 3000);
        }else{
            launchLoginScreen();
        }


    }

    private void launchLoginScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
        finish();
    }
}
