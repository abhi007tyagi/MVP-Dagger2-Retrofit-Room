package com.tyagiabhinav.udacitycourseviewer;

import com.squareup.leakcanary.LeakCanary;
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

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        // set crash handler
//        CrashHandler.init(this)
//                .showStackTraceReport(true)
//                .emailTo("tyagiabhinav@yahoo.co.in");
    }
}
