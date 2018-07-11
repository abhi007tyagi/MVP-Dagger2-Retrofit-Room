package com.tyagiabhinav.udacitycourseviewer.ui.courseDetails;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.tyagiabhinav.udacitycourseviewer.R;
import com.tyagiabhinav.udacitycourseviewer.utils.uiUtil.ActivityUtils;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;

public class CourseDetailActivity extends DaggerAppCompatActivity {

    public static final String TAG = CourseDetailActivity.class.getSimpleName();

    @Inject
    Lazy<CourseDetailFragment> mCourseDetailFragmentProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        if (savedInstanceState == null) {
            Log.d(TAG, "onCreate: savedInstanceState == null");
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.

            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.contentFrame);
            if (fragment == null) {
                // Get the fragment from dagger
                fragment = mCourseDetailFragmentProvider.get();
            }
            fragment.setArguments(getIntent().getExtras());
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.course_detail_container);

        }
    }
}
