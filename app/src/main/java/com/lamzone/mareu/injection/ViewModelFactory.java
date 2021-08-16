package com.lamzone.mareu.injection;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.lamzone.mareu.member_list.MemberViewModel;
import com.lamzone.mareu.repositories.MeetingDataRepository;
import com.lamzone.mareu.repositories.MemberDataRepository;

import java.util.concurrent.Executor;

/**
 * Created by lleotraas on 14.
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

    private final MemberDataRepository memberDataSource;
    private final MeetingDataRepository meetingDataSource;
    private final Executor mExecutor;

    public ViewModelFactory(MemberDataRepository memberDataSource, MeetingDataRepository meetingDataSource, Executor executor) {
        this.memberDataSource = memberDataSource;
        this.meetingDataSource = meetingDataSource;
        this.mExecutor = executor;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MemberViewModel.class)){
            return (T) new MemberViewModel(memberDataSource, meetingDataSource, mExecutor);
        }
        throw new IllegalArgumentException("Unknow ViewModel class");
    }
}
