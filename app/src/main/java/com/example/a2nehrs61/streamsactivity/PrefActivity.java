package com.example.a2nehrs61.streamsactivity;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by 2nehrs61 on 08/03/2018.
 */

public class PrefActivity extends PreferenceActivity{
    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
