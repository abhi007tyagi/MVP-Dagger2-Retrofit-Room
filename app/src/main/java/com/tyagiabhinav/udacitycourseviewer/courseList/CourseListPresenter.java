package com.tyagiabhinav.udacitycourseviewer.courseList;

import javax.annotation.Nullable;
import javax.inject.Inject;

import com.tyagiabhinav.udacitycourseviewer.pojo.Courses;

final class CourseListPresenter implements CourseListContract.Presenter {


    @Nullable
    private CourseListContract.View mCourseView;

    @Inject
    CourseListPresenter(){
    }

    @Override
    public void loadCouses() {
//        mCourseRepository.getCourses(new DataSource.GetCourseList() {
//            @Override
//            public void onCoursesFetched(List<Courses> coursesList) {
//
//            }
//
//            @Override
//            public void onFetchFailure() {
//
//            }
//        });
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