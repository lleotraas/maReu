package com.lamzone.mareu.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.lamzone.mareu.R;
import com.lamzone.mareu.injector.DependencyInjector;
import com.lamzone.mareu.model.Reunion;
import com.lamzone.mareu.service.ReunionApiService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lleotraas on 06.
 */
public class AddReunion extends AppCompatActivity {

    private Spinner mHourSpinner;
    private Spinner mMinuteSpinner;
    private Spinner mRoomSpinner;
    private EditText mNameInput;
    private EditText mMemberInput;
    private Button mAddMemberBtn;
    private TextView mMemberListTxt;
    private Button mValidateBtn;
    private ArrayAdapter<CharSequence> spinnerAdapter;
    private List<String> mMembers;
    private int mIndex;

    private ReunionApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_reunion);

        mApiService = DependencyInjector.getReunionApiService();

        mHourSpinner = findViewById(R.id.activity_add_reunion_hour_spinner);
        mMinuteSpinner = findViewById(R.id.activity_add_reunion_minute_spinner);
        mRoomSpinner = findViewById(R.id.activity_add_reunion_room_spinner);
        mNameInput = findViewById(R.id.activity_add_reunion_name_input);
        mMemberInput = findViewById(R.id.activity_add_reunion_members_input);
        mAddMemberBtn = findViewById(R.id.activity_add_reunion_members_btn);
        mMemberListTxt = findViewById(R.id.activity_add_reunion_members_list_txt);
        mValidateBtn = findViewById(R.id.activity_add_reunion_validate_btn);
        mIndex = 0;

        mMembers = new ArrayList<>();
        mValidateBtn.setEnabled(false);
        setSpinnerAdapter();
        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mValidateBtn.setEnabled(s.length() > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        addMember();
        validateReunion();
    }

    private void setSpinnerAdapter() {
        spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.room_array, R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        mRoomSpinner.setAdapter(spinnerAdapter);

        spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.hour_array, R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        mHourSpinner.setAdapter(spinnerAdapter);

        spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.minute_array, R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        mMinuteSpinner.setAdapter(spinnerAdapter);

    }

    private void addMember() {
        mAddMemberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMembers.add(mMemberInput.getText().toString());
                String member = mMemberListTxt.getText().toString();
                member += (++mIndex) + "." + mMemberInput.getText().toString();
                mMemberListTxt.setText(member + "\n");
                mMemberInput.setText("");
                mMemberListTxt.setMovementMethod(new ScrollingMovementMethod());
            }
        });
    }

    private void validateReunion() {
        mValidateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reunion reunion = new Reunion(
                        System.currentTimeMillis(),
                        mRoomSpinner.getSelectedItem().toString(),
                       mHourSpinner.getSelectedItem().toString() + "H" + mMinuteSpinner.getSelectedItem().toString(),
                        mNameInput.getText().toString(),
                        mMembers
                );
                mApiService.addReunion(reunion);
                finish();
            }
        });
    }
}
