package com.example.bangbangmail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MailDetail extends AppCompatActivity implements NavigationView.OnClickListener {
    private static final String TAG = "MailDetail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mail_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("返回");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(this);
        Intent intent = getIntent();
        String sender = intent.getStringExtra("FROM");
        String receiver = intent.getStringExtra("TO");
        String subject = intent.getStringExtra("SUBJECT");
        String time = intent.getStringExtra("DATE");
        String content = intent.getStringExtra("TEXT");
        Log.d(TAG, "onCreate: " + sender);
        TextView senderText = (TextView) findViewById(R.id.sender);
        TextView receiverText = (TextView) findViewById(R.id.receiver);
        TextView subjectText = (TextView) findViewById(R.id.subject);
        TextView timeText = (TextView) findViewById(R.id.rcv_time);
        TextView contentText = (TextView) findViewById(R.id.content);
        senderText.setText(sender);
        receiverText.setText(receiver);
        subjectText.setText(subject);
        timeText.setText(time);
        contentText.setText(content);
    }

    @Override
    public void onClick(View v) {
        MailDetail.this.finish();
    }

}
