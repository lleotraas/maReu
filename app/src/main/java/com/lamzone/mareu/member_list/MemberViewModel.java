package com.lamzone.mareu.member_list;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.lamzone.mareu.model.Meeting;
import com.lamzone.mareu.model.Member;
import com.lamzone.mareu.repositories.MeetingDataRepository;
import com.lamzone.mareu.repositories.MemberDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * Created by lleotraas on 14.
 */
public class MemberViewModel extends ViewModel {

    // REPOSITORIES
    private final MemberDataRepository memberDataSource;
    private final MeetingDataRepository meetingDataSource;
    private final Executor mExecutor;

    // DATA
    @Nullable
    private LiveData<Meeting> currentMeeting;

    public MemberViewModel(MemberDataRepository memberDataRepository, MeetingDataRepository meetingDataRepository, Executor executor) {
        memberDataSource = memberDataRepository;
        meetingDataSource = meetingDataRepository;
        mExecutor = executor;
    }

    public void init(long meetingId){
        if(this.currentMeeting != null){
            return;
        }
        currentMeeting = meetingDataSource.getMeeting(meetingId);
    }

    // --------------
    // FOR MEETING
    // --------------
    public LiveData<Meeting> getMeeting(long meetingId){
        return this.currentMeeting;
    }

    // --------------
    // FOR MEMBER
    // --------------
    public LiveData<List<Member>> getMembers(long meetingId){
        return memberDataSource.getMembers(meetingId);
    }

    public void createMember(Member member){
        mExecutor.execute(() ->{
            memberDataSource.createMember(member);
        });
    }

    public void deleteMember(long memberId){
        mExecutor.execute(() ->{
            memberDataSource.deleteMember(memberId);
        });
    }

    public void updateMember(Member member){
        mExecutor.execute(() ->{
            memberDataSource.updateMember(member);
        });
    }
}
