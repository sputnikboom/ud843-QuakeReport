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

public class QuakeAdapter extends ArrayAdapter<Earthquake> {

    public QuakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View earthquakeView = convertView;
        if (earthquakeView == null) {
            earthquakeView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_item, parent, false);
        }

            Earthquake currentQuake = getItem(position);

            TextView magnitudeTextView = (TextView) earthquakeView.findViewById(R.id.magnitude);
            magnitudeTextView.setText(currentQuake.getMagnitude());

            TextView locationTextView = (TextView) earthquakeView.findViewById(R.id.location);
            locationTextView.setText(currentQuake.getLocation());

            TextView dateTextView = (TextView) earthquakeView.findViewById(R.id.quake_date);
            dateTextView.setText(currentQuake.getDate());

            return earthquakeView;
        }
    }

