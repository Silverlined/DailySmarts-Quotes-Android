package com.example.dimitriygeorgiev.dailysmarts.models.retrofit;

import com.example.dimitriygeorgiev.dailysmarts.models.Quote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("?method=getQuote&format=json&lang=en ")
    Call<Quote> getQuote();
}
