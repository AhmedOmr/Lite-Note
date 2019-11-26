package com.mecodroid.notestrore.ui.fragment;

import android.support.v4.app.Fragment;

import com.mecodroid.notestrore.ui.activity.BaseActivity;

public class BaseFragment extends Fragment {
    BaseActivity baseActivity;

    public void initFragment() {
        baseActivity = (BaseActivity) getActivity();
        baseActivity.baseFragment = this;
    }

    public void onBack() {
        baseActivity.backPressed();
    }
}
