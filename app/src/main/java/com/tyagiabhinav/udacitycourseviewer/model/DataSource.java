package com.tyagiabhinav.udacitycourseviewer.model;

import android.support.annotation.NonNull;

import com.tyagiabhinav.udacitycourseviewer.model.pojo.Courses;

import java.util.List;

public interface DataSource {

    interface GetCourseList {

        void onCoursesFetched(List<Courses> coursesList);

        void onFetchFailure();
    }

    interface GetCourse {

        void onCourseFetched(Courses courses);

        void onFetchFailure();
    }

//    List<Courses> extractCourses (ApiResponse apiResponse);

    void saveCourses(@NonNull List<Courses> coursesList);

    void getCourses(@NonNull GetCourseList callback);

    void getCourse(@NonNull String key, @NonNull GetCourse callback);

}
