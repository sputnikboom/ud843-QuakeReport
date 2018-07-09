/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderCallbacks<List<Earthquake>> {

    private static final String LOG_TAG = EarthquakeActivity.class.getName();
    private static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=10";
    private static final int EARTHQUAKE_LOADER_ID = 1;
    private QuakeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Find a reference to the {@link ListView} in the layout
        ListView quakeListView = (ListView) findViewById(R.id.list);

        // create a new adapter that takes an empty list of earthquakes as an input
        mAdapter = new QuakeAdapter(this, new ArrayList<Earthquake>());

        // set the adapter on the {@link ListView}
        // so the list can be populated with the user interface
        quakeListView.setAdapter(mAdapter);

        // set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected earthquake
        quakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // find the earthquake that was clicked on
                Earthquake currentEarthquake = mAdapter.getItem(position);
                // convert the striong url into a URI object to pas to the intent constructor
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());
                // create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
                // send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });


        //Get a reference to the LoaderManager, in order to interact with loaders
        LoaderManager loaderManager = getLoaderManager();

        //Initialize the loader. Pass in the int id constant defined above and pass in null
        //for the bundle. Pass in this activiy for the LoaderCallbacks parameter
        //(which is valid because this activity implements the LoaderCallbacks interface)
        loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int i, Bundle bundle) {
        // create a new loader for the given URL
        return new EarthquakeLoader(this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquakes) {
        // clear the adapter of previous earthquake data
        mAdapter.clear();

        // if there's a valid list of {@link Earthquake}s, then add them to the
        // adapter's data set. This will trigger the ListView to update
        if (earthquakes != null && !earthquakes.isEmpty()) {
            mAdapter.addAll(earthquakes);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        // Loader reset, so we can clear out existing data
        mAdapter.clear();
    }

}

