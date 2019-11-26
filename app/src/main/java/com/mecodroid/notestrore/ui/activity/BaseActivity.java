package com.mecodroid.notestrore.ui.activity;

import android.support.v7.app.AppCompatActivity;

import com.mecodroid.notestrore.ui.fragment.BaseFragment;

public class BaseActivity extends AppCompatActivity {
    public BaseFragment baseFragment;

    public void backPressed() {
        super.onBackPressed();
    }
}
