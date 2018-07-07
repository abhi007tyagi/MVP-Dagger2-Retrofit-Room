package com.tyagiabhinav.udacitycourseviewer.courseList;

import com.tyagiabhinav.udacitycourseviewer.BasePresenter;
import com.tyagiabhinav.udacitycourseviewer.BaseView;

import java.util.List;

import com.tyagiabhinav.udacitycourseviewer.pojo.Courses;

/**
 * contract between the view and the presenter.
 */

public interface CourseListContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showCourses(List<Courses> courseList);

        void showLoadingCourseError();

        void showNoCourse();
    }

    interface Presenter extends BasePresenter<View> {

        void loadCouses();

        void onCourseSelected(Courses selectedCourse);
    }
}