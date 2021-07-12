package com.lamzone.mareu.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lamzone.mareu.R;
import com.lamzone.mareu.model.Reunion;

public class ReunionDetailActivity extends AppCompatActivity {

    private FloatingActionButton mReturnFab;
    private TextView mTitle;
    private TextView mTime;
    private TextView mRoom;
    private TextView mMember;
    private Reunion mReunion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reunion_detail);

        mReturnFab = findViewById(R.id.activity_detail_reunion_return_fab);
        mTitle = findViewById(R.id.activity_detail_reunion_title_txt);
        mTime = findViewById(R.id.activity_detail_reunion_time_txt);
        mRoom = findViewById(R.id.activity_detail_reunion_room_letter_txt);
        mMember = findViewById(R.id.activity_detail_reunion_member_list_txt);


        Intent detailActivity = getIntent();
        if (detailActivity != null){
            mReunion = getIntent().getParcelableExtra("reunion");
            if (mReunion != null){
                initDetails();        
            }
        }

        mReturnFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initDetails() {
        mTitle.setText("Réunion de " + mReunion.getName());
        mRoom.setText(mReunion.getRoom());
        mTime.setText(mReunion.getHour() + "H" + mReunion.getMinute());
        mMember.setText(mReunion.toStringDetail());
    }
}