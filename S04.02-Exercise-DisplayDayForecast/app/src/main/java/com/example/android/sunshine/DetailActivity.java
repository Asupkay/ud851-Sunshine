package com.example.android.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent creationIntent = getIntent();

        // COMPLETED (2) Display the weather forecast that was passed from MainActivity
        if(creationIntent != null && creationIntent.hasExtra(Intent.EXTRA_TEXT)){
            TextView weather_display = (TextView) findViewById(R.id.weather_display);
            weather_display.setText(creationIntent.getStringExtra(Intent.EXTRA_TEXT));
        }

    }
}