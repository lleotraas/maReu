package com.lamzone.mareu.view.add;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.TimePicker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.lamzone.mareu.R;
import com.lamzone.mareu.databinding.ActivityMeetingAddBinding;
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
public class MeetingAddActivity extends AppCompatActivity implements RoomChoice.SingleChoiceListener, TimePickerDialog.OnTimeSetListener {

    private ActivityMeetingAddBinding binding;
    private List<String> mMembers;
    private int mIndex;
    private MeetingApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMeetingAddBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mApiService = DependencyInjector.getReunionApiService();

        mIndex = 0;

        mMembers = new ArrayList<>();
        binding.activityAddMeetingValidateBtn.setEnabled(false);
        binding.activityAddMeetingMembersBtn.setEnabled(false);

        this.configureToolbar();

        binding.activityAddMeetingValidateBtn.setOnClickListener(v -> addNewReunion());

        addMember();
        enableButtons();
    }

    private void configureToolbar() {
        Toolbar toolbar = findViewById(R.id.activity_meeting_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Créer une réunion");
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
        binding.activityAddMeetingChooseTimeInput.setText("Heure : " + mApiService.makeHourString(hour, minute));
        enableValidateBtn();
    }

    public void setRoomInput(View v){
        DialogFragment singleChoiceDialog = new RoomChoice();
                singleChoiceDialog.setCancelable(false);
                singleChoiceDialog.show(getSupportFragmentManager(), "Single Choice Dialog");
    }

    @Override
    public void onPositiveButtonClicked(String[] list, int position) {
        binding.activityAddMeetingRoomInput.setText("Salle " + list[position]);
        enableValidateBtn();
    }

    @Override
    public void onNegativeButtonClicked() {}

    private void addMember() {
        binding.activityAddMeetingMembersBtn.setOnClickListener(v -> {
            mMembers.add(binding.activityAddMeetingMembersInput.getText().toString());
            String member = binding.activityAddMeetingMembersListTxt.getText().toString();
            member += String.format("%d.%s", ++mIndex, binding.activityAddMeetingMembersInput.getText().toString());
            binding.activityAddMeetingMembersListTxt.setText(String.format("%s\n", member));
            binding.activityAddMeetingMembersInput.setText("");
            binding.activityAddMeetingMembersBtn.setEnabled(false);
            enableValidateBtn();
        });
    }

    private void addNewReunion() {
        Meeting meeting = new Meeting(
                mApiService.getImageColor(),
                 String.format("%s%s", binding.activityAddMeetingChooseTimeInput.getText().toString().charAt(8), binding.activityAddMeetingChooseTimeInput.getText().toString().charAt(9)),
                String.format("%s%s", binding.activityAddMeetingChooseTimeInput.getText().toString().charAt(11), binding.activityAddMeetingChooseTimeInput.getText().toString().charAt(12)),
                String.format("%s", binding.activityAddMeetingRoomInput.getText().charAt(binding.activityAddMeetingRoomInput.getText().length()-1))  ,
                binding.activityAddMeetingNameInput.getText().toString(),
                mMembers
        );
        mApiService.addMeeting(meeting);
        finish();
    }

    private void enableButtons() {
        binding.activityAddMeetingNameInput.addTextChangedListener(new TextWatcher() {
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

        binding.activityAddMeetingMembersInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.activityAddMeetingMembersBtn.setEnabled(s.length() != 0);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void enableValidateBtn(){
        binding.activityAddMeetingValidateBtn.setEnabled(
                binding.activityAddMeetingNameInput.getText().length() != 0 &&
                binding.activityAddMeetingChooseTimeInput.getText().toString() != "" &&
                binding.activityAddMeetingRoomInput.getText().toString() != "" &&
                mMembers.size() != 0
        );
    }
}
