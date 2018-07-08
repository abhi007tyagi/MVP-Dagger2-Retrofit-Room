
package com.tyagiabhinav.udacitycourseviewer.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse {

    @SerializedName("courses")
    @Expose
    private List<Courses> courses = null;
    @SerializedName("tracks")
    @Expose
    private List<Track> tracks = null;
    @SerializedName("degrees")
    @Expose
    private List<Degree> degrees = null;

    public List<Courses> getCourses() {
        return courses;
    }

    public void setCourses(List<Courses> courses) {
        this.courses = courses;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Degree> getDegrees() {
        return degrees;
    }

    public void setDegrees(List<Degree> degrees) {
        this.degrees = degrees;
    }

}
