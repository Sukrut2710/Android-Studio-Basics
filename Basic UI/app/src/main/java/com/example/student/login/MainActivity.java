package com.example.student.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText userName;
    private EditText password;
    private TextView info;
    private Button login;
    private int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText)findViewById(R.id.ed);
        password = (EditText)findViewById(R.id.etPassword);
        info = (TextView)findViewById(R.id.tvinfo);
        login = (Button)findViewById(R.id.btnLogin);
        info.setText("No fo attempts remaining : 3");

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                validate(userName.getText().toString(),password.getText().toString());
            }
        });

    }

    private void validate(String userName, String userPassword)
    {
        if(userName.equals("admin") && userPassword.equals("admin123"))
        {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }
        else
        {
            counter--;
            info.setText("No of attempts remaining : "+String.valueOf(counter));
            if(counter == 0)
                login.setEnabled(false);
        }
    }
}
