package com.mecodroid.notestrore.ui.activity;

import android.os.Bundle;

import com.mecodroid.Note_Lite.R;
import com.mecodroid.notestrore.ui.fragment.splashCycle.SplashFragment;

import static com.mecodroid.notestrore.helper.HelperMethod.ReplaceFragment;

public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ReplaceFragment(getSupportFragmentManager(), new SplashFragment(),
                R.id.splash_activity_fr_container, null, null);
    }

    @Override
    public void onBackPressed() {
        baseFragment.onBack();
    }
}
