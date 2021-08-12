package com.lamzone.mareu.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import androidx.lifecycle.LiveData;

import com.lamzone.mareu.model.Member;

import java.util.List;

/**
 * Created by lleotraas on 12.
 */
@Dao
public interface MemberDao {
    @Query("SELECT * FROM Member WHERE mMeetingId = :mMeetingId ")
    LiveData<List<Member>> getMembers(long mMeetingId);

    @Insert
    long insertMember(Member member);

    @Update
    int updateMember(Member member);

    @Query("DELETE FROM Member WHERE mId = :memberId")
    int deleteMember(long memberId);
}
