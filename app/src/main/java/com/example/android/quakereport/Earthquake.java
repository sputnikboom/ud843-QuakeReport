package com.example.android.quakereport;

/**
 * {@link Earthquake} is an object that contains the relevant infromatin
 * for a single 'quake
 */
public class Earthquake {

    private String mMagnitude;
    private String mLocation;
    private String mDate;

    /**
     * Contructor to make a new {@link Earthquake} object
     * @param magnitude is the magnitude of earthquake
     * @param location is the closest named location
     * @param date is the date the 'quake occured
     */
    public Earthquake(String magnitude, String location, String date) {
        mMagnitude = magnitude;
        mLocation = location;
        mDate = date;
    }

    public String getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public String getDate() {
        return mDate;
    }
}
