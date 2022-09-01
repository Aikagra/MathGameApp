package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //splash screen delay and then intent

        Thread thread = new Thread(){

            public void run(){
                try {
                    sleep(2300);

                }
                catch (Exception e){
                    e.printStackTrace();

                }
                finally {
                    Intent intent = new Intent(SplashScreenActivity.this , DashboardActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };thread.start();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}