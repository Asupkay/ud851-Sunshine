package com.example.android.sunshine;
/*
 * Created by Alex on 5/27/17.
 */

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener{
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preference_screen);
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        int numPrefs = preferenceScreen.getPreferenceCount();
        for(int i = 0; i < numPrefs; i++){
            Preference p = preferenceScreen.getPreference(i);
            if(!(p instanceof CheckBoxPreference)){
                String value = sharedPreferences.getString(p.getKey(), "");
                setPreferenceSummary(p, value);
            }
        }
    }

    private void setPreferenceSummary(Preference p, Object value){
        String stringValue = value.toString();
        String key = p.getKey();

        if(p instanceof ListPreference){
            ListPreference listPreference = (ListPreference) p;
            int prefIndex = listPreference.findIndexOfValue(stringValue);

            if(prefIndex >= 0){
                p.setSummary(listPreference.getEntries()[prefIndex]);
            }
        } else {
            p.setSummary(stringValue);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference p = findPreference(key);
        if(null != p){
            if(!(p instanceof CheckBoxPreference)) {
                setPreferenceSummary(p, sharedPreferences.getString(key, ""));
            }
        }
    }
}
