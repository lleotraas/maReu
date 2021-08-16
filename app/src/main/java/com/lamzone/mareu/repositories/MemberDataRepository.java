package com.lamzone.mareu.repositories;

import androidx.lifecycle.LiveData;

import com.lamzone.mareu.database.dao.MemberDao;
import com.lamzone.mareu.model.Member;

import java.util.List;

/**
 * Created by lleotraas on 14.
 */
public class MemberDataRepository {
    private final MemberDao mMemberDao;

    public MemberDataRepository(MemberDao memberDao) {
        mMemberDao = memberDao;
    }

    // --- GET ---
    public LiveData<List<Member>> getMembers(long meetingId){
        return this.mMemberDao.getMembers(meetingId);
    }

    // --- CREATE ---
    public void createMember(Member member){
        mMemberDao.insertMember(member);
    }

    // --- DELETE ---
    public void deleteMember(long memberId){
        mMemberDao.deleteMember(memberId);
    }

    // --- UPDATE ---
    public void updateMember(Member member){
        mMemberDao.updateMember(member);
    }
}
