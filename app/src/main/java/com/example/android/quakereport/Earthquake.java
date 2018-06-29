package com.example.android.quakereport;

/**
 * {@link Earthquake} is an object that contains the relevant infromatin
 * for a single 'quake
 */
public class Earthquake {

    private double mMagnitude;
    private String mLocation;
    private long mTimeInMilliseconds;

    /**
     * Contructor to make a new {@link Earthquake} object
     * @param magnitude is the magnitude of earthquake
     * @param location is the closest named location
     * @param timeInMilliseconds is the date the 'quake occured
     */
    public Earthquake(double magnitude, String location, long timeInMilliseconds) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }
}
