package com.lamzone.mareu.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.lamzone.mareu.R;
import com.lamzone.mareu.injector.DependencyInjector;
import com.lamzone.mareu.model.Reunion;
import com.lamzone.mareu.service.DummyReunion;
import com.lamzone.mareu.service.ReunionApiService;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ReunionListAdapter mReunionListAdapter;
    private List<Reunion> mReunions;
    private ReunionApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mApiService = DependencyInjector.getReunionApiService();
        mReunions = mApiService.getReunion();

        mRecyclerView = findViewById(R.id.list_reunion);

        mReunionListAdapter = new ReunionListAdapter(mReunions);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setAdapter(mReunionListAdapter);
    }

}