package com.example.senzerroom;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;

public class SenzHome extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senz_home);

        ImageView room1 =(ImageView) findViewById(R.id.imageView2);
        ImageView room2 =(ImageView) findViewById(R.id.imageView3);
        ImageView room3 =(ImageView) findViewById(R.id.imageView4);

        room1.setOnClickListener(this);
        room2.setOnClickListener(this);
        room3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent1 = new Intent(SenzHome.this, SenzRoom1.class);
        Intent intent2 = new Intent(SenzHome.this, SenzRoom2.class);
        Intent intent3 = new Intent(SenzHome.this, SenzRoom3.class);


        switch(v.getId()){

            case R.id.imageView2:
                startActivity(intent1);
                break;
            case R.id.imageView3:
                startActivity(intent2);
                break;

            case R.id.imageView4:
                startActivity(intent3);
                break;
        }
    }
}
