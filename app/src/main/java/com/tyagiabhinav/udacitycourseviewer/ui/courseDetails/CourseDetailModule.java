package com.tyagiabhinav.udacitycourseviewer.ui.courseDetails;

import com.tyagiabhinav.udacitycourseviewer.di.FragmentScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CourseDetailModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract CourseDetailFragment courseDetailFragment();
}
