package com.lamzone.mareu;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;

import androidx.test.platform.app.InstrumentationRegistry;

import com.lamzone.mareu.database.MeetingDatabase;
import com.lamzone.mareu.model.Meeting;
import com.lamzone.mareu.utils.LiveDataTestUtility;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertTrue;

/**
 * Created by lleotraas on 12.
 */
@RunWith(JUnit4.class)
public class MemberDaoTest {

    private MeetingDatabase mDatabase;
    private static long MEETING_ID = 1;
    private static Meeting MEETING_DEMO = new Meeting(MEETING_ID, 212, "08", "12", "J", "carlos");

    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception{
        this.mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(),
                MeetingDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb()throws Exception{
        mDatabase.close();
    }

    @Test
    public void insertAndGetUser() throws InterruptedException {
        this.mDatabase.mMeetingDao().createMeeting(MEETING_DEMO);
        Meeting meeting = LiveDataTestUtility.getValue(this.mDatabase.mMeetingDao().getMeeting(MEETING_ID));
        assertTrue(meeting.getName().equals(MEETING_DEMO.getName()) && meeting.getId() == MEETING_ID);
    }
}
