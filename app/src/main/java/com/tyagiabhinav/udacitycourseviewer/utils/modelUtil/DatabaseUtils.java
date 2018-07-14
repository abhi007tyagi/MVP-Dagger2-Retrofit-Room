package com.tyagiabhinav.udacitycourseviewer.utils.modelUtil;

import android.support.annotation.NonNull;

import com.tyagiabhinav.udacitycourseviewer.model.local.database.Course;
import com.tyagiabhinav.udacitycourseviewer.model.local.database.Instructor;
import com.tyagiabhinav.udacitycourseviewer.model.pojo.Courses;
import com.tyagiabhinav.udacitycourseviewer.model.pojo.Instructors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseUtils {

    public static final String COURSE_KEY = "course";
    public static final String INSTRUCTOR_KEY = "instructor";

    public static Map<String, Object> convertPojoToEntity(@NonNull Courses courses) {

        Course course = new Course(courses.getKey(),courses.getSummary(),
                courses.getExpected_duration_unit(),courses.getTitle(),
                courses.getLevel(), courses.getShort_summary(),String.valueOf(courses.getExpected_duration()));

        List<Instructors> instructorsList = courses.getInstructors();
        List<Instructor> instructorList = new ArrayList<>(instructorsList.size());
        for(Instructors instructors : instructorsList){
            Instructor instructor = new Instructor(courses.getKey(), instructors.getBio(),
                    instructors.getName(), instructors.getImage());
            instructorList.add(instructor);
        }
        Map<String, Object> dbMap = new HashMap<>(2);
        dbMap.put(COURSE_KEY,course);
        dbMap.put(INSTRUCTOR_KEY, instructorList);

        return dbMap;

    }

    public static Courses convertEntityToPojo(@NonNull Map<String, Object> dbMap) {
        Course course = (Course) dbMap.get(COURSE_KEY);
        ArrayList<Instructor> instructors = (ArrayList<Instructor>) dbMap.get(INSTRUCTOR_KEY);

        Courses courses = new Courses();
        courses.setKey(course.getKey());
        courses.setSummary(course.getSummary());
        courses.setExpected_duration_unit(course.getExpectedDurationUnit());
        courses.setTitle(course.getTitle());
        courses.setLevel(course.getLevel());
        courses.setShort_summary(course.getShortSummary());
        courses.setExpected_duration(Integer.parseInt(course.getExpectedDuration()));


        List<Instructors> instructorsList = new ArrayList<>(instructors.size());
        for(Instructor instructor: instructors){
            Instructors courseInstructor = new Instructors();
            courseInstructor.setBio(instructor.getBio());
            courseInstructor.setImage(instructor.getImage());
            courseInstructor.setName(instructor.getName());
            instructorsList.add(courseInstructor);
        }

        courses.setInstructors(instructorsList);

        return courses;

    }
}
