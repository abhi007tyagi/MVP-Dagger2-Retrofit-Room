
package com.tyagiabhinav.udacitycourseviewer.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Instructors implements Parcelable {

    @SerializedName("bio")
    @Expose
    private String bio;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("name")
    @Expose
    private String name;

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bio);
        dest.writeString(this.image);
        dest.writeString(this.name);
    }

    public Instructors() {
    }

    protected Instructors(Parcel in) {
        this.bio = in.readString();
        this.image = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Instructors> CREATOR = new Parcelable.Creator<Instructors>() {
        @Override
        public Instructors createFromParcel(Parcel source) {
            return new Instructors(source);
        }

        @Override
        public Instructors[] newArray(int size) {
            return new Instructors[size];
        }
    };
}
