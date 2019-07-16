package com.example.login;

import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.login.base.BaseActivity;
import com.example.login.home.banner.HomeFragmentss;
import com.example.login.wechat.WeChatFragment;
import com.example.login.widget.ButtonNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private ButtonNavigationView mButtonNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        addFragment(getSupportFragmentManager(), HomeFragmentss.class, R.id.main_fragment_container, null);


        mButtonNavigationView = findViewById(R.id.main_bottom_navigation);


        mButtonNavigationView.setOnTabChangedListener(new ButtonNavigationView.OnTabCheckedChangedListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              switch (buttonView.getId()){
                  case R.id.main_button_tab_home:
                      addFragment(getSupportFragmentManager(), HomeFragmentss.class, R.id.main_fragment_container, null);
                      break;

                  case R.id.main_button_tab_knowledge:
                      break;

                      case R.id.main_button_tab_wechat:
                      addFragment(getSupportFragmentManager(), WeChatFragment.class, R.id.main_fragment_container, null);
                      break;
              }
            }
        });





    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

