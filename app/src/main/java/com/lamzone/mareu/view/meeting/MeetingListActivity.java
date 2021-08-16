
package com.lamzone.mareu.view.meeting;

import android.app.TimePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lamzone.mareu.R;
import com.lamzone.mareu.databinding.ActivityMeetingListBinding;
import com.lamzone.mareu.injection.Injection;
import com.lamzone.mareu.injection.ViewModelFactory;
import com.lamzone.mareu.injector.DependencyInjector;
import com.lamzone.mareu.member_list.MemberViewModel;
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
    private MemberViewModel mMemberViewModel;
    private static int MEETING_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMeetingListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mRecyclerView = findViewById(R.id.list_meeting);
        mApiService = DependencyInjector.getMeetingApiService();
        this.configureToolbar();
        this.configureViewModel();
        binding.activityMainFab.setOnClickListener(v -> {
            Intent addReunion = new Intent(MeetingListActivity.this, MeetingAddActivity.class);

            startActivity(addReunion);
        });
    }

    private void configureViewModel() {
        ViewModelFactory mViewModelFactory = Injection.provideViewModelFactory(this);
        this.mMemberViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MemberViewModel.class);
        this.mMemberViewModel.init(MEETING_ID);
    }

    private void getCurrentMeeting(int meetingId){

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
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: resumed");
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
                    mMeetingListAdapter.getFilter().filter("");
                    return true;

                default:return super.onOptionsItemSelected(item);
            }
    }

    /**
     * show meetings sorted by room
     * @param list
     * @param position
     */
    @Override
    public void onPositiveButtonClicked(String[] list, int position) {
        mMeetingListAdapter.getFilter().filter(list[position]);
    }

    @Override
    public void onNegativeButtonClicked() {}

    /**
     * show meetings sorted by hour
     * @param view
     * @param hour
     * @param minute
     */
    @Override
    public void onTimeSet(TimePicker view, int hour, int minute) {
        mMeetingListAdapter.getFilter().filter(String.format("%02d", hour));
    }
}
