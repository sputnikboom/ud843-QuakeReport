package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 *  {@link QuakeAdapter} creates a list item layout for each earthquake in the list
 *  of {@link Earthquake} objects.
 */

public class QuakeAdapter extends ArrayAdapter<Earthquake> {

    /**
     * constructs a new {@link QuakeAdapter}
     * @param context of app
     * @param earthquakes is the list of 'quakes, which provide data for the adapter
     */

    public QuakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    /**
     * Returns a list item view that displays information about the earthquake
     * at the given position in the list.
     */

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Checks if there's an existing view {this is the convertView} that we can recycle
        // if not (convertView us null) then inflate a new list item layout
        View earthquakeView = convertView;
        if (earthquakeView == null) {
            earthquakeView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_item, parent, false);
        }
            // Find the 'quake at a given position in the list
            Earthquake currentQuake = getItem(position);

            // Finds textView with id magnitude
            TextView magnitudeTextView = (TextView) earthquakeView.findViewById(R.id.magnitude);
            // Displays text from current 'quake object in this textView
            magnitudeTextView.setText(currentQuake.getMagnitude());

            // As above, textView with id location
            TextView locationTextView = (TextView) earthquakeView.findViewById(R.id.location);
            locationTextView.setText(currentQuake.getLocation());

            // As above, id date.
            TextView dateTextView = (TextView) earthquakeView.findViewById(R.id.quake_date);
            dateTextView.setText(currentQuake.getDate());

            // returns a list item view with all the data from our object
            return earthquakeView;
        }
    }

