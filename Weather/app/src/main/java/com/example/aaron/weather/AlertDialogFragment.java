package com.example.aaron.weather;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by Aaron on 7/13/2016.
 */
public class AlertDialogFragment extends DialogFragment{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(R.string.err_Title)
                .setMessage("An error has occurred!")
                .setPositiveButton(R.string.err_ok_button_txt, null);
        AlertDialog dialog = builder.create();
        return dialog;
    }
}
