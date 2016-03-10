package com.ftovaro.articlereader.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;

import com.ftovaro.articlereader.R;

/**
 * Created by ftova on 09-Mar-16.
 */
public class InfoShower {

    private static ProgressDialog progressDialog;

    public static void showDialog(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(context.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public static void hideDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public static void showSnack(CoordinatorLayout coordinatorLayout, String message, int duration) {
        Snackbar.make(coordinatorLayout, message, duration).show();
    }

    public static void showSnack(CoordinatorLayout coordinatorLayout, String message) {
        showSnack(coordinatorLayout, message, Snackbar.LENGTH_LONG);
    }
}
