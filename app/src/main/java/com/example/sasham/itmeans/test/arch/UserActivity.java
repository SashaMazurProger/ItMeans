package com.example.sasham.itmeans.test.arch;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.sasham.itmeans.R;
import com.example.sasham.itmeans.test.TestRepo;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class UserActivity extends AppCompatActivity {

    @Inject
    public TestRepo testRepo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        AndroidInjection.inject(this);
        Toast.makeText(this,"res:"+testRepo.getNumb(),Toast.LENGTH_LONG).show();
    }


}
