package com.example.dimitriygeorgiev.dailysmarts.models.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dimitriygeorgiev.dailysmarts.models.Quote;

import java.util.List;

@Dao
public interface QuoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(QuoteEntity quote);

    @Query("DELETE FROM quotes_table")
    void deleteAll();

    @Query("SELECT * from quotes_table ORDER BY id ASC")
    LiveData<List<QuoteEntity>> getAllQuotes();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateQuote(QuoteEntity quote);

    @Delete
    void deleteQuote(QuoteEntity quote);
}
