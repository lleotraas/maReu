package com.lamzone.mareu.view.add;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.lamzone.mareu.R;
import com.lamzone.mareu.injector.DependencyInjector;
import com.lamzone.mareu.model.Meeting;
import com.lamzone.mareu.service.MeetingApiService;
import com.lamzone.mareu.view.material_dialog.RoomChoice;
import com.lamzone.mareu.view.timePicker_dialog.TimeChoice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lleotraas on 06.
 */
public class ReunionAddActivity extends AppCompatActivity implements RoomChoice.SingleChoiceListener, TimePickerDialog.OnTimeSetListener {


    private EditText mHourInput;
    private EditText mRoomChoiceInput;
    private EditText mNameInput;
    private EditText mMemberInput;
    private ImageButton mAddMemberBtn;
    private TextView mMemberListTxt;
    private Button mValidateBtn;
    private List<String> mMembers;
    private int mIndex;

    private MeetingApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_add);

        mApiService = DependencyInjector.getReunionApiService();

        mNameInput = findViewById(R.id.activity_add_meeting_name_input);
        mMemberInput = findViewById(R.id.activity_add_meeting_members_input);
        mAddMemberBtn = findViewById(R.id.activity_add_meeting_members_btn);
        mMemberListTxt = findViewById(R.id.activity_add_meeting_members_list_txt);
        mValidateBtn = findViewById(R.id.activity_add_meeting_validate_btn);
        mHourInput = findViewById(R.id.activity_add_meeting_choose_time_input);
        mRoomChoiceInput = findViewById(R.id.activity_add_meeting_room_input);
        mIndex = 0;

        mMembers = new ArrayList<>();
        mValidateBtn.setEnabled(false);
        mAddMemberBtn.setEnabled(false);

        this.configureToolbar();

        mValidateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewReunion();
            }
        });

        addMember();
        enableButtons();
    }

    private void configureToolbar() {
        Toolbar toolbar = findViewById(R.id.activity_meeting_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_24);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    public void setTimeInput(View v){
        TimeChoice timePicker = new TimeChoice();
        timePicker.show(getSupportFragmentManager(), "time picker");
    }

    @Override
    public void onTimeSet(TimePicker view, int hour, int minute) {
        mHourInput.setText("Heure : " + mApiService.makeHourString(hour, minute));
        enableValidateBtn();
    }

    public void setRoomInput(View v){
        DialogFragment singleChoiceDialog = new RoomChoice();
                singleChoiceDialog.setCancelable(false);
                singleChoiceDialog.show(getSupportFragmentManager(), "Single Choice Dialog");
    }

    @Override
    public void onPositiveButtonClicked(String[] list, int position) {
        mRoomChoiceInput.setText("Salle " + list[position]);
        enableValidateBtn();
    }

    private void addMember() {
        mAddMemberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMembers.add(mMemberInput.getText().toString());
                String member = mMemberListTxt.getText().toString();
                member += (++mIndex) + "." + mMemberInput.getText().toString();
                mMemberListTxt.setText(String.format("%s\n", member));
                mMemberInput.setText("");
                mAddMemberBtn.setEnabled(false);
                enableValidateBtn();
            }
        });
    }

    private void addNewReunion() {
        Meeting meeting = new Meeting(
                mApiService.getImageColor(),
                 String.format("%s%s", mHourInput.getText().toString().charAt(8), mHourInput.getText().toString().charAt(9)),
                String.format("%s%s", mHourInput.getText().toString().charAt(11), mHourInput.getText().toString().charAt(12)),
                String.format("%s", mRoomChoiceInput.getText().charAt(mRoomChoiceInput.getText().length()-1))  ,
                mNameInput.getText().toString(),
                mMembers
        );
        mApiService.addMeeting(meeting);
        finish();
    }

    @Override
    public void onNegativeButtonClicked() {}

    private void enableButtons() {
        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    enableValidateBtn();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        mMemberInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAddMemberBtn.setEnabled(s.length() != 0);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void enableValidateBtn(){
        mValidateBtn.setEnabled(mNameInput.getText().length() != 0 && mHourInput.getText().toString() != "" && mRoomChoiceInput.getText().toString() != "" && mMembers.size() != 0);
    }


}
