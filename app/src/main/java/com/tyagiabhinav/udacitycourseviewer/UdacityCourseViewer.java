package com.tyagiabhinav.udacitycourseviewer;

import com.tyagiabhinav.udacitycourseviewer.di.DaggerApplicationComponent;
import com.tyagiabhinav.udacitycourseviewer.model.CourseRepository;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class UdacityCourseViewer extends DaggerApplication {
    @Inject
    CourseRepository courseRepository;

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerApplicationComponent.builder().application(this).build();
    }

}
