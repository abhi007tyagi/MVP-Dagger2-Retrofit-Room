package com.tyagiabhinav.udacitycourseviewer.model.local.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface InstructorDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Instructor instructor);

    @Update
    void update(Instructor... instructor);

    @Delete
    void delete(Instructor... instructor);

    @Query("SELECT * FROM instructors WHERE courseId=:courseKey")
    List<Instructor> getCourseInstructorsForUser(final String courseKey);
}