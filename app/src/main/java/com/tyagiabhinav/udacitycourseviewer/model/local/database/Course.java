package com.tyagiabhinav.udacitycourseviewer.model.local.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Objects;

/**
 * Immutable model class for a Course.
 */
@Entity(tableName = "courses")
public class Course {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "key")
    private final String mKey;


    @Nullable
    @ColumnInfo(name = "summary")
    private final String mSummary;

    @Nullable
    @ColumnInfo(name = "expected_duration_unit")
    private final String mExpectedDurationUnit;

    @Nullable
    @ColumnInfo(name = "banner_image")
    private final String mBannerImage;

    @Nullable
    @ColumnInfo(name = "title")
    private final String mTitle;

    @Nullable
    @ColumnInfo(name = "level")
    private final String mLevel;

    @Nullable
    @ColumnInfo(name = "short_summary")
    private final String mShortSummary;

    @Nullable
    @ColumnInfo(name = "expected_duration")
    private final String mExpectedDuration;


    /**
     * Use this constructor to create a new Course.
     */
    public Course(@NonNull String mKey, @Nullable String mSummary, @Nullable String mExpectedDurationUnit,
                  @Nullable String mBannerImage, @Nullable String mTitle, @Nullable String mLevel,
                  @Nullable String mShortSummary, @Nullable String mExpectedDuration) {
        this.mKey = mKey;
        this.mSummary = mSummary;
        this.mExpectedDurationUnit = mExpectedDurationUnit;
        this.mBannerImage = mBannerImage;
        this.mTitle = mTitle;
        this.mLevel = mLevel;
        this.mShortSummary = mShortSummary;
        this.mExpectedDuration = mExpectedDuration;
    }

    @NonNull
    public String getKey() {
        return mKey;
    }

    @Nullable
    public String getSummary() {
        return mSummary;
    }

    @Nullable
    public String getExpectedDurationUnit() {
        return mExpectedDurationUnit;
    }

    @Nullable
    public String getBannerImage() {
        return mBannerImage;
    }

    @Nullable
    public String getTitle() {
        return mTitle;
    }

    @Nullable
    public String getLevel() {
        return mLevel;
    }

    @Nullable
    public String getShortSummary() {
        return mShortSummary;
    }

    @Nullable
    public String getExpectedDuration() {
        return mExpectedDuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(mKey, course.mKey) &&
                Objects.equals(mTitle, course.mTitle) &&
                Objects.equals(mSummary, course.mSummary);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mKey + mTitle + mSummary);
    }

    @Override
    public String toString() {
        return "Course with title " + mTitle;
    }
}
