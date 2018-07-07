package com.tyagiabhinav.udacitycourseviewer.model.local.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import java.util.List;

@Dao
public interface CourseListDAO {
    @Transaction
    @Query("SELECT * from courses")
    public List<CourseList> getCourses();
}
