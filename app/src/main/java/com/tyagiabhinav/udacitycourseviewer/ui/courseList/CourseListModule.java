package com.tyagiabhinav.udacitycourseviewer.ui.courseList;

import com.tyagiabhinav.udacitycourseviewer.di.ActivityScope;
import com.tyagiabhinav.udacitycourseviewer.ui.courseDetails.CourseDetailModule;

import dagger.Binds;
import dagger.Module;

@Module(includes = CourseDetailModule.class)
public abstract class CourseListModule {

    @ActivityScope
    @Binds
    abstract CourseListContract.Presenter courseListPresenter(CourseListPresenter presenter);
}
