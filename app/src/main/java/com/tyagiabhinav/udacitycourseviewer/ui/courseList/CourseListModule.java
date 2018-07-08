package com.tyagiabhinav.udacitycourseviewer.ui.courseList;

import com.tyagiabhinav.udacitycourseviewer.di.ActivityScope;
import com.tyagiabhinav.udacitycourseviewer.di.FragmentScope;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CourseListModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract CourseListFragment courseListFragment();

    @ActivityScope
    @Binds
    abstract CourseListContract.Presenter courseListPresenter(CourseListPresenter presenter);
}
