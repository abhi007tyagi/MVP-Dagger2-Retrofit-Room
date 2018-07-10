package com.tyagiabhinav.udacitycourseviewer.ui.courseList;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.tyagiabhinav.udacitycourseviewer.R;
import com.tyagiabhinav.udacitycourseviewer.model.pojo.Courses;
import com.tyagiabhinav.udacitycourseviewer.utils.uiUtil.ActivityUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;

public class CourseListActivity extends DaggerAppCompatActivity implements CourseListFragment.OnFragmentInteractionListener {

    public static final String TAG = CourseListActivity.class.getSimpleName();

    @Inject
    CourseListPresenter mCourseListPresenter;

    @Inject
    Lazy<CourseListFragment> mCourseListFragmentProvider;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        Log.d(TAG, "onCreate: toolbar set");
        toolbar.setTitle(getTitle());

        CourseListFragment courseListFragment = (CourseListFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (courseListFragment == null) {
            // Get the fragment from dagger
            courseListFragment = mCourseListFragmentProvider.get();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), courseListFragment, R.id.contentFrame);
        }


//        // Load previously saved state, if available.
//        if (savedInstanceState != null) {
//            TasksFilterType currentFiltering =
//                    (TasksFilterType) savedInstanceState.getSerializable(CURRENT_FILTERING_KEY);
//            mTasksPresenter.setFiltering(currentFiltering);
//        }
    }

    @Override
    public void onFragmentInteraction(Courses selectedCourse) {
        Log.d(TAG, "onFragmentInteraction: "+selectedCourse.getTitle());
    }
}
