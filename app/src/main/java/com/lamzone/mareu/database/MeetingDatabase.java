package com.lamzone.mareu.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;

import com.lamzone.mareu.database.dao.MeetingDao;
import com.lamzone.mareu.database.dao.MemberDao;
import com.lamzone.mareu.model.Meeting;
import com.lamzone.mareu.model.Member;
import com.lamzone.mareu.service.MeetingApiService;

/**
 * Created by lleotraas on 12.
 */
@Database(entities =  {Member.class, Meeting.class}, version = 1, exportSchema = false)
public abstract class MeetingDatabase extends RoomDatabase {

    private static volatile MeetingDatabase INSTANCE;
    public abstract MeetingDao mMeetingDao();
    public abstract MemberDao mMemberDao();

    public static MeetingDatabase getInstance(Context context){
        if (INSTANCE == null){
            synchronized (MeetingDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MeetingDatabase.class, "MyDatabase.db")
                            .addCallback(prepopulateDatabase())
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback prepopulateDatabase() {
        return new Callback() {

            @Override
            public void onCreate(SupportSQLiteDatabase db) {
                super.onCreate(db);
                ContentValues contentValues = new ContentValues();
                contentValues.put("mId", 1);
                contentValues.put("mColor", 254);
                contentValues.put("mHour", "15");
                contentValues.put("mMinute", "35");
                contentValues.put("mRoom", "C");
                contentValues.put("mName", "lleotraas");
                db.insert("Meeting", OnConflictStrategy.IGNORE, contentValues);
            }
        };
    }

}
