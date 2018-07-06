package com.tyagiabhinav.udacitycourseviewer.model.local.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Immutable model class for a Instructors.
 */

@Entity(tableName = "instructors")
public class Instructor {

    @NonNull
    @ColumnInfo(name = "courseId")
    private final String mCourseId;

    @Nullable
    @ColumnInfo(name = "bio")
    private final String mBio;

    @Nullable
    @ColumnInfo(name = "name")
    private final String mName;

    @Nullable
    @ColumnInfo(name = "image")
    private final String mImage;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "uid")
    private final String mUid;


    /**
     * Use this constructor to create a new Instructor.
     */
    public Instructor(@NonNull String courseId, @Nullable String bio, @Nullable String name, @Nullable String image, @NonNull String uid) {
        this.mCourseId = courseId;
        this.mBio = bio;
        this.mName = name;
        this.mImage = image;
        this.mUid = uid;
    }

    @Ignore
    public Instructor(@NonNull String courseId, @Nullable String bio, @Nullable String name, @Nullable String image) {
        this(courseId, bio, name, image, UUID.randomUUID().toString());
    }

    @NonNull
    public String getCourseId() {
        return mCourseId;
    }

    @Nullable
    public String getBio() {
        return mBio;
    }

    @Nullable
    public String getName() {
        return mName;
    }

    @Nullable
    public String getImage() {
        return mImage;
    }

    @NonNull
    public String getUid() {
        return mUid;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instructor instructor = (Instructor) o;
        return Objects.equals(mCourseId, instructor.mCourseId) &&
                Objects.equals(mBio, instructor.mBio) &&
                Objects.equals(mName, instructor.mName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mCourseId + mBio + mName);
    }

    @Override
    public String toString() {
        return "Instructor with name " + mName;
    }
}
