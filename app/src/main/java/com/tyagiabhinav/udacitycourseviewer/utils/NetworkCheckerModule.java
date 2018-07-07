package com.tyagiabhinav.udacitycourseviewer.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class NetworkCheckerModule {

    @Provides
    @Singleton
    public OnlineChecker onlineChecker(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return new OnlineChecker(connectivityManager);
    }
}