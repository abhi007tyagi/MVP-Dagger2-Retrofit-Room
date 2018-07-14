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

        if (savedInstanceState == null) {
            Log.d(TAG, "onCreate: savedInstanceState == null");

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
