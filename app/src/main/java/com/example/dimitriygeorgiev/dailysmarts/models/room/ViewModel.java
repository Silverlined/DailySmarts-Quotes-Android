package com.example.dimitriygeorgiev.dailysmarts.models.room;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class ViewModel extends AndroidViewModel {
    private QuoteRepository mRepository;
    private LiveData<List<QuoteEntity>> mAllQuotes;

    public ViewModel(Application application) {
        super(application);
        mRepository = new QuoteRepository(application);
        mAllQuotes = mRepository.getmAllQuotes();
    }

    public LiveData<List<QuoteEntity>> getAllQuotes() {
        return mAllQuotes;
    }

    public void insert(QuoteEntity quote) {
        mRepository.insert(quote);
    }

    public void update(QuoteEntity quote) {
        mRepository.update(quote);
    }

    public void delete(QuoteEntity quote) {mRepository.delete(quote); }

    public void deleteAll() {mRepository.deleteAll(); }
}
