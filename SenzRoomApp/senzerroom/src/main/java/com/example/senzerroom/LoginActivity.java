package com.example.senzerroom;

/*Team Diversity */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity
{
    EditText Name;
    EditText Password;
    TextView Info;
    Button Login;
    int count = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Name = (EditText) findViewById(R.id.nameEntry);
        Password = (EditText) findViewById(R.id.passEntry);
        Info = (TextView) findViewById(R.id.attempts);
        Login = (Button) findViewById(R.id.loginButton);
        Info.setText("Number of Attempts: " + String.valueOf(count));
        Login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(LoginActivity.this, SenzHome.class);
                startActivity(intent);
            }
        });

        Button register = (Button) findViewById(R.id.registerButton);
        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent registerPage = new Intent(LoginActivity.this, SenzRegister.class);
                startActivity(registerPage);
            }
        });
    }

   /* public void validate(String userName, String userPassword)
    {
        if((userName == "kogul".trim()) && (userPassword=="kogul".trim()))
        {
            Intent intent = new Intent(LoginActivity.this, SenzHome.class);
            startActivity(intent);
        }
        else
        {
            count--;
            Info.setText("Number of Attempts: " + String.valueOf(count));
            if(count == 0)
            {
                Login.setEnabled(false);
            }
        }
    }*/
}
