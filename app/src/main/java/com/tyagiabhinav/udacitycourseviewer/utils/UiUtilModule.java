package com.tyagiabhinav.udacitycourseviewer.utils;

import android.content.Context;

import com.tyagiabhinav.udacitycourseviewer.utils.uiUtil.DividerLine;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class UiUtilModule {

//    Commented as not required
//
//    @Provides
//    static Picasso providePicasso(Context context, OkHttp3Downloader okHttp3Downloader){
//        return new Picasso.Builder(context).
//                downloader(okHttp3Downloader).
//                build();
//    }
//
//    @Provides
//    static OkHttp3Downloader provideOkHttp3Downloader(OkHttpClient okHttpClient){
//        return new OkHttp3Downloader(okHttpClient);
//    }

    @Provides
    static DividerLine provideDividerLine(Context context){
        return new DividerLine(context);
    }
}
