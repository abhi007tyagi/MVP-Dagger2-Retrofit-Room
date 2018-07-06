package com.tyagiabhinav.udacitycourseviewer.model.local.database;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

public class CourseList {

    @Embedded
    public Course course;

    @Relation(parentColumn = "key", entityColumn = "courseId")
    public List<Instructor> instructors;

}
