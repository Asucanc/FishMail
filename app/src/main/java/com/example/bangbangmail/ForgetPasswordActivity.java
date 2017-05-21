package com.example.bangbangmail;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class ForgetPasswordActivity extends AppCompatActivity implements NavigationView.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("找回密码");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(this);
        Button find_password = (Button)findViewById(R.id.find_password_button);
        find_password.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.find_password_button:
                Intent intent = new Intent(ForgetPasswordActivity.this,ResetPassword.class);
                startActivity(intent);
                break;
            case R.id.toolbar:
                ForgetPasswordActivity.this.finish();
                break;

        }
    }

}
