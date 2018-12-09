package com.example.senzerroom;

/*Team Diversity */

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity
{
    private EditText name;
    private EditText password;
    private Button loginBtn;
    private Button logoutBtn;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;


    int count = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        name = (EditText) findViewById(R.id.nameEntry);
        password = (EditText) findViewById(R.id.passEntry);
        loginBtn = (Button) findViewById(R.id.loginButton);
		logoutBtn = (Button) findViewById(R.id.logoutBtn);
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();


        loginBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!name.getText().toString().equals("") && (!password.getText().toString().equals("")))
                {
                    UserLogin(name.getText().toString().trim(),password.getText().toString().trim());
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Please Enter Login Info",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v)
			{
				if(currentUser != null){
                    Toast.makeText(LoginActivity.this, "User Has Been Logged Out",
                            Toast.LENGTH_SHORT).show();
					auth.signOut();
				} else{
					Toast.makeText(LoginActivity.this, "No User Logged In!",
							Toast.LENGTH_SHORT).show();
				}
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

    public void UserLogin(String email,String pass){

     //   name = (EditText) findViewById(R.id.nameEntry);
     //   password = (EditText) findViewById(R.id.passEntry);

        //String email = name.getText().toString().trim();
        //String pass = password.getText().toString().trim();
        auth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            currentUser = auth.getCurrentUser();
                            finish();
                            startActivity(new Intent(getApplicationContext(),
                                    SenzHome.class));
                        }else {
                            Toast.makeText(LoginActivity.this, "Login Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
