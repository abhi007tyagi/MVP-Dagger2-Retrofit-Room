package com.tyagiabhinav.udacitycourseviewer.courseList;

import android.os.Bundle;

import com.tyagiabhinav.udacitycourseviewer.R;

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
    }
}
