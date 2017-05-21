package com.example.bangbangmail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.bangbangmail.Model.Mail;
import com.example.bangbangmail.Util.MailAdapter;

import java.util.ArrayList;
import java.util.List;

public class ReceiveActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private MailAdapter adapter;

    private List<Mail> mailList = new ArrayList<>();

    private static final String TAG = "ReceiveActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("收件箱");
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        LinearLayout mianLayout = (LinearLayout) findViewById(R.id.main_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //加载收件箱的布局

        initMails();
        LinearLayout layout = (LinearLayout) getLayoutInflater().inflate(
                R.layout.activity_receive, null).findViewById(R.id.recv_layout);
        RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.recycler_view_recv);
        Log.d(TAG, "onCreate: " + mailList.isEmpty());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MailAdapter(mailList);
        recyclerView.setAdapter(adapter);
        mianLayout.removeAllViews();
        mianLayout.addView(layout);
    }
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        switch (id){
            case R.id.nav_rec_mail:
                intent=new Intent(ReceiveActivity.this,ReceiveActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_start_mail:
                intent = new Intent(ReceiveActivity.this,StartMailActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_write_mail:
                intent= new Intent(ReceiveActivity.this,WriteActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_delete_mail:
                intent = new Intent(ReceiveActivity.this,AlreadyDeleteActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_trash_mail:
                intent = new Intent(ReceiveActivity.this,TrashActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_send:
                intent = new Intent(ReceiveActivity.this,SendActivity.class);
                break;
            case R.id.nav_address:
                intent = new Intent(ReceiveActivity.this,AddressActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_adversaria:
                intent = new Intent(ReceiveActivity.this,DraftActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initMails(){
        for (int i = 0; i < 10; i++){
            Mail mail = new Mail("0001","kenshin@fishmial.com","351807993@qq.com",
                    "2017-5-12 12:21","这是邮件主题","0",false,"1","1","0",
                    "这是邮件正文，此邮件为鱼邮的测试邮件正文，今天是星期天，下午有实验，马上要吃饭了");
            mailList.add(mail);
        }
    }
}
