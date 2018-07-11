package com.tyagiabhinav.udacitycourseviewer.ui.courseList;

import com.tyagiabhinav.udacitycourseviewer.BasePresenter;
import com.tyagiabhinav.udacitycourseviewer.BaseView;
import com.tyagiabhinav.udacitycourseviewer.model.pojo.Courses;

import java.util.List;

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

        void loadCourses();

        void onCourseSelected(Courses selectedCourse);

        void takeView(View view);
    }
}