package com.example.pos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import static java.lang.Thread.sleep;

public class Splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ImageView im=findViewById(R.id.spash_screen_image);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent it=new Intent(Splash_screen.this,Sign_in.class);
                it.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                finish();
                startActivity(it);
                Splash_screen.this.finish();
            }
        },3000);
    }


    @Override
    public void onBackPressed() {
        Splash_screen.this.finish();
    }
}
