package com.lamzone.mareu.view.meeting;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lamzone.mareu.R;
import com.lamzone.mareu.databinding.ActivityMeetingListBinding;
import com.lamzone.mareu.injector.DependencyInjector;
import com.lamzone.mareu.model.Meeting;
import com.lamzone.mareu.service.MeetingApiService;
import com.lamzone.mareu.view.add.MeetingAddActivity;
import com.lamzone.mareu.view.material_dialog.RoomChoice;
import com.lamzone.mareu.view.timePicker_dialog.TimeChoice;

import java.util.List;

import static android.content.ContentValues.TAG;

public class MeetingListActivity extends AppCompatActivity implements RoomChoice.SingleChoiceListener, TimePickerDialog.OnTimeSetListener {

    private RecyclerView mRecyclerView;
    private MeetingListAdapter mMeetingListAdapter;
    private MeetingApiService mApiService;
    private ActivityMeetingListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMeetingListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mRecyclerView = findViewById(R.id.list_meeting);
        mApiService = DependencyInjector.getReunionApiService();
        this.configureToolbar();

        binding.activityMainFab.setOnClickListener(v -> {
            Intent addReunion = new Intent(MeetingListActivity.this, MeetingAddActivity.class);
            startActivity(addReunion);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_meeting, menu);
        return true;
    }

    private void configureToolbar(){
        Toolbar toolbar = findViewById(R.id.activity_meeting_toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d(TAG, "onPostResume: resumed");
        setAdapter(mApiService.getMeeting());
    }

    private void setAdapter(List<Meeting> meeting) {
        mMeetingListAdapter = new MeetingListAdapter(meeting);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setAdapter(mMeetingListAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()){
                case R.id.menu_activity_meeting_sorting_time:
                    TimeChoice timePicker = new TimeChoice();
                    timePicker.show(getSupportFragmentManager(), "time picker");
                    return true;

                case R.id.menu_activity_meeting_sorting_room:
                    DialogFragment singleChoiceDialog = new RoomChoice();
                    singleChoiceDialog.setCancelable(false);
                    singleChoiceDialog.show(getSupportFragmentManager(), "Single Choice Dialog");
                    return true;

                case R.id.menu_activity_meeting_sorting_cancel:
                    setAdapter(mApiService.getMeeting());
                    return true;

                default:return super.onOptionsItemSelected(item);
            }
    }

    @Override
    public void onPositiveButtonClicked(String[] list, int position) {
        setAdapter(mApiService.sortingByRoom(list[position]));
    }

    @Override
    public void onNegativeButtonClicked() {}

    @Override
    public void onTimeSet(TimePicker view, int hour, int minute) {
        setAdapter(mApiService.sortingByTime(hour));
    }
}