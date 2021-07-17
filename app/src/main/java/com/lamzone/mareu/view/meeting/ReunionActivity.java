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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lamzone.mareu.R;
import com.lamzone.mareu.injector.DependencyInjector;
import com.lamzone.mareu.model.Reunion;
import com.lamzone.mareu.service.ReunionApiService;
import com.lamzone.mareu.view.add.ReunionAddActivity;
import com.lamzone.mareu.view.material_dialog.RoomChoice;
import com.lamzone.mareu.view.timePicker_dialog.TimeChoice;

import java.util.List;

import static android.content.ContentValues.TAG;

public class ReunionActivity extends AppCompatActivity implements RoomChoice.SingleChoiceListener, TimePickerDialog.OnTimeSetListener {

    private RecyclerView mRecyclerView;
    private ReunionListAdapter mReunionListAdapter;
    private ReunionApiService mApiService;
    private FloatingActionButton mAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reunion);
        mAddButton = findViewById(R.id.activity_main_fab);
        mRecyclerView = findViewById(R.id.list_reunion);
        mApiService = DependencyInjector.getReunionApiService();
        this.configureToolbar();

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

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d(TAG, "onPostResume: resumed");
        setAdapter(mApiService.getReunion());
    }

    private void setAdapter(List<Reunion> reunion) {
        mReunionListAdapter = new ReunionListAdapter(reunion);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setAdapter(mReunionListAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()){
                case R.id.menu_activity_reunion_sorting_time:
                    TimeChoice timePicker = new TimeChoice();
                    timePicker.show(getSupportFragmentManager(), "time picker");
                    return true;

                case R.id.menu_activity_Reunion_sorting_room:
                    DialogFragment singleChoiceDialog = new RoomChoice();
                    singleChoiceDialog.setCancelable(false);
                    singleChoiceDialog.show(getSupportFragmentManager(), "Single Choice Dialog");
                    return true;

                case R.id.menu_activity_Reunion_sorting_cancel:
                    setAdapter(mApiService.getReunion());
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