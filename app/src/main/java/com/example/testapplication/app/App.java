package com.example.testapplication.app;

import android.app.Application;

import com.example.testapplication.network.NetworkModule;
import com.example.testapplication.util.ComponentReflectionInjector;
import com.example.testapplication.util.Injector;


/**
 * Dagger initialising class
 */

public class App extends Application implements Injector{
    private ComponentReflectionInjector<AppComponent> injector;

    @Override
    public void onCreate() {
        super.onCreate();
        AppComponent component = DaggerAppComponent.builder()
                .networkModule(new NetworkModule())
                .appModule(new AppModule(this))
                .build();
        injector = new ComponentReflectionInjector<>(AppComponent.class, component);
    }

    @Override
    public void inject(Object target) {
        injector.inject(target);
    }
}
