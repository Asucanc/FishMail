package com.example.bangbangmail;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class AddressActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("通讯录");
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
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
                intent=new Intent(AddressActivity.this,ReceiveActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_start_mail:
                intent = new Intent(AddressActivity.this,StartMailActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_write_mail:
                intent= new Intent(AddressActivity.this,WriteActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_delete_mail:
                intent = new Intent(AddressActivity.this,AlreadyDeleteActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_trash_mail:
                intent = new Intent(AddressActivity.this,TrashActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_send:
                intent = new Intent(AddressActivity.this,SendActivity.class);
                break;
            case R.id.nav_address:
                intent = new Intent(AddressActivity.this,AddressActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_adversaria:
                intent = new Intent(AddressActivity.this,DraftActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_write_thing:
                intent = new Intent(AddressActivity.this,AdversariaActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
