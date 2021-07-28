package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

public class  SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIME=2000;
    Animation topAnim,botAnim;
    ImageView imageView;
    TextView name,sub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_anim);
        botAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_anim);
        name=findViewById(R.id.splashName);
        sub=findViewById(R.id.splashSub);
        name.setAnimation(topAnim);
        sub.setAnimation(botAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainPage.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME);

    }
}