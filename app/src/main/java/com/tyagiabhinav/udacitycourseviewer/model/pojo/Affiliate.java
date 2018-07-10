
package com.tyagiabhinav.udacitycourseviewer.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Affiliate implements Parcelable {

    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("name")
    @Expose
    private String name;

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
        dest.writeString(this.image);
        dest.writeString(this.name);
    }

    public Affiliate() {
    }

    protected Affiliate(Parcel in) {
        this.image = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Affiliate> CREATOR = new Parcelable.Creator<Affiliate>() {
        @Override
        public Affiliate createFromParcel(Parcel source) {
            return new Affiliate(source);
        }

        @Override
        public Affiliate[] newArray(int size) {
            return new Affiliate[size];
        }
    };
}
