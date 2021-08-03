package com.lamzone.mareu;

import android.widget.TimePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.lamzone.mareu.injector.DependencyInjector;
import com.lamzone.mareu.service.MeetingApiService;
import com.lamzone.mareu.utils.DeleteViewAction;
import com.lamzone.mareu.view.meeting.MeetingListActivity;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.Calendar;
import java.util.Locale;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MeetingListActivityTest {

    private MeetingListActivity mActivity;
    private MeetingApiService service;
    private int childCount;

    @Rule
    public ActivityTestRule<MeetingListActivity> mActivityTestRule = new ActivityTestRule(MeetingListActivity.class);

    @Before
    public void setUp(){
        mActivity = mActivityTestRule.getActivity();
        assertThat(mActivity, notNullValue());
        service = DependencyInjector.getMeetingApiService();
        assertThat(service, notNullValue());
        childCount = service.getMeeting().size();
    }

    /**
     * We add an item in an empty list then we add a second item with the same method for the next tests
     */
    @Test
    public void test1_MeetingAddActivity_shouldAddItem(){
        Calendar calendar = Calendar.getInstance(Locale.FRANCE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        String firstMember = "member_test@hotmail.fr";
        String secondMember = "member_test@hotmail.fr";

        onView(withId(R.id.activity_main_fab))
                .perform(click());
        onView(withId(R.id.activity_add_meeting_choose_time_input))
                .perform(click());
        onView(isAssignableFrom(TimePicker.class))
                .perform(PickerActions.setTime(hour,minute));
        onView(withText("OK"))
                .perform(click());

        //check if the text on the button is right
        onView(withId(R.id.activity_add_meeting_choose_time_input))
                .check(matches(withText(String.format("Heure : %02d:%02d", hour, minute))));
        onView(withId(R.id.activity_add_meeting_room_input))
                .perform(click());
        onView(withText("F"))
                .perform(click());
        onView(withText("OK"))
                .perform(click());

        //check if the text on the button is right
        onView(withId(R.id.activity_add_meeting_room_input))
                .check(matches(withText("Salle F")));
        onView(withId(R.id.activity_add_meeting_name_input))
                .perform(replaceText("reunion_Test"));
        onView(withId(R.id.activity_add_meeting_members_input))
                .perform(replaceText(firstMember));
        onView(withId(R.id.activity_add_meeting_members_btn))
                .perform(click());

        //check if text in the textView is correct
        onView(withId(R.id.activity_add_meeting_members_list_txt))
                .check(matches(withText("1." + firstMember + "\n")));
        onView(withId(R.id.activity_add_meeting_name_input))
                .perform(replaceText("reunion_Test2"));
        onView(withId(R.id.activity_add_meeting_members_input))
                .perform(replaceText(secondMember));
        onView(withId(R.id.activity_add_meeting_members_btn))
                .perform(click());

        //check if text in the textView is correct
        onView(withId(R.id.activity_add_meeting_members_list_txt))
                .check(matches(withText("1." + firstMember + "\n" + "2." + secondMember + "\n")));
        onView(withId(R.id.activity_add_meeting_validate_btn))
                .perform(click());
        addMeetingForTest();
        onView(withId(R.id.list_meeting))
                .check(matches(hasChildCount(childCount+2)));
    }

    /**
     *  We verifify when we click on an item in the recycler view that launch a new activity
     */
    @Test
    public void test2_MeetingDetailActivity_activityLaunched(){
        onView(withId(R.id.list_meeting))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.activity_detail_meeting_member_list_txt));
        pressBack();
        onView(withId(R.id.list_meeting))
                .check(matches(hasChildCount(childCount)));
    }

    /**
     * We ensure the hour filter show us only one item of two
     */
    @Test
    public void test3_MeetingActivity_hourSorted(){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        onView(withId(R.id.menu_activity_meeting_sorting))
                .perform(click());
        onView(withText("Trier par heure"))
                .perform(click());
        onView(isAssignableFrom(TimePicker.class))
                .perform(PickerActions.setTime(hour, 0));
        onView(withText("OK"))
                .perform(click());
        onView(withId(R.id.list_meeting))
                .check(matches(hasChildCount(childCount - 1)));
        onView(withId(R.id.menu_activity_meeting_sorting))
                .perform(click());
        onView(withText("Pas de tri"))
                .perform(click());
        onView(withId(R.id.list_meeting))
                .check(matches(hasChildCount(childCount)));
    }

    /**
     * We ensure the room filter show us only one item of two
     */
    @Test
    public void test4_MeetingActivity_roomSorted(){
        onView(withId(R.id.menu_activity_meeting_sorting))
                .perform(click());
        onView(withText("Trier par salle"))
                .perform(click());
        onView(withText("B"))
                .perform(click());
        onView(withText("OK"))
                .perform(click());
        onView(withId(R.id.list_meeting))
                .check(matches(hasChildCount(childCount - 1)));
        onView(withId(R.id.menu_activity_meeting_sorting))
                .perform(click());
        onView(withText("Pas de tri"))
                .perform(click());
        onView(withId(R.id.list_meeting))
                .check(matches(hasChildCount(childCount)));
    }

    /**
     * We ensure the clicked item is deleted
     */
    @Test
    public void test5_MeetingActivity_shouldRemoveItem(){
        onView(allOf(withId(R.id.list_meeting),isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));
        onView(withId(R.id.list_meeting))
                .check(matches(hasChildCount(childCount - 1)));
    }

    /**
     * Same method as the test1 with different hour and room
     */
    private void addMeetingForTest(){
        Calendar calendar = Calendar.getInstance(Locale.FRANCE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        String firstMember = "member_test@hotmail.fr";
        String secondMember = "member_test@hotmail.fr";

        onView(withId(R.id.activity_main_fab))
                .perform(click());
        onView(withId(R.id.activity_add_meeting_choose_time_input))
                .perform(click());
        onView(isAssignableFrom(TimePicker.class))
                .perform(PickerActions.setTime(hour + 1,minute));
        onView(withText("OK"))
                .perform(click());
        onView(withId(R.id.activity_add_meeting_room_input))
                .perform(click());
        onView(withText("B"))
                .perform(click());
        onView(withText("OK"))
                .perform(click());
        onView(withId(R.id.activity_add_meeting_name_input))
                .perform(replaceText("reunion_Test"));
        onView(withId(R.id.activity_add_meeting_members_input))
                .perform(replaceText(firstMember));
        onView(withId(R.id.activity_add_meeting_members_btn))
                .perform(click());
        onView(withId(R.id.activity_add_meeting_name_input))
                .perform(replaceText("reunion_Test2"));
        onView(withId(R.id.activity_add_meeting_members_input))
                .perform(replaceText(secondMember));
        onView(withId(R.id.activity_add_meeting_members_btn))
                .perform(click());
        onView(withId(R.id.activity_add_meeting_validate_btn))
                .perform(click());
    }
}