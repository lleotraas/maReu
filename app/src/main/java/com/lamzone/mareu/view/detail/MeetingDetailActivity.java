package com.lamzone.mareu.view.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.lamzone.mareu.R;
import com.lamzone.mareu.databinding.ActivityMeetingDetailBinding;
import com.lamzone.mareu.model.Meeting;

public class MeetingDetailActivity extends AppCompatActivity {

    private Meeting mMeeting;
    private ActivityMeetingDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMeetingDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent detailActivity = getIntent();
        if (detailActivity != null){
            mMeeting = getIntent().getParcelableExtra("reunion");
            if (mMeeting != null){
                initDetails();        
            }
        }
        this.configureToolbar();
    }

    private void configureToolbar() {
        Toolbar toolbar = findViewById(R.id.activity_meeting_toolbar);
        toolbar.setTitle(String.format("Détail de la réunion"));
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

    private void initDetails() {
        binding.activityDetailMeetingTitleTxt.setText(String.format("Réunion de %s", mMeeting.getName()));
        binding.activityDetailMeetingInformationsTxt.setText(String.format("Salle %s à %sH%s", mMeeting.getRoom(), mMeeting.getHour(), mMeeting.getMinute()));
//        binding .activityDetailMeetingMemberTitleTxt.setText(String.format("Participants (%d)", mMeeting.getMembers().size()));
//        binding.activityDetailMeetingMemberListTxt.setText(mMeeting.toStringDetail());
    }
}