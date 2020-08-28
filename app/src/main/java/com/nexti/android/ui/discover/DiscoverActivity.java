package com.nexti.android.ui.discover;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.nexti.android.R;

public class DiscoverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);

       getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_discover, new DiscoverFragment()).commit();
    }
}