package com.lamzone.mareu.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reunion);
        Log.d(TAG, "onCreate: c'est créé quoi");

        mAddButton = findViewById(R.id.activity_main_fab);

        mApiService = DependencyInjector.getReunionApiService();
        mReunions = mApiService.getReunion();

        mRecyclerView = findViewById(R.id.list_reunion);



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
        Toolbar toolbar = findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: c'est résumé");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: c'est starté");
        mReunionListAdapter = new ReunionListAdapter(mReunions);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setAdapter(mReunionListAdapter);
        this.configureToolbar();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: c'est stoppé");
    }
}