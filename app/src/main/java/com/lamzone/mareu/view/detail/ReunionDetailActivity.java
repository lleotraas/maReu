package com.lamzone.mareu.view.detail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lamzone.mareu.R;
import com.lamzone.mareu.model.Reunion;

public class ReunionDetailActivity extends AppCompatActivity {

    private TextView mTitle;
    private TextView mTime;
    private TextView mRoom;
    private TextView mMember;
    private Reunion mReunion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reunion_detail);

        mTitle = findViewById(R.id.activity_detail_reunion_title_txt);
        mTime = findViewById(R.id.activity_detail_reunion_time_txt);
        mRoom = findViewById(R.id.activity_detail_reunion_room_letter_txt);
        mMember = findViewById(R.id.activity_detail_reunion_member_list_txt);

        this.configureToolbar();
        Intent detailActivity = getIntent();
        if (detailActivity != null){
            mReunion = getIntent().getParcelableExtra("reunion");
            if (mReunion != null){
                initDetails();        
            }
        }
    }

    private void configureToolbar() {
        Toolbar toolbar = findViewById(R.id.activity_reunion_toolbar);
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
        mTitle.setText("Réunion de " + mReunion.getName());
        mRoom.setText(mReunion.getRoom());
        mTime.setText(mReunion.getHour() + "H" + mReunion.getMinute());
        mMember.setText(mReunion.toStringDetail());
    }
}