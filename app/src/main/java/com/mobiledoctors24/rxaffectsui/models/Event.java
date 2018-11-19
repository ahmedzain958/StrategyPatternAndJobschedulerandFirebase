package com.mobiledoctors24.rxaffectsui.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable {
    private String id;

    public Event() {
    }

    public Event(Parcel in) {
        id = in.readString();
        name = in.readString();
        place = in.readString();
        type = in.readString();
        startTime = in.readString();
        endTime = in.readString();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String name;
    private String place;
    private String type;
    private String startTime;
    private String endTime;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(place);
        dest.writeString(type);
        dest.writeString(startTime);
        dest.writeString(endTime);
    }
}
