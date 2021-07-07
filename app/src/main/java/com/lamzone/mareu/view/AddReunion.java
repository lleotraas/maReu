package com.lamzone.mareu.view;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;


import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.lamzone.mareu.R;
import com.lamzone.mareu.injector.DependencyInjector;
import com.lamzone.mareu.model.Reunion;
import com.lamzone.mareu.service.ReunionApiService;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by lleotraas on 06.
 */
public class AddReunion extends AppCompatActivity {

    private TextView mTitleTxt;
    private Button mTimeBtn;
    private Spinner mRoomSpinner;
    private EditText mNameInput;
    private EditText mMemberInput;
    private Button mAddMemberBtn;
    private TextView mMemberListTxt;
    private Button mValidateBtn;
    private ArrayAdapter<CharSequence> spinnerAdapter;
    private List<String> mMembers;

    private TimePickerDialog mTimePickerDialog;
    private ReunionApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_reunion);

        mApiService = DependencyInjector.getReunionApiService();
        initTimePicker();

        mTitleTxt = findViewById(R.id.activity_add_reunion_title_txt);
        mTimeBtn = findViewById(R.id.activity_add_reunion_time_btn);
        mRoomSpinner = findViewById(R.id.activity_add_reunion_room_spinner);
        mNameInput = findViewById(R.id.activity_add_reunion_name_input);
        mMemberInput = findViewById(R.id.activity_add_reunion_members_input);
        mAddMemberBtn = findViewById(R.id.activity_add_reunion_members_btn);
        mMemberListTxt = findViewById(R.id.activity_add_reunion_members_list_txt);
        mValidateBtn = findViewById(R.id.activity_add_reunion_validate_btn);

        mMembers = new ArrayList<>();
        mValidateBtn.setEnabled(false);
        setSpinnerAdapter();
        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mValidateBtn.setEnabled(s.length() > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        addMember();
        validateReunion();
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

    public void openHourPicker(View view) {
        mTimePickerDialog.show();
    }

    private void setSpinnerAdapter() {
        spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.room_array, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mRoomSpinner.setAdapter(spinnerAdapter);
    }

    private void addMember() {
        mAddMemberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMembers.add(mMemberInput.getText().toString());
                String member = mMemberListTxt.getText().toString();
                member += mMemberInput.getText().toString();
                mMemberListTxt.setText(member + "\n");
                mMemberInput.setText("");

            }
        });
    }

    private void validateReunion() {
        mValidateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id = System.currentTimeMillis();
                String time = mTimeBtn.getText().toString();
                String room = mRoomSpinner.getSelectedItem().toString();
                String name = mNameInput.getText().toString();
                mApiService.addReunion(id, room, time, name, mMembers);
                finish();
            }
        });
    }
}
