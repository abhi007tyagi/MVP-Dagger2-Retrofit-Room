package com.tyagiabhinav.udacitycourseviewer.model.remote;

import android.support.annotation.NonNull;

import com.tyagiabhinav.udacitycourseviewer.model.DataSource;
import com.tyagiabhinav.udacitycourseviewer.model.pojo.ApiResponse;
import com.tyagiabhinav.udacitycourseviewer.model.pojo.Courses;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RemoteDataSource implements DataSource {

    private final static String TAG = RemoteDataSource.class.getName();
    private final RepoService service;

    @Inject
    RemoteDataSource(RepoService repoService) {
        this.service = repoService;
    }

    @Override
    public void saveCourses(@NonNull List<Courses> coursesList) {

    }

    @Override
    public void getCourses(@NonNull final GetCourseList callback) {
//        service.getCourses().enqueue(new Callback<ApiResponse>() {
//            @Override
//            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
//                Log.d(TAG, "onResponse: "+ response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<ApiResponse> call, Throwable t) {
//
//            }
//        });
    }

    @Override
    public void getCourse(@NonNull String key, @NonNull GetCourse callback) {

    }

    private List<Courses> extractCourses(ApiResponse apiResponse) {
        return null;
    }
}