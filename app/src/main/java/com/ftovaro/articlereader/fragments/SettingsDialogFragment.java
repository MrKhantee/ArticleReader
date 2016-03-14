package com.ftovaro.articlereader.fragments;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;

import com.ftovaro.articlereader.R;
import com.ftovaro.articlereader.interfaces.SettingsDialogListener;

/**
 * Manage the AlertDialog that is shown to the user.
 * Created by FelipeTovar on 09-Mar-16.
 */
public class SettingsDialogFragment extends DialogFragment {

    private SettingsDialogListener dialogListener;
    private SwitchCompat switchCompat;
    boolean switchState;
    private SharedPreferences preferences;
    private static final String PREF_NAME = "swpref";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        preferences = getActivity().getSharedPreferences(PREF_NAME,
                getActivity().MODE_PRIVATE);

        boolean switchCurrentState = preferences.getBoolean(PREF_NAME, false);

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.fragment_settings_dialog, null);

        switchCompat = (SwitchCompat) view.findViewById(R.id.color_mode_switch);

        switchCompat.setChecked(switchCurrentState);

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switchState = isChecked;

                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean(PREF_NAME, switchCompat.isChecked()); // value to store
                editor.commit();
            }
        });

        builder.setView(view)
                // Add action buttons
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialogListener.onDialogPositiveClick(switchState);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SettingsDialogFragment.this.getDialog().cancel();
                    }
                });

        builder.setTitle("Settings");

        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            dialogListener = (SettingsDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement SettingsDialogListener");
        }
    }
}
