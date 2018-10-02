package com.example.senzerroom;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class SenzHome extends AppCompatActivity implements View.OnClickListener {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.senzmenu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senz_home);

        ImageButton room1 =(ImageButton) findViewById(R.id.imageView2);
        ImageButton room2 =(ImageButton) findViewById(R.id.imageView3);
        ImageButton room3 =(ImageButton) findViewById(R.id.imageView4);
        TextView text1 = (TextView) findViewById(R.id.textView);
        TextView text2 = (TextView) findViewById(R.id.textView2);
        TextView text3 = (TextView) findViewById(R.id.textView3);
        room1.setOnClickListener(this);
        room2.setOnClickListener(this);
        room3.setOnClickListener(this);
        text1.setOnClickListener(this);
        text2.setOnClickListener(this);
        text3.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.admin:
                            Intent intent = new Intent(SenzHome.this,SenzAdminLoginActivity.class);
                            startActivity(intent);
                            return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onClick(View v) {

        Intent intent1 = new Intent(SenzHome.this, SenzRoom1.class);
        Intent intent2 = new Intent(SenzHome.this, SenzRoom2.class);
        Intent intent3 = new Intent(SenzHome.this, SenzRoom3.class);


        switch(v.getId()){
            case R.id.textView:
                                startActivity(intent1);
                                break;
            case R.id.textView2:
                                startActivity(intent2);
                                break;
            case R.id.textView3:
                                startActivity(intent3);
                                break;
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
