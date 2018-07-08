package com.tyagiabhinav.udacitycourseviewer.di;

import com.tyagiabhinav.udacitycourseviewer.ui.courseList.CourseListActivity;
import com.tyagiabhinav.udacitycourseviewer.ui.courseList.CourseListModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = CourseListModule.class)
    abstract CourseListActivity courseListActivity();

//    @ActivityScope
//    @ContributesAndroidInjector(modules = CourseDetailModule.class)
//    abstract CourseDetailActivity courseDetailActivity();
}