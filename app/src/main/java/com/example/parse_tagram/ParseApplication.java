package com.example.parse_tagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application
{
    // Initialize Parse SDK as soon as the application is created
    @Override
    public void onCreate()
    {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("8TV0Mvmnr6g0W798aL6EBHUDHpgNkujbDT9fgJhH")
                .clientKey("sjEmoyL1RunhWtdXynzAr6qNmzBgd4o1KtoQI3UH")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
