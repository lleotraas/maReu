package com.lamzone.mareu;

import android.widget.TimePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.lamzone.mareu.injector.DependencyInjector;
import com.lamzone.mareu.model.Reunion;
import com.lamzone.mareu.service.ReunionApiService;
import com.lamzone.mareu.utils.DeleteViewAction;
import com.lamzone.mareu.view.meeting.ReunionActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
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
public class ReunionActivityTest {

    private ReunionActivity mActivity;
    private ReunionApiService service;
    private int childCount;

    @Rule
    public ActivityTestRule<ReunionActivity> mActivityTestRule = new ActivityTestRule(ReunionActivity.class);

    @Before
    public void setUp(){
        mActivity = mActivityTestRule.getActivity();
        assertThat(mActivity, notNullValue());
        service = DependencyInjector.getReunionApiService();
        assertThat(service, notNullValue());
        Reunion reunionTest = new Reunion(-12345678, "14", "15", "B", "reunionTest", Arrays.asList("un@lamzone.com", "deux@lamzone.com", "trois@lamzone.com", "quatre@lamzone.com"));
        Reunion reunionTest1 = new Reunion(-12345678, "10", "30", "J", "reunionTest", Arrays.asList("un@lamzone.com", "deux@lamzone.com", "trois@lamzone.com", "quatre@lamzone.com"));
        service.getReunion().add(reunionTest);
        service.getReunion().add(reunionTest1);
        childCount = service.getReunion().size();
    }
    @After
    public void finish() {
        service.getReunion().removeAll(service.getReunion());
        mActivity = null;
        service = null;
    }


    @Test
    public void ReunionActivity_shouldRemoveItem(){
        onView(withId(R.id.menu_activity_Reunion_sorting))
                .perform(click());
        onView(withText("Pas de tri"))
                .perform(click());
        onView(withId(R.id.list_reunion))
                .check(matches(hasChildCount(childCount)));
        onView(allOf(withId(R.id.list_reunion),isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));
        onView(withId(R.id.list_reunion))
                .check(matches(hasChildCount(childCount - 1)));
    }

    @Test
    public void ReunionAddActivity_shouldAddItem(){
        Calendar calendar = Calendar.getInstance(Locale.FRANCE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        String firstMember = "member_test@hotmail.fr";
        String secondMember = "member_test@hotmail.fr";

        onView(withId(R.id.activity_main_fab))
                .perform(click());
        onView(withId(R.id.activity_add_reunion_hour_btn))
                .perform(click());
        onView(isAssignableFrom(TimePicker.class))
                .perform(PickerActions.setTime(hour,minute));
        onView(withText("OK"))
                .perform(click());

        //check if the text on the button is right
        onView(withId(R.id.activity_add_reunion_hour_btn))
                .check(matches(withText(String.format("%02d:%02d", hour, minute))));
        onView(withId(R.id.activity_add_reunion_room_btn))
                .perform(click());
        onView(withText("F"))
                .perform(click());
        onView(withText("OK"))
                .perform(click());

        //check if the text on the button is right
        onView(withId(R.id.activity_add_reunion_room_btn))
                .check(matches(withText("F")));
        onView(withId(R.id.activity_add_reunion_name_input))
                .perform(replaceText("reunion_Test"));
        onView(withId(R.id.activity_add_reunion_members_input))
                .perform(replaceText(firstMember));
        onView(withId(R.id.activity_add_reunion_members_btn))
                .perform(click());

        //check if text in the textView is correct
        onView(withId(R.id.activity_add_reunion_members_list_txt))
                .check(matches(withText("1." + firstMember + "\n")));
        onView(withId(R.id.activity_add_reunion_name_input))
                .perform(replaceText("reunion_Test2"));
        onView(withId(R.id.activity_add_reunion_members_input))
                .perform(replaceText(secondMember));
        onView(withId(R.id.activity_add_reunion_members_btn))
                .perform(click());

        //check if text in the textView is correct
        onView(withId(R.id.activity_add_reunion_members_list_txt))
                .check(matches(withText("1." + firstMember + "\n" + "2." + secondMember + "\n")));
        onView(withId(R.id.activity_add_reunion_validate_btn))
                .perform(click());
        onView(withId(R.id.list_reunion))
                .check(matches(hasChildCount(childCount+1)));
    }

    @Test
    public void ReunionDetailActivity_activityLaunched(){
        onView(withId(R.id.menu_activity_Reunion_sorting))
                .perform(click());
        onView(withText("Pas de tri"))
                .perform(click());
        onView(withId(R.id.list_reunion))
                .check(matches(hasChildCount(childCount)));
        onView(withId(R.id.list_reunion))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.activity_detail_reunion_member_list_txt));
        pressBack();
        onView(withId(R.id.list_reunion))
                .check(matches(hasChildCount(childCount)));
    }

    @Test
    public void ReunionActivity_hourSorted(){
        onView(withId(R.id.menu_activity_Reunion_sorting))
                .perform(click());
        onView(withText("Trier par heure"))
                .perform(click());
        onView(isAssignableFrom(TimePicker.class))
                .perform(PickerActions.setTime(10,00));
        onView(withText("OK"))
                .perform(click());
        onView(withId(R.id.list_reunion))
                .check(matches(hasChildCount(childCount - 1)));
        onView(withId(R.id.menu_activity_Reunion_sorting))
                .perform(click());
        onView(withText("Pas de tri"))
                .perform(click());
        onView(withId(R.id.list_reunion))
                .check(matches(hasChildCount(childCount)));
    }

    @Test
    public void ReunionActivity_roomSorted(){
        onView(withId(R.id.menu_activity_Reunion_sorting))
                .perform(click());
        onView(withText("Trier par salle"))
                .perform(click());
        onView(withText("B"))
                .perform(click());
        onView(withText("OK"))
                .perform(click());
        onView(withId(R.id.list_reunion))
                .check(matches(hasChildCount(childCount - 1)));
        onView(withId(R.id.menu_activity_Reunion_sorting))
                .perform(click());
        onView(withText("Pas de tri"))
                .perform(click());
        onView(withId(R.id.list_reunion))
                .check(matches(hasChildCount(childCount)));
    }
}