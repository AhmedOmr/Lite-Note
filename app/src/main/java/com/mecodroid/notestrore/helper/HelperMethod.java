package com.mecodroid.notestrore.helper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mecodroid.Note_Lite.R;
import com.yarolegovich.lovelydialog.LovelyProgressDialog;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;


public class HelperMethod {

    public static AlertDialog alertDialog;
    public static Snackbar snackbar;
    public static Toast toast;
    private static ProgressDialog checkDialog;
    private static ConnectivityManager cm;
    private static LovelyProgressDialog lovelyDailog;
    private static DecimalFormatSymbols symbols;
    private static DecimalFormat mFormat;
    private static String data;

    public static void createSnackbar(View v, String message, Activity activity) {
        Snackbar snack = Snackbar.make(
                (activity.findViewById(android.R.id.content)),
                message + "", Snackbar.LENGTH_SHORT);
        snack.setDuration(Snackbar.LENGTH_INDEFINITE);//change Duration as you need
        //snack.setAction(actionButton, new View.OnClickListener());//add your own listener
        View view = snack.getView();
        TextView tv = view
                .findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);//change textColor

        TextView tvAction = view
                .findViewById(android.support.design.R.id.snackbar_action);
        tvAction.setTextSize(16);
        tvAction.setTextColor(activity.getResources().getColor(R.color.dark_blue));

        snack.show();
    }

    public static void createSnackBar(View view, String message, Context context) {
        final Snackbar snackbar = Snackbar.make(view, message, 50000);
        snackbar.setAction(R.string.dismiss, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
            }
        })
                .setActionTextColor(context.getResources().getColor(R.color.dark_blue))

                .show();
    }

    public static void createSnackBar(View view, String message, Context context,
                                      View.OnClickListener action, String Title) {
        snackbar = Snackbar.make(view, message, 50000);
        snackbar.setAction(Title, action)
                .setActionTextColor(context.getResources().getColor(R.color.dark_blue))
                .show();
    }


    public static void showProgressDialog(Activity activity, String title) {
        try {
            checkDialog = new ProgressDialog(activity);
            checkDialog.setMessage(title);
            checkDialog.setIndeterminate(false);
            checkDialog.setCancelable(false);
            checkDialog.show();

        } catch (Exception e) {

        }
    }

    public static void dismissProgressDialog() {
        try {
            if (checkDialog != null && checkDialog.isShowing()) {
                checkDialog.dismiss();
            }
        } catch (Exception e) {

        }
    }

    public static void ReplaceFragment(FragmentManager supportFragmentManager, Fragment fragment,
                                       int container_id
            , TextView toolbarTitle, String title) {

        FragmentTransaction transaction = supportFragmentManager.beginTransaction();

        transaction.replace(container_id, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        if (toolbarTitle != null) {
            toolbarTitle.setText(title);
        }

    }

   /* public static void hideFragment(FragmentManager supportFragmentManager, Fragment fragment,
                                             int container_id) {
        Fragment currentFragment = supportFragmentManager
                .findFragmentById(container_id);
        if (currentFragment instanceof fragment) {
            supportFragmentManager.beginTransaction()
                    .hide(supportFragmentManager
                            .findFragmentById(container_id)).commit();

        }
    }*/

    public static void disappearKeypad(Activity activity, View v) {
        try {
            if (v != null) {
                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        } catch (Exception e) {

        }
    }

    public static void showToastMassage(Activity activity, String title) {
        try {
            toast = Toast.makeText(activity,
                    title,
                    Toast.LENGTH_SHORT);
            toast.show();
        } catch (Exception e) {

        }
    }

    public static void customMassageError(Activity activity, String title) {
        LayoutInflater inflater = activity.getLayoutInflater();

        View toastLayout = inflater.inflate(R.layout.shape_toast,
                (ViewGroup) activity.findViewById(R.id.toast_root_view));

        toastLayout.setBackground(activity.getResources().getDrawable(R.drawable.shape_toast_error));

        TextView header = toastLayout.findViewById(R.id.toast_header);
        header.setText(title);

        ImageView body = toastLayout.findViewById(R.id.toast_body);
        body.setImageResource(R.drawable.icon_failed24);

        Toast toast = new Toast(activity);
        toast.setGravity(Gravity.BOTTOM, 0, 250);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastLayout);
        toast.show();
    }

    public static void customMassageDone(Activity activity, String title) {
        LayoutInflater inflater = activity.getLayoutInflater();

        View toastLayout = inflater.inflate(R.layout.shape_toast,
                (ViewGroup) activity.findViewById(R.id.toast_root_view));

        toastLayout.setBackground(activity.getResources().getDrawable(R.drawable.shape_toast_done));

        TextView header = toastLayout.findViewById(R.id.toast_header);
        header.setText(title);

        ImageView body = toastLayout.findViewById(R.id.toast_body);
        body.setImageResource(R.drawable.icon_done24);

        Toast toast = new Toast(activity);
        toast.setGravity(Gravity.BOTTOM, 0, 250);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastLayout);
        toast.show();
    }

    // set color with alpha
    public static int getColorWithAlpha(int color, float ratio) {
        int newColor = 0;
        int alpha = Math.round(Color.alpha(color) * ratio);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        newColor = Color.argb(alpha, r, g, b);
        return newColor;
    }

    // Check Language
    public static boolean isRTL() {
        return isRTL(Locale.getDefault());
    }

    private static boolean isRTL(Locale locale) {
        final int directionality = Character.getDirectionality(locale.getDisplayName().charAt(0));
        return directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT ||
                directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC;
    }

    // check internet
    public static boolean isConnected(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
            return isConnected;
        } catch (NullPointerException e) {

        }

        return false;
    }


    public static void setTitle(String title, TextView textView) {
        textView.setText(title);
    }


    public static void onLoadImageFromUrl(ImageView imageView, String URl, Context context) {
        Glide.with(context)
                .load(URl)
                .into(imageView);

    }

    public static void setLovelyProgressDailog(Activity activity, int icon,
                                               String topTitle, int titleColor, int topColor) {
        lovelyDailog = new LovelyProgressDialog(activity);
        lovelyDailog.setIcon(icon)
                .setTitle(R.string.wait)
                .setTitleGravity(Gravity.CENTER)
                .setTopTitle(topTitle)
                .setTopTitleColor(titleColor)
                .setTopColorRes(topColor)
                .show();
    }

    public static void dismissLovelyDailog() {
        lovelyDailog.dismiss();
    }


}
