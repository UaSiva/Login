package com.example.vijai.logvolley;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class MainActivity extends AppCompatActivity {
    @BindView(R.id.login_name) EditText login_name;
    @BindView(R.id.login_password) EditText login_password;
    @BindView(R.id.register) TextView textView;
    @BindView(R.id.bn_login) Button bn_login;

    @OnClick(R.id.bn_login)
    public void login(){
        if(login_name.getText().toString().equals("")||login_password.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(),"Fields cannot be empty",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.register)
    public void submit(){
        startActivity(new Intent(MainActivity.this,Register.class ) );

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        submit();
        login();

    }

}
