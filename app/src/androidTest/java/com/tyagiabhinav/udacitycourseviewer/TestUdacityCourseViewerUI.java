package com.tyagiabhinav.udacitycourseviewer;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.tyagiabhinav.udacitycourseviewer.ui.courseList.CourseListActivity;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;


@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUdacityCourseViewerUI {

    private static final String TAG = TestUdacityCourseViewerUI.class.getSimpleName();

    @Rule
    public ActivityTestRule<CourseListActivity> mActivityRule = new ActivityTestRule<>(CourseListActivity.class);

    /**
     * Test activity_main items visibility on launch of app
     */
    @Test
    public void test01_UIViewsPresenceOnLoad() {
    }
}