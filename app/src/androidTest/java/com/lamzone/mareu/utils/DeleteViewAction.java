package com.lamzone.mareu.utils;


import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import com.lamzone.mareu.R;

import org.hamcrest.Matcher;

/**
 * Created by lleotraas on 15.
 */
public class DeleteViewAction implements ViewAction {

    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void perform(UiController uiController, View view) {
        View button = view.findViewById(R.id.item_list_delete_btn);
        button.performClick();
    }
}
