package com.tyagiabhinav.udacitycourseviewer.utils;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.tyagiabhinav.udacitycourseviewer.R;
import com.tyagiabhinav.udacitycourseviewer.ui.courseList.CourseListActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.content.Context.WINDOW_SERVICE;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.tyagiabhinav.udacitycourseviewer.utils.OrientationChangeAction.orientationLandscape;
import static com.tyagiabhinav.udacitycourseviewer.utils.OrientationChangeAction.orientationPortrait;
import static org.hamcrest.core.AllOf.allOf;


@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUdacityCourseViewerUI {

    private static final String TAG = TestUdacityCourseViewerUI.class.getSimpleName();


    @Rule
    public ActivityTestRule<CourseListActivity> mActivityRule = new ActivityTestRule<>(CourseListActivity.class, true, true);


    /**
     * Test activity items visibility on launch of app
     */
    @Test
    public void test01_UIViewsPresenceOnLoad() {
        Log.d(TAG, "running test01_UIViewsPresenceOnLoad..");

        // checks if app bar is present
        onView(ViewMatchers.withId(R.id.app_bar)).check(matches(isDisplayed()));

        // checks if toolbar is present
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));

        // checks if content frame for list is present
        onView(withId(R.id.contentFrame)).check(matches(isDisplayed()));

        // checks if recycler view is visible
        onView(withId(R.id.courseListRecyclerView)).check(matches(isDisplayed()));
    }

    /**
     * Test activity rotation
     */
    @Test
    public void test02_RotateDeviceToLandscape() {
        Log.d(TAG, "running test02_RotateDeviceToLandscape..");

//
        Context context = InstrumentationRegistry.getTargetContext();
        onView(isRoot()).perform(orientationLandscape());

        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        int widthInDP = Math.round(dm.widthPixels / dm.density);

        if (widthInDP >= 820) {
            Log.d(TAG, widthInDP + " >= 820");
            // checks if new container is visible
            onView(withId(R.id.course_detail_container)).check(matches(isDisplayed()));

        } else {
            Log.d(TAG, widthInDP + " < 820");
            // rotate back
            onView(isRoot()).perform(orientationPortrait());

            // added for slowing the test for humans to see
            sleep(2000);
        }
    }

    /**
     * Test to scroll the list and select an item
     */
    @Test
    public void test03_ScrollAndSelectItem() {

        // scroll recycler view
        onView(withId(R.id.courseListRecyclerView)).perform(RecyclerViewActions.scrollToPosition(16));

        // click the 16th entry
        onView(withId(R.id.courseListRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(15, click()));

        test04_UIViewListItemPresenceOnLoad();

    }

    /**
     * Test fields visibility
     */
    private void test04_UIViewListItemPresenceOnLoad() {

        // checks if title is present
        onView(withId(R.id.title)).check(matches(isDisplayed()));

        // checks if level is present
        onView(withId(R.id.level)).check(matches(isDisplayed()));

        // checks if duration is present
        onView(withId(R.id.duration)).check(matches(isDisplayed()));

        // checks if summary is present
        onView(withId(R.id.summary)).check(matches(isDisplayed()));


        test05_UIViewInstructorsPresenceOnLoad();
    }

    /**
     * Test view pager items and scrolling
     */
    private void test05_UIViewInstructorsPresenceOnLoad() {

        // checks if pager is present
        onView(withId(R.id.instructorsPager)).check(matches(isDisplayed()));

        // checks if profile pic is present
        onView(allOf(withId(R.id.profilePic), isDescendantOfA(firstChildOf(withId(R.id.instructorsPager))))).check(matches(isDisplayed()));

        // checks if name is present
        onView(allOf(withId(R.id.instructorName), isDescendantOfA(firstChildOf(withId(R.id.instructorsPager))))).check(matches(isDisplayed()));

        // checks if bio is present
        onView(allOf(withId(R.id.bio), isDescendantOfA(firstChildOf(withId(R.id.instructorsPager))))).check(matches(isDisplayed()));

        // swipe left
        onView(withId(R.id.instructorsPager)).perform(swipeLeft());

        // added for slowing the test for humans to see
        sleep(2000);

        // swipe left
        onView(withId(R.id.instructorsPager)).perform(swipeRight());

        // added for slowing the test for humans to see
        sleep(2000);
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Matcher<View> firstChildOf(final Matcher<View> parentMatcher) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("with first child view of type parentMatcher");
            }

            @Override
            public boolean matchesSafely(View view) {

                if (!(view.getParent() instanceof ViewGroup)) {
                    return parentMatcher.matches(view.getParent());
                }
                ViewGroup group = (ViewGroup) view.getParent();
                return parentMatcher.matches(view.getParent()) && group.getChildAt(0).equals(view);

            }
        };
    }
}