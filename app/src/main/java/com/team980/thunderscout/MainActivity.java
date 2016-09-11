package com.team980.thunderscout;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.team980.thunderscout.bluetooth.BluetoothServerFragment;
import com.team980.thunderscout.info.LocalStorageFragment;
import com.team980.thunderscout.match.MatchScoutFragment;
import com.team980.thunderscout.preferences.SettingsActivity;
import com.team980.thunderscout.sheets.LinkedSheetsFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static String INTENT_FLAG_SHOWN_FRAGMENT = "SHOWN_FRAGMENT";
    public static int INTENT_FLAGS_MATCH_SCOUT = 0;
    public static int INTENT_FLAGS_BT_SERVER = 1;
    public static int INTENT_FLAGS_LINKED_SHEETS = 2;
    public static int INTENT_FLAGS_LOCAL_STORAGE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        int shownFragment = getIntent().getIntExtra(INTENT_FLAG_SHOWN_FRAGMENT, INTENT_FLAGS_MATCH_SCOUT);

        Fragment fragment;

        switch (shownFragment) {
            case 0: //INTENT_FLAGS_MATCH_SCOUT
                navigationView.setCheckedItem(R.id.nav_match_scout);
                fragment = new MatchScoutFragment();
                break;
            case 1: //INTENT_FLAGS_BT_SERVER
                navigationView.setCheckedItem(R.id.nav_bt_server);
                fragment = new BluetoothServerFragment();
                break;
            case 2: //INTENT_FLAGS_LINKED_SHEETS
                navigationView.setCheckedItem(R.id.nav_linked_sheets);
                fragment = new LinkedSheetsFragment();
                break;
            case 3: //INTENT_FLAGS_LOCAL_STORAGE
                navigationView.setCheckedItem(R.id.nav_local_storage);
                fragment = new LocalStorageFragment();
                break;
            default: //default to INTENT_FLAGS_MATCH_SCOUT
                navigationView.setCheckedItem(R.id.nav_match_scout);
                fragment = new MatchScoutFragment();
                break;
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment, fragment);
        ft.addToBackStack(null);
        ft.commit();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_match_scout) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment, new MatchScoutFragment());
            ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.nav_bt_server) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment, new BluetoothServerFragment());
            ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.nav_linked_sheets) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment, new LinkedSheetsFragment());
            ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.nav_local_storage) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment, new LocalStorageFragment());
            ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_about) {
            //TODO
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
