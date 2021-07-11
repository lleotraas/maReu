package com.lamzone.mareu.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

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
    private MenuItem sortingTime;
    private LinearLayout mLinearLayout;
    private EditText mSearchTimeInput;
    private ImageButton mSearchTimeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reunion);
        mAddButton = findViewById(R.id.activity_main_fab);
        mSearchTimeInput = findViewById(R.id.menu_activity_Reunion_sorting_input);
        mApiService = DependencyInjector.getReunionApiService();
        mReunions = mApiService.getReunion();
        mRecyclerView = findViewById(R.id.list_reunion);
        mReunionListAdapter = new ReunionListAdapter(mReunions);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setAdapter(mReunionListAdapter);
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
        mReunionListAdapter = new ReunionListAdapter(mReunions);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setAdapter(mReunionListAdapter);
        this.configureToolbar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        View view = mSearchTimeInput;
            switch (item.getItemId()){
                case R.id.menu_activity_reunion_sorting_time:
                    //TODO
                    mSearchTimeInput.setVisibility(view.VISIBLE);
                    mSearchTimeInput.setHint("Entrez une heure");
                    mSearchTimeInput.setHintTextColor(Color.WHITE);
                    return true;
                case R.id.menu_activity_Reunion_sorting_room:
                    //TODO
                    mSearchTimeInput.setVisibility(view.VISIBLE);
                    mSearchTimeInput.setHint("Entrez le nom de la salle");
                    mSearchTimeInput.setHintTextColor(Color.WHITE);
                    return true;
                default:return super.onOptionsItemSelected(item);
            }
    }
}