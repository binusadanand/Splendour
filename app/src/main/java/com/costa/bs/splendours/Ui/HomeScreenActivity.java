package com.costa.bs.splendours.Ui;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.costa.bs.splendours.Models.SearchResult;
import com.costa.bs.splendours.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by binusadanand on 09/07/2017.
 */
public class HomeScreenActivity extends AppCompatActivity implements HomeScreenView {

    private static final int MY_PERMISSIONS_REQUEST = 1;

    @BindView(R.id.home_screen_list_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.home_screen_list_empty_view)
    TextView mInfoTv;

    @BindView(R.id.home_screen_progress_view)
    ContentLoadingProgressBar mProgress;

    private HomeScreenAdapter mAdapter;
    private HomeScreenPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen_layout);
        ButterKnife.bind(this);

        setTitle(R.string.search_label);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new HomeScreenAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mPresenter = new HomeScreenPresenter(this);

    }

    @Override
    protected void  onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST);

        } else {
            checkLocationAndSearch();
        }
    }

    private void checkLocationAndSearch() {

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (location != null) {
            mPresenter.getSearchResults(location.getLatitude(), location.getLongitude());
        } else {
            Toast.makeText(this,"No location info available in this device", Toast.LENGTH_LONG);
        }

    }



    @Override
    public void showProgress() {
        mProgress.show();
        mInfoTv.setVisibility(View.GONE);
    }

    @Override
    public void dismissProgress() {
        mProgress.hide();
        mInfoTv.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDetails(SearchResult aObj) {
        mProgress.hide();
        mInfoTv.setVisibility(View.GONE);
        if (aObj.response != null) {
            mAdapter.upDate(aObj.response.venues);
        }
    }

    @Override
    public void showError(String aReason) {
        mProgress.hide();
        mInfoTv.setVisibility(View.VISIBLE);
        mInfoTv.setText(aReason);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkLocationAndSearch();
                } else {
                    Toast.makeText(this, "Location permission required for the app to work", Toast.LENGTH_LONG);
                }
                return;
            }

        }
    }
}
