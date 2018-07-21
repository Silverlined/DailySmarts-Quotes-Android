package com.example.dimitriygeorgiev.dailysmarts.models.retrofit;

import com.example.dimitriygeorgiev.dailysmarts.models.Quote;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitWrapper implements QuoterDataRepository{
    private static final String BASE_URL = "http://api.forismatic.com/api/1.0/";
    private static RetrofitWrapper wrapper;
    private RetrofitService service;

    private RetrofitWrapper() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(RetrofitService.class);
    }

    public static RetrofitWrapper getInstance() {
        if (wrapper == null) {
            return wrapper = new RetrofitWrapper();
        } else {
            return wrapper;
        }
    }

    @Override
    public void getQuote(final QuoteListener quoteListener) {
        Call<Quote> call = service.getQuote();
        call.enqueue(new Callback<Quote>() {
            @Override
            public void onResponse(Call<Quote> call, Response<Quote> response) {
                if (response.isSuccessful()) {
                    quoteListener.onQuoteReady(response.body());
                } else {
                    quoteListener.onQuoteFail();
                }
            }
            @Override
            public void onFailure(Call<Quote> call, Throwable t) {
            }
        });
    }
}
