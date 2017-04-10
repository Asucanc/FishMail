package com.example.bangbangmail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class SendActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("已发送");
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.send, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent;
        switch (id){
            case R.id.nav_rec_mail:
                intent=new Intent(SendActivity.this,ReceiveActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_start_mail:
                intent = new Intent(SendActivity.this,StartMailActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_write_mail:
                intent= new Intent(SendActivity.this,WriteActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_delete_mail:
                intent = new Intent(SendActivity.this,AlreadyDeleteActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_trash_mail:
                intent = new Intent(SendActivity.this,TrashActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_send:
                intent = new Intent(SendActivity.this,SendActivity.class);
                break;
            case R.id.nav_address:
                intent = new Intent(SendActivity.this,AddressActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_adversaria:
                intent = new Intent(SendActivity.this,DraftActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_write_thing:
                intent = new Intent(SendActivity.this,AdversariaActivity.class);
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
