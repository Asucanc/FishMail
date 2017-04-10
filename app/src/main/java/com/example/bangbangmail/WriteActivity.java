package com.example.bangbangmail;

import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class WriteActivity extends AppCompatActivity implements NavigationView.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_mail);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("写邮件");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        WriteActivity.this.finish();
    }
}
