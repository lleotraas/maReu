package com.lamzone.mareu.view.timePicker_dialog;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by lleotraas on 17.
 */
public class TimeChoice extends DialogFragment {

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance(Locale.FRANCE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener) getActivity(), hour, minute, true);
    }
}
