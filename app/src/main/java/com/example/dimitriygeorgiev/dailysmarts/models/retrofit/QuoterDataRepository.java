package com.example.dimitriygeorgiev.dailysmarts.models.retrofit;

import com.example.dimitriygeorgiev.dailysmarts.models.Quote;

public interface QuoterDataRepository {

    void getQuote(QuoteListener cityListener);
    interface QuoteListener {

        void onQuoteReady(Quote quoteOfTheDay);

        void onQuoteFail();

    }
}
