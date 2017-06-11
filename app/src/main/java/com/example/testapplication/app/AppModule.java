package com.example.testapplication.app;

/**
 * Dagger module for providing application's context and preferences
 */
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences() {
        return app.getSharedPreferences("settings", 0);
    }

    @Provides
    @Singleton
    App provideApplication() { return app; }
}