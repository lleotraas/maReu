package com.lamzone.mareu.view.meeting;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lamzone.mareu.R;
import com.lamzone.mareu.injector.DependencyInjector;
import com.lamzone.mareu.model.Reunion;
import com.lamzone.mareu.service.ReunionApiService;
import com.lamzone.mareu.view.add.ReunionAddActivity;
import com.lamzone.mareu.view.material_dialog.RoomChoice;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ReunionActivity extends AppCompatActivity implements RoomChoice.SingleChoiceListener {

    private RecyclerView mRecyclerView;
    private ReunionListAdapter mReunionListAdapter;
    private List<Reunion> mReunions;
    private ReunionApiService mApiService;
    private FloatingActionButton mAddButton;
    private TimePickerDialog mTimePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reunion);
        mAddButton = findViewById(R.id.activity_main_fab);
        mRecyclerView = findViewById(R.id.list_reunion);
        mApiService = DependencyInjector.getReunionApiService();
        mReunions = mApiService.getReunion();
        this.configureToolbar();
        initTimePicker();

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addReunion = new Intent(ReunionActivity.this, ReunionAddActivity.class);
                startActivity(addReunion);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_reunion, menu);
        return true;
    }

    private void configureToolbar(){
        Toolbar toolbar = findViewById(R.id.activity_reunion_toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setAdapter();
    }

    private void setAdapter() {
        mReunionListAdapter = new ReunionListAdapter(mReunions);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setAdapter(mReunionListAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()){
                case R.id.menu_activity_reunion_sorting_time:
                    mTimePickerDialog.show();
                    return true;

                case R.id.menu_activity_Reunion_sorting_room:
                    DialogFragment singleChoiceDialog = new RoomChoice();
                    singleChoiceDialog.setCancelable(false);
                    singleChoiceDialog.show(getSupportFragmentManager(), "Single Choice Dialog");
                    return true;

                case R.id.menu_activity_Reunion_sorting_cancel:
                    mReunions = mApiService.getReunion();
                    setAdapter();

                default:return super.onOptionsItemSelected(item);
            }
    }

    private void initTimePicker() {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                mReunions = mApiService.sortingByTime(hour);
                setAdapter();
            }
        };
        Calendar calendar = Calendar.getInstance(Locale.FRANCE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        mTimePickerDialog = new TimePickerDialog(this, timeSetListener, hour, minute, true);
    }

    @Override
    public void onPositiveButtonClicked(String[] list, int position) {
        mReunions = mApiService.sortingByRoom(list[position]);
        setAdapter();
    }

    @Override
    public void onNegativeButtonClicked() {

    }
}