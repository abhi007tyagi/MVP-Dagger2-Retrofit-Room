package com.tyagiabhinav.udacitycourseviewer;


import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class UdacityCourseViewer extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

}
