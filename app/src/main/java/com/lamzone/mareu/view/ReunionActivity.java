package com.lamzone.mareu.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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

    private RecyclerView mRecyclerView;
    private ReunionListAdapter mReunionListAdapter;
    private List<Reunion> mReunions;
    private ReunionApiService mApiService;
    private FloatingActionButton mAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reunion);
        mAddButton = findViewById(R.id.activity_main_fab);

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
        mReunions = mApiService.getReunion();
        mReunionListAdapter = new ReunionListAdapter(mReunions);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setAdapter(mReunionListAdapter);
        this.configureToolbar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()){
                case R.id.menu_activity_reunion_sorting_time:

                    return true;
                case R.id.menu_activity_Reunion_sorting_room:

                    return true;
                case R.id.menu_activity_Reunion_sorting_cancel:
                    setAdapter();

                default:return super.onOptionsItemSelected(item);
            }
    }
}