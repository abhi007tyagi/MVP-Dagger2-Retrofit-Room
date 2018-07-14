package com.tyagiabhinav.udacitycourseviewer.utils.uiUtil;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class ActivityUtils {

    /**
     * adds fragments to activity
     *
     * @param fragmentManager
     * @param fragment
     * @param frameId
     */
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    /**
     * replaces fragments in an activity
     *
     * @param fragmentManager
     * @param fragment
     * @param frameId
     */
    public static void replaceFragmentInActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
