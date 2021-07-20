package com.lamzone.mareu.view.detail;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;


import com.lamzone.mareu.R;
import com.lamzone.mareu.model.Meeting;

public class ReunionDetailActivity extends AppCompatActivity {

    private TextView mTitle;
    private TextView mMember;
    private TextView mMeetingInformation;
    private Meeting mMeeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_detail);

        mTitle = findViewById(R.id.activity_detail_meeting_title_txt);
        mMeetingInformation = findViewById(R.id.activity_detail_meeting_room_txt);
        mMember = findViewById(R.id.activity_detail_meeting_member_list_txt);

        this.configureToolbar();
        Intent detailActivity = getIntent();
        if (detailActivity != null){
            mMeeting = getIntent().getParcelableExtra("reunion");
            if (mMeeting != null){
                initDetails();        
            }
        }
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

    private void initDetails() {
        mTitle.setText(String.format("Réunion de %s", mMeeting.getName()));
        mMeetingInformation.setText(String.format("Salle %s à %sH%s", mMeeting.getRoom(), mMeeting.getHour(), mMeeting.getMinute()));
        mMember.setText(mMeeting.toStringDetail());
    }
}