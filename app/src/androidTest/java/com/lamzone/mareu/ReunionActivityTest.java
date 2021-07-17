package com.lamzone.mareu;

import android.view.View;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.lamzone.mareu.injector.DependencyInjector;
import com.lamzone.mareu.model.Reunion;
import com.lamzone.mareu.service.DummyReunion;
import com.lamzone.mareu.service.ReunionApiService;
import com.lamzone.mareu.view.meeting.ReunionActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
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
    private List<Reunion> listTest;

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
        Reunion reunionTest2 = new Reunion(-12345678, "14", "45", "B", "reunionTest", Arrays.asList("un@lamzone.com", "deux@lamzone.com", "trois@lamzone.com", "quatre@lamzone.com"));
        Reunion reunionTest3 = new Reunion(-12345678, "10", "00", "J", "reunionTest", Arrays.asList("un@lamzone.com", "deux@lamzone.com", "trois@lamzone.com", "quatre@lamzone.com"));
        Reunion reunionTest4 = new Reunion(-12345678, "14", "15", "B", "reunionTest", Arrays.asList("un@lamzone.com", "deux@lamzone.com", "trois@lamzone.com", "quatre@lamzone.com"));
        service.getReunion().add(reunionTest);
        service.getReunion().add(reunionTest1);
        service.getReunion().add(reunionTest2);
        service.getReunion().add(reunionTest3);
        service.getReunion().add(reunionTest4);
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
                .check(matches(hasChildCount(1)));
        onView(withId(R.id.item_list_delete_btn))
                .perform(click());
        onView(withId(R.id.list_reunion))
                .check(matches(hasChildCount(0)));
    }

    @Test
    public void ReunionAddActivity_shouldAddItem(){
        onView(withId(R.id.activity_main_fab))
                .perform(click());
        onView(withId(R.id.activity_add_reunion_hour_btn))
                .perform(click());
        onView(withText("OK"))
                .perform(click());
        onView(withId(R.id.activity_add_reunion_room_btn))
                .perform(click());
        onView(withText("F"))
                .perform(click());
        onView(withText("OK"))
                .perform(click());
        onView(withId(R.id.activity_add_reunion_name_input))
                .perform(replaceText("reunion_Test"));
        onView(withId(R.id.activity_add_reunion_members_input))
                .perform(replaceText("member_test@hotmail.fr"));
        onView(withId(R.id.activity_add_reunion_members_btn))
                .perform(click());
        onView(withId(R.id.activity_add_reunion_name_input))
                .perform(replaceText("reunion_Test2"));
        onView(withId(R.id.activity_add_reunion_members_input))
                .perform(replaceText("member_test@gmail.com"));
        onView(withId(R.id.activity_add_reunion_members_btn))
                .perform(click());
        onView(withId(R.id.activity_add_reunion_validate_btn))
                .perform(click());
        onView(withId(R.id.list_reunion))
                .check(matches(hasChildCount(2)));
    }

    @Test
    public void ReunionDetailActivity_activityLaunched(){
        onView(withId(R.id.menu_activity_Reunion_sorting))
                .perform(click());
        onView(withText("Pas de tri"))
                .perform(click());
        onView(withId(R.id.list_reunion))
                .check(matches(hasChildCount(1)));
        onView(withId(R.id.list_reunion))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.activity_detail_reunion_member_list_txt));

    }
}