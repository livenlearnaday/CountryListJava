package io.github.livenlearnaday.countrylistjava.utils;

import android.content.Context;


import androidx.appcompat.app.AlertDialog;

public class MessageUtils {

    public static void showAlertDialog(Context context,
                                       String alertTitle,
                                       String alertMessage) {

        if (alertMessage == null || alertMessage.equals("")) {
            AlertDialog dialog = new AlertDialog.Builder(context)
                    .setTitle(alertTitle)
                    .setNegativeButton("close", null)
                    .create();
            dialog.show();

        } else if (alertTitle == null || alertTitle.equals("")) {
            AlertDialog dialog = new AlertDialog.Builder(context)
                    .setMessage(alertMessage)
                    .setNegativeButton("close", null)
                    .create();
            dialog.show();

        } else {
            AlertDialog dialog = new AlertDialog.Builder(context)
                    .setTitle(alertTitle)
                    .setMessage(alertMessage)
                    .setNegativeButton("close", null)
                    .create();
            dialog.show();
        }

    }






}
