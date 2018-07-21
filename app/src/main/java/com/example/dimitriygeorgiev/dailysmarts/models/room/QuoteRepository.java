package com.example.dimitriygeorgiev.dailysmarts.models.room;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.dimitriygeorgiev.dailysmarts.models.Quote;

import java.util.List;

public class QuoteRepository {
    private QuoteDao mQuoteDao;
    private LiveData<List<QuoteEntity>> mAllQuotes;

    QuoteRepository(Application application) {
        QuoteRoomDatabase db = QuoteRoomDatabase.getDatabase(application);
        mQuoteDao = db.dao();
        mAllQuotes = mQuoteDao.getAllQuotes();
    }

    LiveData<List<QuoteEntity>> getmAllQuotes() {
        return mAllQuotes;
    }

    public void insert (QuoteEntity quote) {
        new insertAsyncTask(mQuoteDao).execute(quote);
    }

    public void update (QuoteEntity quote) {
        new updateAsyncTask(mQuoteDao).execute(quote);
    }

    public void delete(QuoteEntity quote) {new deleteAsyncTask(mQuoteDao).execute(quote);}

    public void deleteAll() {new deleteAll(mQuoteDao).execute();}

    private static class insertAsyncTask extends AsyncTask<QuoteEntity, Void, Void> {

        private QuoteDao mAsyncTaskDao;

        insertAsyncTask(QuoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final QuoteEntity... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<QuoteEntity, Void, Void> {
        private QuoteDao mAsyncTaskDao;

        updateAsyncTask(QuoteDao dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final QuoteEntity... params) {
            mAsyncTaskDao.updateQuote(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<QuoteEntity, Void, Void>{
        private QuoteDao mAsyncTaskDao;

        deleteAsyncTask (QuoteDao dao) {
            this.mAsyncTaskDao = dao;
        }


        @Override
        protected Void doInBackground(QuoteEntity... params) {
            mAsyncTaskDao.deleteQuote(params[0]);
            return null;
        }
    }

    private static class deleteAll extends AsyncTask<QuoteEntity, Void, Void>{
        private QuoteDao mAsyncTaskDao;

        deleteAll (QuoteDao dao) {
            this.mAsyncTaskDao = dao;
        }


        @Override
        protected Void doInBackground(QuoteEntity... params) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }
}
