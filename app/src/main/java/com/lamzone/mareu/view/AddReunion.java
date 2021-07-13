package com.lamzone.mareu.view;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.afollestad.materialdialogs.MaterialDialog;
import com.lamzone.mareu.R;
import com.lamzone.mareu.injector.DependencyInjector;
import com.lamzone.mareu.model.Reunion;
import com.lamzone.mareu.service.ReunionApiService;
import com.lamzone.mareu.view.dialog.RoomChoice;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by lleotraas on 06.
 */
public class AddReunion extends AppCompatActivity implements RoomChoice.SingleChoiceListener {

    private Button mTimeButton;
    private EditText mNameInput;
    private EditText mMemberInput;
    private Button mAddMemberBtn;
    private TextView mMemberListTxt;
    private Button mValidateBtn;
    private Button mRoomBtn;
    private List<String> mMembers;
    private int mIndex;
    private MaterialDialog mMaterialDialog;

    private TimePickerDialog mTimePickerDialog;
    private ReunionApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_reunion);

        mApiService = DependencyInjector.getReunionApiService();
        initTimePicker();

        mNameInput = findViewById(R.id.activity_add_reunion_name_input);
        mMemberInput = findViewById(R.id.activity_add_reunion_members_input);
        mAddMemberBtn = findViewById(R.id.activity_add_reunion_members_btn);
        mMemberListTxt = findViewById(R.id.activity_add_reunion_members_list_txt);
        mValidateBtn = findViewById(R.id.activity_add_reunion_validate_btn);
        mTimeButton = findViewById(R.id.activity_add_reunion_hour_btn);
        mRoomBtn = findViewById(R.id.activity_add_reunion_room_btn);
        mIndex = 0;

        mMembers = new ArrayList<>();
        mValidateBtn.setEnabled(false);

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
        this.configureToolbar();
        addMember();
        selectRoom();
        validateReunion();
    }

    private void selectRoom() {
        mRoomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment singleChoiceDialog = new RoomChoice();
                singleChoiceDialog.setCancelable(false);
                singleChoiceDialog.show(getSupportFragmentManager(), "Single Choice Dialog");
            }
        });
    }

    private void configureToolbar() {
        Toolbar toolbar = findViewById(R.id.activity_reunion_toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_add, menu);
        return true;
    }

    private void initTimePicker() {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                mTimeButton.setText(hour + ":" + minute);
            }
        };
        Calendar calendar = Calendar.getInstance(Locale.FRANCE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        mTimePickerDialog = new TimePickerDialog(this, timeSetListener, hour, minute, true);

    }

    public void openHourPicker(View view){
        mTimePickerDialog.show();
    }

    private void addMember() {
        mAddMemberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMembers.add(mMemberInput.getText().toString());
                String member = mMemberListTxt.getText().toString();
                member += (++mIndex) + "." + mMemberInput.getText().toString();
                mMemberListTxt.setText(member + "\n");
                mMemberInput.setText("");
                mMemberListTxt.setMovementMethod(new ScrollingMovementMethod());
            }
        });
    }

    @Override
    public void onPositiveButtonClicked(String[] list, int position) {
        mRoomBtn.setText(list[position]);
    }

    @Override
    public void onNegativeButtonClicked() {

    }

    private void validateReunion() {
        mValidateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reunion reunion = new Reunion(
                        System.currentTimeMillis(),
                        ""+mTimeButton.getText().toString().charAt(0) + mTimeButton.getText().toString().charAt(1),
                        ""+mTimeButton.getText().toString().charAt(3) + mTimeButton.getText().toString().charAt(4),
                        mRoomBtn.getText().toString(),
                        mNameInput.getText().toString(),
                        mMembers
                );
                mApiService.addReunion(reunion);
                finish();
            }
        });
    }
}
