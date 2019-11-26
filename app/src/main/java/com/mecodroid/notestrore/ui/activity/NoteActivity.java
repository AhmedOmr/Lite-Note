package com.mecodroid.notestrore.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

import com.mecodroid.Note_Lite.R;
import com.mecodroid.notestrore.ui.fragment.noteCycle.NoteFragment;

import static com.mecodroid.notestrore.helper.HelperMethod.ReplaceFragment;

public class NoteActivity extends BaseActivity {


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        ReplaceFragment(getSupportFragmentManager(), new NoteFragment(),
                R.id.note_activity_fr_container, null, null);

    }

    @Override
    public void onBackPressed() {
        baseFragment.onBack();
    }
}