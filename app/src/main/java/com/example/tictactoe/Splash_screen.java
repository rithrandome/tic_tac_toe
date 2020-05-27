package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash_screen extends AppCompatActivity {
    ImageView i1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        i1 = findViewById(R.id.imageView);
        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        i1.startAnimation(myanim);

        final Intent intent = new Intent(Splash_screen.this,Main_menu.class);
        Thread timer = new Thread(){
            public void run(){
                try {
                    sleep(5000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                finally{
                    startActivity(intent);
                    finish();

                }
            }
        };
        timer.start();

    }
}
