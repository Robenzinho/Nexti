package com.nexti.android;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;

import com.nexti.android.ui.courses.CoursesFragment;

import com.nexti.android.ui.home.HomeFragment;
import com.nexti.android.ui.notifications.NotificationsActivity;
import com.nexti.android.ui.profile.ProfileActivity;
import com.nexti.android.ui.discover.DiscoverFragment;
import com.nexti.android.ui.tools.ToolsFragment;
import java.util.Objects;



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private Fragment selectedFragment = null;
    private FloatingActionButton fab;
    private ActionBarDrawerToggle mToggle;
   // private TextView username , email;


    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull final MenuItem item) {

            switch (item.getItemId()) {
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    fab.setImageResource(R.drawable.ic_edit);
                    fab.show();
                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            UserPost();
                        }


                    });

                    break;
                case R.id.nav_courses:
                    selectedFragment = new CoursesFragment();
                    fab.hide();
                    break;
//                case R.id.nav_query:
//                    selectedFragment = new QueryFragment();
//                    fab.setImageResource(R.drawable.ic_edit);
//                    fab.show();
//                    fab.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            Toast.makeText(MainActivity.this, "Hello query", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                    break;
                case R.id.nav_search:
                    selectedFragment = new DiscoverFragment();
//                    fab.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            Toast.makeText(MainActivity.this, "Hello discover", Toast.LENGTH_SHORT).show();
//                        }
//                    });
                    fab.hide();
//                    fab.setImageResource(R.drawable.ic_add);

                    break;
                case R.id.nav_tools:
                    selectedFragment = new ToolsFragment();
                    fab.setImageResource(R.drawable.ic_edit);
                    fab.show();
                    fab.setAlpha((float) 0.8);

                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(MainActivity.this, "Hello tools", Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;

        }
    };

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, drawerLayout , R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserPost();
            }
        });

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();






        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        int id = item.getItemId();
        if (id == R.id.notifications) {
         startActivity(new Intent(this, NotificationsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_profile:
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                break;
            case R.id.nav_messages:
                startActivity(new Intent(this, NotificationsActivity.class));
                break;
            case R.id.nav_statistic:
                Toast.makeText(this, "Stats are not available for now", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_discover:
                startActivity(new Intent(MainActivity.this , TestDiscoverActivity.class));
                break;
            case R.id.nav_collections:
                startActivity(new Intent(MainActivity.this, CollectionsActivity.class));
                break;
            case R.id.nav_settings:
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                break;
            case R.id.nav_logOut:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(MainActivity.this , LoginActivity.class));
                break;
        }
        item.setChecked(true);
        return true;
    }

    public void UserPost(){
        startActivity(new Intent(MainActivity.this , PostActivity.class));
    }

}