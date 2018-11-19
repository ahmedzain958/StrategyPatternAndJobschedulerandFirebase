package com.mobiledoctors24.rxaffectsui.rxjavaretrofit;


import retrofit2.http.GET;

public interface ServiceClass {
    @GET("data/all/coinlist")
    io.reactivex.Observable<CoinList> getData();
}
