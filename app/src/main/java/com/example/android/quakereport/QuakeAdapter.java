package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * {@link QuakeAdapter} creates a list item layout for each earthquake in the list
 * of {@link Earthquake} objects.
 */

public class QuakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = " of ";

    public QuakeAdapter(Context context, List<Earthquake> earthquakes) {
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
        // formats the magnitude double
        String formattedMag = formatMag(currentQuake.getMagnitude());
        // Displays formatted magnitude string
        magnitudeTextView.setText(formattedMag);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentQuake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        // original location string from earthquake object
        String originalLocation = currentQuake.getLocation();

        // if this contains a location and distance from location, will be split into two strings
        String primaryLocation;
        String locationOffset;

        // checks if the string had the " of " text separator
        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            // if yes, splits it into two strings
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            // text before " of "
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            // text after " of " plus " of " itself.
            primaryLocation = parts[1];
        } else {
            // if not, offset is set to "near the"
            locationOffset = getContext().getString(R.string.near_the);
            // full location string from earthquake object
            primaryLocation = originalLocation;
        }

        // finds the TextView with primary location id, sets it to be displayed with info from current quake
        TextView primaryLocationTextView = (TextView) earthquakeView.findViewById(R.id.primary_location);
        primaryLocationTextView.setText(primaryLocation);

        // as above, for the offset location
        TextView locationOffsetTextView = (TextView) earthquakeView.findViewById(R.id.location_offset);
        locationOffsetTextView.setText(locationOffset);

        // Create new date object from time in milliseconds of the earthquake
        Date dateObject = new Date(currentQuake.getTimeInMilliseconds());
        // find textview with id date.
        TextView dateTextView = (TextView) earthquakeView.findViewById(R.id.quake_date);
        // formats the date string
        String formattedDate = formatDate(dateObject);
        // displays this in the textview for the current earthquake
        dateTextView.setText(formattedDate);

        // find textview with id time.
        TextView timeTextView = (TextView) earthquakeView.findViewById(R.id.quake_time);
        // formats the date string
        String formattedTime = formatTime(dateObject);
        // displays this in the textview for the current earthquake
        timeTextView.setText(formattedTime);




        // returns a list item view with all the data from our object
        return earthquakeView;
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat(("h:mm a"));
        return timeFormat.format(dateObject);
    }

    private String formatMag(double magnitude) {
        DecimalFormat magFormat = new DecimalFormat("0.0");
        return magFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magniture) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magniture);

        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);

        }
    }


