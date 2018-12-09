/*Team Diversity */

package com.example.senzerroom;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SenzSettings extends AppCompatActivity
{
    private EditText text;
    private Button myButton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senz_settings);
        text =  findViewById(R.id.numberText);
        myButton =  findViewById(R.id.submitButton);
        this.setTitle("Senz Settings");


            myButton.setEnabled(true);
            myButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (text.getText().toString().equals(""))
                    {
                        Toast.makeText(SenzSettings.this, "Please Enter number of rooms", Toast.LENGTH_SHORT).show();
                    } else
                    {
                        int roomAmount = Integer.parseInt(text.getText().toString());
                        final Intent intent = new Intent(SenzSettings.this, SenzHome.class);
                        intent.putExtra("roomAmount", roomAmount);
                        startActivityForResult(intent, 1);

                        SharedPreferences sp = getSharedPreferences("shared", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putInt("roomAmount", roomAmount);
                        editor.putInt("myVal", 10);
                        editor.commit();
                        //finish();
                    }
                }
            });

    }


}
    /*final String roomAmount = text.getText().toString();
    final TextView myText = (TextView) findViewById(R.id.myText);*/
