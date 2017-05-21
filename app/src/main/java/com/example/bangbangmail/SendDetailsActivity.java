package com.example.bangbangmail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class SendDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_details);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("收件箱");
        setSupportActionBar(toolbar);
        //initial paper
        TextView sender = (TextView)findViewById(R.id.textview_receiver);
        sender.setText("发件人：");
        TextView receiver = (TextView)findViewById(R.id.textview_sender);
        receiver.setText("收件人：");
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar)findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBarBackgroundColor(R.color.colorWhite);
        bottomNavigationBar.setActiveColor(R.color.colorPrimary);
        bottomNavigationBar.setInActiveColor(R.color.colorAccent);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.start ,"星标"))
                .addItem(new BottomNavigationItem(R.mipmap.trash,"删除"))
                .addItem(new BottomNavigationItem(R.mipmap.transmit,"转发"))
                .addItem(new BottomNavigationItem(R.mipmap.write1,"回复")).initialise();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.send_menu,menu);
        return true;
    }
}
