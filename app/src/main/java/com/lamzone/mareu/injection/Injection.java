package com.lamzone.mareu.injection;

import android.content.Context;

import com.lamzone.mareu.database.MeetingDatabase;
import com.lamzone.mareu.repositories.MeetingDataRepository;
import com.lamzone.mareu.repositories.MemberDataRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by lleotraas on 14.
 */
public class Injection {

    public static MemberDataRepository provideMemberDataSource(Context context){
        MeetingDatabase database = MeetingDatabase.getInstance(context);
        return new MemberDataRepository(database.mMemberDao());
    }

    public static MeetingDataRepository provideMeetingDataSource(Context context){
        MeetingDatabase database = MeetingDatabase.getInstance(context);
        return new MeetingDataRepository(database.mMeetingDao());
    }

    public static Executor provideExecutor(){
        return Executors.newSingleThreadExecutor();
    }

    public static ViewModelFactory provideViewModelFactory(Context context){
        MemberDataRepository dataSourceMember = provideMemberDataSource(context);
        MeetingDataRepository dataSourceMeeting = provideMeetingDataSource(context);
        Executor executor = provideExecutor();
        return new ViewModelFactory(dataSourceMember, dataSourceMeeting, executor);
    }
}
