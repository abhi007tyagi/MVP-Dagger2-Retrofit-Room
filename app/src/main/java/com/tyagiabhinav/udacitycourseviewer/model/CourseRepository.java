package com.tyagiabhinav.udacitycourseviewer.model;

import android.support.annotation.NonNull;

import com.tyagiabhinav.udacitycourseviewer.model.pojo.Courses;
import com.tyagiabhinav.udacitycourseviewer.utils.OnlineChecker;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CourseRepository implements DataSource {

    private final DataSource mRemoteDataSource;

    private final DataSource mLocalDataSource;

    @Inject
    CourseRepository(@Remote DataSource remoteDataSource,
                     @Local DataSource localDataSource) {
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = localDataSource;
    }

    @Inject
    OnlineChecker onlineChecker;


    @Override
    public void saveCourses(@NonNull List<Courses> coursesList) {}

    @Override
    public void getCourses(@NonNull GetCourseList callback) {

//        if (onlineChecker.isOnline()) {
//            // fetch cources from remote server
//            mRemoteDataSource.getCourses(new GetCourseList() {
//                @Override
//                public void onCoursesFetched(List<Courses> coursesList) {
//
//                }
//
//                @Override
//                public void onFetchFailure() {
//                    // fetch from local database
//                    getCoursesFromDatabase();
//                }
//            });
//        } else {
//            // offline... get from local database
//            getCoursesFromDatabase();
//        }

    }

    @Override
    public void getCourse(@NonNull String key, @NonNull GetCourse callback) {

    }

    private void getCoursesFromDatabase() {
        mLocalDataSource.getCourses(new GetCourseList() {
            @Override
            public void onCoursesFetched(List<Courses> coursesList) {

            }

            @Override
            public void onFetchFailure() {
                // show No Data message
            }
        });
    }
}
