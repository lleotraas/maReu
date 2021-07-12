package com.lamzone.mareu.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lamzone.mareu.R;
import com.lamzone.mareu.injector.DependencyInjector;
import com.lamzone.mareu.model.Reunion;
import com.lamzone.mareu.service.ReunionApiService;

import java.util.List;

public class ReunionActivity extends AppCompatActivity {

    private static final String TAG = ReunionActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private ReunionListAdapter mReunionListAdapter;
    private List<Reunion> mReunions;
    private ReunionApiService mApiService;
    private FloatingActionButton mAddButton;
    private Spinner mSearchTimeInput;
    private Button mSearchBtn;
    private ArrayAdapter<CharSequence> mSpinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reunion);
        mAddButton = findViewById(R.id.activity_main_fab);
        mSearchTimeInput = findViewById(R.id.menu_activity_Reunion_sorting_input);
        mSearchBtn = findViewById(R.id.menu_activity_Reunion_sorting_btn);
        mApiService = DependencyInjector.getReunionApiService();

        mRecyclerView = findViewById(R.id.list_reunion);

        this.configureToolbar();

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addReunion = new Intent(ReunionActivity.this, AddReunion.class);
                startActivity(addReunion);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
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
        mReunions = mApiService.getReunion();
        mReunionListAdapter = new ReunionListAdapter(mReunions);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setAdapter(mReunionListAdapter);
        this.configureToolbar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        View inputView = mSearchTimeInput;
            switch (item.getItemId()){
                case R.id.menu_activity_reunion_sorting_time:
                    mSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.hour_array, R.layout.simple_spinner_item);
                    mSpinnerAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                    mSearchTimeInput.setAdapter(mSpinnerAdapter);
                    mSearchTimeInput.setVisibility(inputView.VISIBLE);
                    mSearchBtn.setVisibility(inputView.VISIBLE);
                    refreshView();
                    return true;
                case R.id.menu_activity_Reunion_sorting_room:
                    mSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.room_array, R.layout.simple_spinner_item);
                    mSpinnerAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                    mSearchTimeInput.setAdapter(mSpinnerAdapter);
                    mSearchTimeInput.setVisibility(inputView.VISIBLE);
                    mSearchBtn.setVisibility(inputView.VISIBLE);
                    refreshView();
                    return true;
                case R.id.menu_activity_Reunion_sorting_cancel:
                    setAdapter();
                    mSearchTimeInput.setVisibility(inputView.INVISIBLE);
                    mSearchBtn.setVisibility(inputView.INVISIBLE);
                default:return super.onOptionsItemSelected(item);
            }
    }

    private void refreshView() {
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSpinnerAdapter.getItem(0).equals("A")) {
                    mReunions = mApiService.sortingByRoom(mSearchTimeInput.getSelectedItem().toString());
                }else{
                    mReunions = mApiService.sortingByTime(mSearchTimeInput.getSelectedItem().toString());
                }
                mReunionListAdapter = new ReunionListAdapter(mReunions);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                mRecyclerView.setAdapter(mReunionListAdapter);
            }
        });

    }
}