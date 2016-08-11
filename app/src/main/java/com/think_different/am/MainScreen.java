package com.think_different.am;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.f_log_scrn);
        getSupportActionBar().hide();

    }
}
