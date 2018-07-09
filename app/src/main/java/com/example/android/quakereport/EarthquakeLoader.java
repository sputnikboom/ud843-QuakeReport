package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    // tag for log messages
    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    // query URL
    private String mUrl;

    /**
     * Constructs a new {@link EarthquakeLoader}
     *
     * @param context of the activity
     * @param url     to load data from
     */

    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    public void onStartLoading() {
        Log.i(LOG_TAG, "TEST: onStartLoading called");

        forceLoad();
    }

    /**
     * Thus is on a background thread
     */
    @Override
    public List<Earthquake> loadInBackground() {
        Log.i(LOG_TAG, "TEST: loadInBackground called");

        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response and extract a list of earthquakes
        List<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(mUrl);
        return earthquakes;

    }
}
