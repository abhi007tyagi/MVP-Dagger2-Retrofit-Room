package com.tyagiabhinav.udacitycourseviewer.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class Courses {

    @SerializedName("key")
    @Expose
    private String key;

    @SerializedName("summary")
    @Expose
    private String summary;

    @SerializedName("expected_duration_unit")
    @Expose
    private String expected_duration_unit;

    @SerializedName("banner_image")
    @Expose
    private String banner_image;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("level")
    @Expose
    private String level;

    @SerializedName("short_summary")
    @Expose
    private String short_summary;

    @SerializedName("expected_duration")
    @Expose
    private String expected_duration;

    @SerializedName("instructors")
    @Expose
    private List<Instructors> instructors;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getExpected_duration_unit() {
        return expected_duration_unit;
    }

    public void setExpected_duration_unit(String expected_duration_unit) {
        this.expected_duration_unit = expected_duration_unit;
    }

    public String getBanner_image() {
        return banner_image;
    }

    public void setBanner_image(String banner_image) {
        this.banner_image = banner_image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getShort_summary() {
        return short_summary;
    }

    public void setShort_summary(String short_summary) {
        this.short_summary = short_summary;
    }

    public String getExpected_duration() {
        return expected_duration;
    }

    public void setExpected_duration(String expected_duration) {
        this.expected_duration = expected_duration;
    }

    public List<Instructors> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<Instructors> instructors) {
        this.instructors = instructors;
    }

    @Override
    public String toString() {
        return "Courses{" +
                "key='" + key + '\'' +
                ", summary='" + summary + '\'' +
                ", expected_duration_unit='" + expected_duration_unit + '\'' +
                ", banner_image='" + banner_image + '\'' +
                ", title='" + title + '\'' +
                ", level='" + level + '\'' +
                ", short_summary='" + short_summary + '\'' +
                ", expected_duration='" + expected_duration + '\'' +
                ", instructors=" + instructors +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Courses courses = (Courses) o;
        return Objects.equals(key, courses.key) &&
                Objects.equals(summary, courses.summary) &&
                Objects.equals(expected_duration_unit, courses.expected_duration_unit) &&
                Objects.equals(banner_image, courses.banner_image) &&
                Objects.equals(title, courses.title) &&
                Objects.equals(level, courses.level) &&
                Objects.equals(short_summary, courses.short_summary) &&
                Objects.equals(expected_duration, courses.expected_duration) &&
                Objects.equals(instructors, courses.instructors);
    }

    @Override
    public int hashCode() {

        return Objects.hash(key, summary, expected_duration_unit, banner_image, title, level, short_summary, expected_duration, instructors);
    }
}
