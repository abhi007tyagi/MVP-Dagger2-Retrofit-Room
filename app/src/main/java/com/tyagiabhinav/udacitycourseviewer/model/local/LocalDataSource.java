package com.tyagiabhinav.udacitycourseviewer.model.local;

import android.support.annotation.NonNull;

import com.tyagiabhinav.udacitycourseviewer.model.DataSource;
import com.tyagiabhinav.udacitycourseviewer.model.local.database.Course;
import com.tyagiabhinav.udacitycourseviewer.model.local.database.CourseDAO;
import com.tyagiabhinav.udacitycourseviewer.model.local.database.CourseList;
import com.tyagiabhinav.udacitycourseviewer.model.local.database.CourseListDAO;
import com.tyagiabhinav.udacitycourseviewer.model.local.database.Instructor;
import com.tyagiabhinav.udacitycourseviewer.model.local.database.InstructorDAO;
import com.tyagiabhinav.udacitycourseviewer.model.pojo.Courses;
import com.tyagiabhinav.udacitycourseviewer.utils.executors.AppExecutor;
import com.tyagiabhinav.udacitycourseviewer.utils.modelUtil.DatabaseUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class LocalDataSource implements DataSource {

    public static final String TAG = LocalDataSource.class.getName();

    private final CourseDAO mCourseDAO;
    private final InstructorDAO mInstructorDAO;
    private final CourseListDAO mCourseListDAO;
    private final AppExecutor mAppExecutor;


    @Inject
    public LocalDataSource(@NonNull CourseDAO courseDAO, @NonNull InstructorDAO instructorDAO,
                           @NonNull CourseListDAO courseListDAO, @NonNull AppExecutor executor) {
        this.mCourseDAO = courseDAO;
        this.mInstructorDAO = instructorDAO;
        this.mCourseListDAO = courseListDAO;
        this.mAppExecutor = executor;
    }


    @Override
    public void saveCourses(@NonNull final List<Courses> coursesList) {

        Runnable saveRunnable = new Runnable() {
            @Override
            public void run() {
                for (Courses courses : coursesList) {
                    Map<String, Object> dbMap = DatabaseUtils.convertPojoToEntity(courses);

                    Course course = (Course) dbMap.get(DatabaseUtils.COURSE_KEY);
                    mCourseDAO.insert(course);

                    List<Instructor> instructors = (List<Instructor>) dbMap.get(DatabaseUtils.INSTRUCTOR_KEY);
                    for (Instructor instructor : instructors) {
                        mInstructorDAO.insert(instructor);
                    }
                }
            }
        };
        mAppExecutor.diskIO().execute(saveRunnable);
    }

    @Override
    public void getCourses(@NonNull final GetCourseList callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<CourseList> listOfCourseList = mCourseListDAO.getCourses();
                mAppExecutor.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        List<Courses> coursesList = new ArrayList<>(listOfCourseList.size());
                        for (CourseList courseList : listOfCourseList) {
                          Course course = courseList.course;
                          List<Instructor> instructors = courseList.instructors;
                          Map<String, Object> dbMap = new HashMap<>(2);
                          dbMap.put(DatabaseUtils.COURSE_KEY, course);
                          dbMap.put(DatabaseUtils.INSTRUCTOR_KEY, instructors);

                          coursesList.add(DatabaseUtils.convertEntityToPojo(dbMap));
                        }
                        callback.onCoursesFetched(coursesList);
                    }
                });
            }
        };
        mAppExecutor.diskIO().execute(runnable);
    }

    @Override
    public void getCourse(@NonNull String key, @NonNull GetCourse callback) {

    }
}
