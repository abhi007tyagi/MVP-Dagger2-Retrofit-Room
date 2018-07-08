package com.tyagiabhinav.udacitycourseviewer.ui.courseList;

import android.os.Bundle;

import com.tyagiabhinav.udacitycourseviewer.R;
import com.tyagiabhinav.udacitycourseviewer.utils.ActivityUtils;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;

public class CourseListActivity extends DaggerAppCompatActivity {

    @Inject
    CourseListPresenter mCourseListPresenter;
    @Inject
    Lazy<CourseListFragment> mCourseListFragmentProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        CourseListFragment courseListFragment =
                (CourseListFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (courseListFragment == null) {
            // Get the fragment from dagger
            courseListFragment = mCourseListFragmentProvider.get();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), courseListFragment, R.id.contentFrame);
        }


//        // Load previously saved state, if available.
//        if (savedInstanceState != null) {
//            TasksFilterType currentFiltering =
//                    (TasksFilterType) savedInstanceState.getSerializable(CURRENT_FILTERING_KEY);
//            mTasksPresenter.setFiltering(currentFiltering);
//        }
    }
}
