package com.costa.bs.splendours.Ui;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.costa.bs.splendours.ApiServices.Api;
import com.costa.bs.splendours.ApiServices.EndpointConstants;
import com.costa.bs.splendours.ApiServices.Provider;
import com.costa.bs.splendours.BuildConfig;
import com.costa.bs.splendours.Models.SearchResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by binusadanand on 09/07/2017.
 */

public class HomeScreenPresenter {
    private Api mApiService;
    private final HomeScreenView mView;

    public HomeScreenPresenter(HomeScreenActivity aActivity) {
        mView = aActivity;
        mApiService = Provider.getApiService();
    }

    public void getSearchResults(double aLat, double aLong) {

        mView.showProgress();;

        StringBuilder sb =  new StringBuilder();
        sb.append(String.format(Locale.ENGLISH, "%.2f",aLat));
        sb.append(",");
        sb.append(String.format(Locale.ENGLISH, "%.2f",aLong));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
        String aCurrentDate = sdf.format(new Date());

        mApiService.searchVenue(
                sb.toString(),
                BuildConfig.CLIENT_ID,
                BuildConfig.CLIENT_SECRET,
                aCurrentDate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SearchResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.dismissProgress();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(SearchResult aResult) {
                        mView.dismissProgress();
                        mView.showDetails(aResult);
                    }
                });


    }

}
