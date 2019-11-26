package com.mecodroid.notestrore.ui.fragment.splashCycle;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.mecodroid.Note_Lite.R;
import com.mecodroid.notestrore.ui.activity.HomeActivity;
import com.mecodroid.notestrore.ui.fragment.BaseFragment;
import com.mecodroid.notestrore.helper.HelperMethod;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends BaseFragment {

    private final int SPLASH_TIME_OUT = 3000;
    @BindView(R.id.splash_fragment_img_logo)
    ImageView splashFragmentImgLogo;
    @BindView(R.id.splash_fragment_txt_title)
    TextView splashFragmentTxtTitle;
    Unbinder unbinder;
    private Typeface cairoboldFont, proudlyFont;

    public SplashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initFragment();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        unbinder = ButterKnife.bind(this, view);
        initTools();
        launchSplash();
        return view;
    }

    public void initTools() {
        StatusBarUtil.setTransparent(getActivity());
        cairoboldFont = ResourcesCompat.getFont(getActivity(), R.font.cairo_bold);
        proudlyFont = ResourcesCompat.getFont(getActivity(), R.font.proudlybold);
        if (HelperMethod.isRTL()) {
            splashFragmentTxtTitle.setTypeface(cairoboldFont);
            splashFragmentTxtTitle.setTextSize(36);
        } else {
            splashFragmentTxtTitle.setTypeface(proudlyFont);
            splashFragmentTxtTitle.setTextSize(36);

        }
    }

    public void launchSplash() {

        try {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(getActivity(), HomeActivity.class));
                    getActivity().finish();

                }
            }, SPLASH_TIME_OUT);
        } catch (Exception e) {
            HelperMethod.customMassageError(getActivity(), e.getMessage());
        }
    }

    @Override
    public void onBack() {
        getActivity().finish();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
