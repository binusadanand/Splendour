package com.costa.bs.splendours.Ui;

import com.costa.bs.splendours.Models.SearchResult;

/**
 * Created by binusadanand on 09/07/2017.
 */

public interface HomeScreenView {
    void showProgress();
    void dismissProgress();
    void showDetails(SearchResult aObj);
    void showError(String aReason);
}
