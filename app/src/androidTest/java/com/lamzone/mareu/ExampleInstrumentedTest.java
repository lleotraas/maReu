package com.lamzone.mareu;

import android.support.test.rule.ActivityTestRule;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.lamzone.mareu.injector.DependencyInjector;
import com.lamzone.mareu.model.Reunion;
import com.lamzone.mareu.service.ReunionApiService;
import com.lamzone.mareu.view.meeting.ReunionActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private ReunionActivity mActivity;
    private ReunionApiService service;

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
    public void ReunionActivity_shouldNotBeEmpty() {
        onView(allOf(ViewMatchers.withId(R.id.md_recyclerview_content),isDisplayed()))
                .check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void ReunionActivity_shouldRemoveItem(){

    }
}