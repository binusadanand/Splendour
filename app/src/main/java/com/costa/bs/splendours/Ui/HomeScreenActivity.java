package com.costa.bs.splendours.Ui;

import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.costa.bs.splendours.Models.SearchResult;
import com.costa.bs.splendours.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by binusadanand on 09/07/2017.
 */
public class HomeScreenActivity extends AppCompatActivity implements HomeScreenView {

    @BindView(R.id.home_screen_list_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.home_screen_list_empty_view)
    TextView mInfoTv;

    @BindView(R.id.home_screen_progress_view)
    ContentLoadingProgressBar mProgress;

    private HomeScreenAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen_layout);
        ButterKnife.bind(this);

        setTitle(R.string.search_label);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new HomeScreenAdapter(this);
        mRecyclerView.setAdapter(mAdapter);


    }

    @Override
    protected void  onResume() {
        super.onResume();
        HomeScreenAdapter presenter = new HomeScreenAdapter(this);
        presenter.();

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
    public void showDetails(SearchResult aDetailObj) {
        mProgress.hide();
        mInfoTv.setVisibility(View.GONE);
        mAdapter.upDate(aDetailObj.list);
    }

    @Override
    public void showError(String aReason) {
        mProgress.hide();
        mInfoTv.setVisibility(View.VISIBLE);
        mInfoTv.setText(aReason);

    }
}
