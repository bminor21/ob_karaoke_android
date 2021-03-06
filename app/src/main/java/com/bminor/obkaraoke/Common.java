package com.bminor.obkaraoke;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Created by Brett on 1/13/16.
 */
public class Common {

    public enum ResultType{
        INACTIVE, SUCCESS, ERROR;
    }

    /**
     *
     *    Show dialog alert box.
     *    Takes title and message strings as a parameter
     **/
    public static void showAlert( String title, String message, Activity current ){
        AlertDialog alertDialog = new AlertDialog.Builder( current ).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });

        alertDialog.show();
    }
}
