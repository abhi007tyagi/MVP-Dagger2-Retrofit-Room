package com.tyagiabhinav.udacitycourseviewer.model;

import com.tyagiabhinav.udacitycourseviewer.model.local.DatabaseModule;
import com.tyagiabhinav.udacitycourseviewer.model.local.LocalDataSource;
import com.tyagiabhinav.udacitycourseviewer.model.remote.RemoteDataSource;
import com.tyagiabhinav.udacitycourseviewer.utils.executors.AppExecutor;
import com.tyagiabhinav.udacitycourseviewer.utils.executors.DiskExecutors;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module(includes = DatabaseModule.class)
public abstract class CourseRepositoryModule {

    private static final int THREAD_COUNT = 4;

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

    @Singleton
    @Provides
    static AppExecutor provideAppExecutor() {
        return new AppExecutor(new DiskExecutors(),
                Executors.newFixedThreadPool(THREAD_COUNT),
                new AppExecutor.MainThreadExecutor());
    }
}
