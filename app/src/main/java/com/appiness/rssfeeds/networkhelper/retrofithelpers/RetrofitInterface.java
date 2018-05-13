package com.appiness.rssfeeds.networkhelper.retrofithelpers;


import com.appiness.rssfeeds.networkhelper.EndPoints;
import com.appiness.rssfeeds.networkhelper.responsemodels.RetrofitResponse;

import retrofit2.Call;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST(EndPoints.headlines)
    Call<RetrofitResponse> loadHeadlines();


}
