package com.nexti.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.nexti.android.ui.main.TestDiscoverFragment;

public class TestDiscoverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_discover_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, TestDiscoverFragment.newInstance())
                    .commitNow();
        }
    }
}