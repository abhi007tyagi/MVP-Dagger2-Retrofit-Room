package com.tyagiabhinav.udacitycourseviewer.model;

import com.tyagiabhinav.udacitycourseviewer.model.local.DatabaseModule;
import com.tyagiabhinav.udacitycourseviewer.model.local.LocalDataSource;
import com.tyagiabhinav.udacitycourseviewer.model.remote.RemoteDataSource;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module(includes = DatabaseModule.class)
public abstract class CourseRepositoryModule {

//    @Provides
    @Singleton
    @Binds
    @Local
    abstract DataSource provideLocalDataSource(LocalDataSource dataSource);

//    @Provides
    @Singleton
    @Binds
    @Remote
    abstract DataSource provideRemoteDataSource(RemoteDataSource dataSource);
}
