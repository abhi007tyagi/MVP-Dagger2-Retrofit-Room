package com.tyagiabhinav.udacitycourseviewer.model.local.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface CourseDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Course course);

    @Update
    void update(Course... course);

    @Delete
    void delete(Course... course);

    @Query("SELECT * FROM courses")
    List<Course> getAllCourses();
}
