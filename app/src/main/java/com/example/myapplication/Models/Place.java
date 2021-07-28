package com.example.myapplication.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Place implements Parcelable {
    private String name;
    private int image;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getImage() {
        return image;
    }
    public void setImage(int image) {
        this.image = image;
    }
    public Place(String name, int desc) {
        this.name = name;
        this.image = desc;
    }
    protected Place(Parcel in) {
        name = in.readString();
        image = in.readInt();
    }
    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }
        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(image);
    }
}
