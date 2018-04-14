package com.example.hispeed.calculatoroil;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class SplashScreenActivity extends AppCompatActivity {

    Handler handler;
    Runnable runnable;
    long delay;
    long time = 3500L;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                //  if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

                Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                startActivity(intent);

                //   }

            }
        };

        ImageView img_loading = (ImageView) findViewById(R.id.img_loading);
        img_loading.setBackgroundResource(R.drawable.loading_screen);
        AnimationDrawable animationDrawable = (AnimationDrawable) img_loading.getBackground();
        animationDrawable.start();


    }

    public void onResume() {
        super.onResume();
        delay = time;
        handler.postDelayed(runnable, delay);
        time = System.currentTimeMillis();
    }

    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
        time = delay - (System.currentTimeMillis() - time);
    }

}
