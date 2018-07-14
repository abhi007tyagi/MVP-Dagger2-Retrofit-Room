package com.tyagiabhinav.udacitycourseviewer.ui.courseList;


import android.support.annotation.NonNull;
import android.util.Log;

import com.tyagiabhinav.udacitycourseviewer.di.ActivityScope;
import com.tyagiabhinav.udacitycourseviewer.model.CourseRepository;
import com.tyagiabhinav.udacitycourseviewer.model.DataSource;
import com.tyagiabhinav.udacitycourseviewer.model.pojo.Courses;

import java.util.List;

import javax.inject.Inject;

@ActivityScope
final class CourseListPresenter implements CourseListContract.Presenter {

    private static final String TAG = CourseListPresenter.class.getSimpleName();

    private final CourseRepository mCourseRepository;
    private CourseListContract.View mCourseView;


    @Inject
    CourseListPresenter(CourseRepository courseRepository) {
        mCourseRepository = courseRepository;
    }

    @Override
    public void loadCourses(@NonNull boolean fromDB) {
        mCourseView.setLoadingIndicator(true);

        mCourseRepository.getCourses(fromDB, new DataSource.GetCourseList() {
            @Override
            public void onCoursesFetched(List<Courses> coursesList) {
                if (coursesList.size() > 0) {
                    Log.d(TAG, "onCoursesFetched");
                    mCourseView.showCourses(coursesList);
                } else if (fromDB) {
                    mCourseView.showCourseLoadError();
                }
                mCourseView.setLoadingIndicator(false);
            }

            @Override
            public void onFetchFailure() {
                Log.w(TAG, "onFetchFailure");
                if (fromDB) {
                    mCourseView.showCourseLoadError();
                } else {
                    mCourseView.showNoCourse();
                }
                mCourseView.setLoadingIndicator(false);
            }
        });
    }

    @Override
    public void onCourseSelected(Courses selectedCourse, int position) {
        mCourseView.onCourseSelected(selectedCourse, position);
    }

    @Override
    public void takeView(CourseListContract.View view) {
        this.mCourseView = view;
    }

    @Override
    public void dropView() {
        this.mCourseView = null;
    }

}
