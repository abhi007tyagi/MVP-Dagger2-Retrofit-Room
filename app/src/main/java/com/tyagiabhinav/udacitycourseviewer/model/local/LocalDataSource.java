package com.tyagiabhinav.udacitycourseviewer.model.local;

import android.support.annotation.NonNull;

import com.tyagiabhinav.udacitycourseviewer.model.DataSource;
import com.tyagiabhinav.udacitycourseviewer.model.local.database.CourseDAO;
import com.tyagiabhinav.udacitycourseviewer.model.local.database.CourseListDAO;
import com.tyagiabhinav.udacitycourseviewer.model.local.database.InstructorDAO;
import com.tyagiabhinav.udacitycourseviewer.model.pojo.Courses;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class LocalDataSource implements DataSource {

    public static final String TAG = LocalDataSource.class.getName();

    private final CourseDAO mCourseDAO;
    private final InstructorDAO mInstructorDAO;
    private final CourseListDAO mCourseListDAO;


    @Inject
    public LocalDataSource(@NonNull CourseDAO courseDAO, @NonNull InstructorDAO instructorDAO, @NonNull CourseListDAO courseListDAO) {
        this.mCourseDAO = courseDAO;
        this.mInstructorDAO = instructorDAO;
        this.mCourseListDAO = courseListDAO;
    }


    @Override
    public void saveCourses(@NonNull List<Courses> coursesList) {

    }

    @Override
    public void getCourses(@NonNull GetCourseList callback) {

    }

    @Override
    public void getCourse(@NonNull String key, @NonNull GetCourse callback) {

    }
}
