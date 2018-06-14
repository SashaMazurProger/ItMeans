package com.example.sasham.itmeans.recents;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sasham.itmeans.BaseActivity;
import com.example.sasham.itmeans.R;

import dagger.android.support.AndroidSupportInjection;

public class RecentsActivity extends BaseActivity {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recents);

        fragmentManager = getSupportFragmentManager();

        showRecentsFragment();
    }

    private void showRecentsFragment() {
        fragmentManager.beginTransaction()
                .replace(R.id.recents_container, new RecentsFragment())
                .commit();

//        setTitle(getString(R.string.));
    }
}
