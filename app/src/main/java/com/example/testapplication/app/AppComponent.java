package com.example.testapplication.app;

/**
 * Created by fobo66 on 16.09.2016.
 */

import javax.inject.Singleton;

import dagger.Component;

import com.example.testapplication.ui.cat.CategoriesPresenter;
import com.example.testapplication.ui.item.ItemPresenter;
import com.example.testapplication.ui.main.MainActivity;
import com.example.testapplication.ui.main.MainPresenter;
import com.example.testapplication.network.NetworkModule;

@Singleton
@Component(modules = {NetworkModule.class, AppModule.class})
public interface AppComponent {
    void inject(MainPresenter x);
    void inject(CategoriesPresenter x);
    void inject(ItemPresenter x);
    void inject(MainActivity x);
}