
package com.tyagiabhinav.udacitycourseviewer.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Courses implements Parcelable {

    @SerializedName("instructors")
    @Expose
    private List<Instructors> instructors = null;
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("short_summary")
    @Expose
    private String short_summary;
    @SerializedName("level")
    @Expose
    private String level;
    @SerializedName("expected_duration_unit")
    @Expose
    private String expected_duration_unit;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("expected_duration")
    @Expose
    private Integer expected_duration;

    public List<Instructors> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<Instructors> instructors) {
        this.instructors = instructors;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShort_summary() {
        return short_summary;
    }

    public void setShort_summary(String short_summary) {
        this.short_summary = short_summary;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getExpected_duration_unit() {
        return expected_duration_unit;
    }

    public void setExpected_duration_unit(String expected_duration_unit) {
        this.expected_duration_unit = expected_duration_unit;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getExpected_duration() {
        return expected_duration;
    }

    public void setExpected_duration(Integer expected_duration) {
        this.expected_duration = expected_duration;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.instructors);
        dest.writeString(this.key);
        dest.writeString(this.title);
        dest.writeString(this.short_summary);
        dest.writeString(this.level);
        dest.writeString(this.expected_duration_unit);
        dest.writeString(this.summary);
        dest.writeValue(this.expected_duration);
    }

    public Courses() {
    }

    protected Courses(Parcel in) {
        this.instructors = new ArrayList<Instructors>();
        in.readList(this.instructors, Instructors.class.getClassLoader());
        this.key = in.readString();
        this.title = in.readString();
        this.short_summary = in.readString();
        this.level = in.readString();
        this.expected_duration_unit = in.readString();
        this.summary = in.readString();
        this.expected_duration = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Courses> CREATOR = new Parcelable.Creator<Courses>() {
        @Override
        public Courses createFromParcel(Parcel source) {
            return new Courses(source);
        }

        @Override
        public Courses[] newArray(int size) {
            return new Courses[size];
        }
    };
}
