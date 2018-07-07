package com.tyagiabhinav.udacitycourseviewer.courseList;

import com.tyagiabhinav.udacitycourseviewer.pojo.Courses;

import javax.inject.Inject;

final class CourseListPresenter implements CourseListContract.Presenter {


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