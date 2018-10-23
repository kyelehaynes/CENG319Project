package com.example.senzerroom;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SenzRoom1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
            super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senz_room1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textView = (TextView)findViewById(R.id.textView);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            String textId = bundle.getString("senzRoom");
            textView.setText(textId);
            textView.setTextColor(Color.RED);
        }
    }

}
