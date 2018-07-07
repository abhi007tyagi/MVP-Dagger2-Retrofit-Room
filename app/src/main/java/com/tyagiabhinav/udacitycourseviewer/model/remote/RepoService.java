package com.tyagiabhinav.udacitycourseviewer.model.remote;

import com.tyagiabhinav.udacitycourseviewer.model.pojo.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RepoService {
    @GET("/public-api/v0/courses")
    Call<ApiResponse> getCourses();
}