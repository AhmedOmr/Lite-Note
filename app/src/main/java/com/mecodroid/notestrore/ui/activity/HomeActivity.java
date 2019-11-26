package com.mecodroid.notestrore.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

import com.mecodroid.Note_Lite.R;
import com.mecodroid.notestrore.ui.fragment.homeCycle.HomeFragment;

import static com.mecodroid.notestrore.helper.HelperMethod.ReplaceFragment;


public class HomeActivity extends BaseActivity {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ReplaceFragment(getSupportFragmentManager(), new HomeFragment(),
                R.id.home_activity_fr_container, null, null);

    }

    @Override
    public void onBackPressed() {
        baseFragment.onBack();
    }
}
