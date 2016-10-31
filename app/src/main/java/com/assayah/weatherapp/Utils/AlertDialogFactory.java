package com.assayah.weatherapp.Utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.widget.EditText;

import com.assayah.weatherapp.R;

public class AlertDialogFactory {

    public static AlertDialog.Builder createDialogForAdding(Context context, final CreateCityListener listener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(context.getString(R.string.add_new_city));
        alertDialog.setMessage(context.getString(R.string.enter_zip_code));
        final EditText input = new EditText(context);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        alertDialog.setView(input);
        alertDialog.setPositiveButton(context.getString(R.string.add_button), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onAddingCityWithZipCode(input.getText().toString());
            }
        });
        alertDialog.setNegativeButton(context.getString(R.string.cancel_button), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }});

        return alertDialog;
    }

    public interface CreateCityListener {
       void onAddingCityWithZipCode(String zipCode);
    }

}
