package com.example.testapplication.ui.item;

/**
 * Get only one item that user has chosen
 */

import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import icepick.State;
import rx.schedulers.Schedulers;

import com.example.testapplication.pojo.Offer;
import com.example.testapplication.pojo.Offers;
import com.example.testapplication.ui.base.BasePresenter;
import com.example.testapplication.network.ServerAPI;

import static rx.android.schedulers.AndroidSchedulers.mainThread;

public class ItemPresenter extends BasePresenter<ItemFragment> {

    public static final int GET_ITEM_REQUEST = 1;

    @Inject ServerAPI api;

    @State int id;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        restartableLatestCache(GET_ITEM_REQUEST,
                () -> api.getItems("ukAXxeJYZN")
                        .subscribeOn(Schedulers.io())
                        .map(Offers::getOffers)   //transform to array
                        .map(it -> getItem(it, id))             //get single item
                        .observeOn(mainThread()),
                ItemFragment::onItem,
                ItemFragment::onNetworkError);
    }

    void requestItem(int id) {
        this.id = id;
        start(GET_ITEM_REQUEST);
    }

    private Offer getItem(List<Offer> offers, int id) {
        for (Offer offer : offers) {
            if (offer.getId() == id)
                return offer;
        }
        return null;
    }
}
