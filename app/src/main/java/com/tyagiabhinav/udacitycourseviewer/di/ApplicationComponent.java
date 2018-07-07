package com.tyagiabhinav.udacitycourseviewer.di;

import android.app.Application;

import com.tyagiabhinav.udacitycourseviewer.UdacityCourseViewer;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class,
})
public interface ApplicationComponent extends AndroidInjector<UdacityCourseViewer> {


    // Gives us syntactic sugar. we can then do DaggerAppComponent.builder().application(this).build().inject(this);
    // never having to instantiate any modules or say which module we are passing the application to.
    // Application will just be provided into our app graph now.
    @Component.Builder
    interface Builder {

        @BindsInstance
        ApplicationComponent.Builder application(Application application);

        ApplicationComponent build();
    }
}
