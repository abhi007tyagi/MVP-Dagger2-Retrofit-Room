package com.tyagiabhinav.udacitycourseviewer.model.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.tyagiabhinav.udacitycourseviewer.model.DataSource;
import com.tyagiabhinav.udacitycourseviewer.model.pojo.ApiResponse;
import com.tyagiabhinav.udacitycourseviewer.model.pojo.Courses;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class RemoteDataSource implements DataSource {

    private final static String TAG = RemoteDataSource.class.getSimpleName();
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
        service.getCourses().enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.d(TAG, "onResponse: "+ response.body().getCourses().get(0).getTitle().toString());
                callback.onCoursesFetched(response.body().getCourses());
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void getCourse(@NonNull String key, @NonNull GetCourse callback) {

    }

    private List<Courses> extractCourses(ApiResponse apiResponse) {
        return null;
    }
}
