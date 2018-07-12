package com.tyagiabhinav.udacitycourseviewer.model.local.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * The Room Database that contains the Task table.
 */
@Database(entities = {Course.class, Instructor.class}, version = 2, exportSchema = false)
public abstract class CourseDatabase extends RoomDatabase {

    public abstract CourseDAO courseDao();

    public abstract InstructorDAO instructorDAO();

    public abstract CourseListDAO courseListDAO();
}