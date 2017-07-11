package com.costa.bs.splendours.ApiServices;

import com.costa.bs.splendours.Models.SearchResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by binusadanand on 09/07/2017.
 */

public interface Api {
    @GET("v2/venues/search")
    Observable<SearchResult> searchVenue(
            @Query(EndpointConstants.LATLONG) String aID,
            @Query(EndpointConstants.CLIENT_ID_KEY) String aClientID,
            @Query(EndpointConstants.CLIENT_SECRET_KEY) String aClientSecret,
            @Query(EndpointConstants.VERSION_NO) String aVersionNo);

    @GET("v2/venues/search")
    Observable<SearchResult> searchVenueWithTerm(@Query(EndpointConstants.CLIENT_ID_KEY) String aClientID,
                                                 @Query(EndpointConstants.CLIENT_SECRET_KEY) String aClientSecret,
                                                 @Query(EndpointConstants.LATLONG) String aID,
                                                 @Query(EndpointConstants.QUERY) String aTerm);



}