package id.endang.manajemensampah.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import id.endang.manajemensampah.R;


public class SplashActivity extends AppCompatActivity {


    private static final int SPLASH_TIME = 2000;


    private Handler handler = new Handler(Looper.getMainLooper());


    private Runnable runnable = new Runnable() {
        @Override
        public void run() {


            Intent intent = new Intent(
                    SplashActivity.this,
                    LoginActivity.class
            );

            intent.setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK
            );


            startActivity(intent);


            overridePendingTransition(
                    android.R.anim.fade_in,
                    android.R.anim.fade_out
            );


            finish();


        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(
                R.layout.activity_splash
        );

        handler.postDelayed(
                runnable,
                SPLASH_TIME
        );


    }



    @Override
    protected void onDestroy() {

        super.onDestroy();

        handler.removeCallbacks(runnable);

    }




    @Override
    public void onBackPressed() {

    }


}