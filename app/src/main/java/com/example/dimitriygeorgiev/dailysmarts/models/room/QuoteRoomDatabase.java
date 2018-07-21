package com.example.dimitriygeorgiev.dailysmarts.models.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {QuoteEntity.class}, version = 1)
public abstract class QuoteRoomDatabase extends RoomDatabase {
    public abstract QuoteDao dao();

    private static QuoteRoomDatabase INSTANCE;

    public static QuoteRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (QuoteRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            QuoteRoomDatabase.class, "quotes_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
