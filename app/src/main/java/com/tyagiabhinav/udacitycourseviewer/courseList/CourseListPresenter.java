package com.tyagiabhinav.udacitycourseviewer.courseList;


import com.tyagiabhinav.udacitycourseviewer.di.ActivityScope;
import com.tyagiabhinav.udacitycourseviewer.model.CourseRepository;
import com.tyagiabhinav.udacitycourseviewer.model.DataSource;
import com.tyagiabhinav.udacitycourseviewer.model.pojo.Courses;

import java.util.List;

import javax.inject.Inject;

@ActivityScope
final class CourseListPresenter implements CourseListContract.Presenter {

    private final CourseRepository mCourseRepository;

    private CourseListContract.View mCourseView;

    @Inject
    CourseListPresenter(CourseRepository courseRepository) {
        mCourseRepository = courseRepository;
    }

    @Override
    public void loadCouses() {
        mCourseRepository.getCourses(new DataSource.GetCourseList() {
            @Override
            public void onCoursesFetched(List<Courses> coursesList) {

            }

            @Override
            public void onFetchFailure() {

            }
        });
    }

    @Override
    public void onCourseSelected(Courses selectedCourse) {

    }

    @Override
    public void takeView(CourseListContract.View view) {

    }

    @Override
    public void dropView() {

    }
}
