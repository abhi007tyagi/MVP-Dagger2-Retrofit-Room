package com.tyagiabhinav.udacitycourseviewer.model.local;


import android.arch.persistence.room.Room;
import android.content.Context;

import com.tyagiabhinav.udacitycourseviewer.model.local.database.CourseDAO;
import com.tyagiabhinav.udacitycourseviewer.model.local.database.CourseDatabase;
import com.tyagiabhinav.udacitycourseviewer.model.local.database.CourseListDAO;
import com.tyagiabhinav.udacitycourseviewer.model.local.database.InstructorDAO;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class DatabaseModule {

    @Provides
    @Singleton
    static CourseDatabase provideDatabase(Context context){
        return Room.databaseBuilder(context, CourseDatabase.class, "udacity_courses.db").build();
    }

    @Singleton
    @Provides
    static CourseDAO provideCourseDao(CourseDatabase db) {
        return db.courseDao();
    }

    @Singleton
    @Provides
    static InstructorDAO provideInstructorDao(CourseDatabase db) {
        return db.instructorDAO();
    }

    @Singleton
    @Provides
    static CourseListDAO provideCourseListDao(CourseDatabase db) {
        return db.courseListDAO();
    }

}
