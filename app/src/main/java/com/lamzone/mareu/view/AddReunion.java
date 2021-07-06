package com.lamzone.mareu.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;


import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.lamzone.mareu.R;
import com.lamzone.mareu.injector.DependencyInjector;
import com.lamzone.mareu.service.ReunionApiService;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by lleotraas on 06.
 */
public class AddReunion extends AppCompatActivity {

    private Button mDateBtn;
    private Button mTimeBtn;
    private Spinner mRoomSpinner;
    private EditText mNameInput;
    private MultiAutoCompleteTextView mMemberMacTxt;
    private ArrayAdapter<CharSequence> spinnerAdapter;

    private DatePickerDialog mDatePickerDialog;
    private TimePickerDialog mTimePickerDialog;
    private ReunionApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_reunion);

        mApiService = DependencyInjector.getReunionApiService();
        initDatePicker();
        initTimePicker();

        mDateBtn = findViewById(R.id.activity_add_reunion_date_btn);
        mTimeBtn = findViewById(R.id.activity_add_reunion_time_btn);
        mRoomSpinner = findViewById(R.id.activity_add_reunion_room_spinner);
        mNameInput = findViewById(R.id.activity_add_reunion_name_txt);
        mMemberMacTxt = findViewById(R.id.activity_add_reunion_members_mactxt);

        spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.room_array, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mRoomSpinner.setAdapter(spinnerAdapter);
    }

    private void initTimePicker() {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                String time = mApiService.makeHourString(hour, minute);
                mTimeBtn.setText(time);
            }
        };
        Calendar cal = Calendar.getInstance(Locale.FRANCE);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int style = AlertDialog.THEME_HOLO_LIGHT;
        mTimePickerDialog = new TimePickerDialog(this, style, timeSetListener, hour, minute, true);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = mApiService.makeDateString(day, month, year);
                mDateBtn.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance(Locale.FRANCE);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;
        mDatePickerDialog = new DatePickerDialog(this, style, dateSetListener,year, month, day);
    }

    public void openDatePicker(View view) {
        mDatePickerDialog.show();
    }

    public void openHourPicker(View view) {
        mTimePickerDialog.show();
    }
}
