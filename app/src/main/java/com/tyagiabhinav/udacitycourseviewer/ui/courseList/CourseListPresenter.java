package com.tyagiabhinav.udacitycourseviewer.ui.courseList;


import android.util.Log;

import com.tyagiabhinav.udacitycourseviewer.di.ActivityScope;
import com.tyagiabhinav.udacitycourseviewer.model.CourseRepository;
import com.tyagiabhinav.udacitycourseviewer.model.DataSource;
import com.tyagiabhinav.udacitycourseviewer.model.pojo.Courses;

import java.util.List;

import javax.inject.Inject;

@ActivityScope
final class CourseListPresenter implements CourseListContract.Presenter {

    public static final String TAG = CourseListPresenter.class.getSimpleName();

    private final CourseRepository mCourseRepository;
    private CourseListContract.View mCourseView;


    @Inject
    CourseListPresenter(CourseRepository courseRepository) {
        mCourseRepository = courseRepository;
    }

    @Override
    public void loadCourses() {
        mCourseView.setLoadingIndicator(true);
        mCourseRepository.getCourses(new DataSource.GetCourseList() {
            @Override
            public void onCoursesFetched(List<Courses> coursesList) {
                Log.d(TAG, "onCoursesFetched");
                Log.d(TAG, "onCoursesFetched: " + coursesList.get(0).getTitle());
                Log.d(TAG, "onCoursesFetched: " + mCourseView);
                mCourseView.showCourses(coursesList);
                mCourseView.setLoadingIndicator(false);
            }

            @Override
            public void onFetchFailure() {
                Log.w(TAG, "onFetchFailure");
                mCourseView.showNoCourse();
                mCourseView.setLoadingIndicator(false);
            }
        });
    }

    @Override
    public void onCourseSelected(Courses selectedCourse) {

    }

    @Override
    public void takeView(CourseListContract.View view) {
        this.mCourseView = view;
//        loadCourses();
    }

    @Override
    public void dropView() {
        this.mCourseView = null;
    }
}
