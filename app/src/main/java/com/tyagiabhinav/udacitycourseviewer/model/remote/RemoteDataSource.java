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
    public void getCourses(@NonNull boolean fromDB, @NonNull final GetCourseList callback) {
            service.getCourses().enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                    Log.d(TAG, "onResponse");
                    if (response.body() != null) {
                        callback.onCoursesFetched(response.body().getCourses());
                    }else{
                        callback.onFetchFailure();
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    callback.onFetchFailure();
                }
            });
    }

    @Override
    public void getCourse(@NonNull String key, @NonNull GetCourse callback) {}
}
