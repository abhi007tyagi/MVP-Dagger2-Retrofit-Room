package com.tyagiabhinav.udacitycourseviewer.utils;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class OnlineChecker implements NetworkChecker {

    private final ConnectivityManager connectivityManager;

    public OnlineChecker(ConnectivityManager connectivityManager) {
        this.connectivityManager = connectivityManager;
    }

    @Override
    public boolean isOnline() {
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
