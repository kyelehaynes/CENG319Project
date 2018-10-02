package com.example.senzerroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SenzRoom1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senz_room1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
