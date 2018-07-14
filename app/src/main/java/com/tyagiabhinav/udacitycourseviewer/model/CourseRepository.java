package com.tyagiabhinav.udacitycourseviewer.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.tyagiabhinav.udacitycourseviewer.model.pojo.Courses;
import com.tyagiabhinav.udacitycourseviewer.utils.networkUtil.OnlineChecker;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CourseRepository implements DataSource {

    public static final String TAG = CourseRepository.class.getSimpleName();

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
    public void saveCourses(@NonNull List<Courses> coursesList) {
        mLocalDataSource.saveCourses(coursesList);
    }

    @Override
    public void getCourses(@NonNull boolean fromDB, @NonNull final GetCourseList callback) {

        if (onlineChecker.isOnline() && !fromDB) {
            // fetch cources from remote server
            mRemoteDataSource.getCourses(fromDB, new GetCourseList() {
                @Override
                public void onCoursesFetched(List<Courses> coursesList) {
                    Log.d(TAG, "onCoursesFetched: " + coursesList.toString());
                    saveCourses(coursesList);
                    callback.onCoursesFetched(coursesList);
                }

                @Override
                public void onFetchFailure() {
                    // fetch from local database
                    getCoursesFromDatabase(fromDB, callback);
                }
            });
        } else {
            // offline... get from local database
            getCoursesFromDatabase(fromDB, callback);
        }

    }

    @Override
    public void getCourse(@NonNull String key, @NonNull GetCourse callback) {

    }

    private void getCoursesFromDatabase(@NonNull boolean fromDB, @NonNull final GetCourseList callback) {
        mLocalDataSource.getCourses(fromDB, new GetCourseList() {
            @Override
            public void onCoursesFetched(List<Courses> coursesList) {
                callback.onCoursesFetched(coursesList);
            }

            @Override
            public void onFetchFailure() {
                // show No Data message
                callback.onFetchFailure();
            }
        });
    }
}
