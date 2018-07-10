
package com.tyagiabhinav.udacitycourseviewer.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Teaser_video implements Parcelable {

    @SerializedName("youtube_url")
    @Expose
    private String youtube_url;

    public String getYoutube_url() {
        return youtube_url;
    }

    public void setYoutube_url(String youtube_url) {
        this.youtube_url = youtube_url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.youtube_url);
    }

    public Teaser_video() {
    }

    protected Teaser_video(Parcel in) {
        this.youtube_url = in.readString();
    }

    public static final Parcelable.Creator<Teaser_video> CREATOR = new Parcelable.Creator<Teaser_video>() {
        @Override
        public Teaser_video createFromParcel(Parcel source) {
            return new Teaser_video(source);
        }

        @Override
        public Teaser_video[] newArray(int size) {
            return new Teaser_video[size];
        }
    };
}
