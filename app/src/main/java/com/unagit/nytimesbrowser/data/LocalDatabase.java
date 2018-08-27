package com.unagit.nytimesbrowser.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.provider.SyncStateContract;

import com.unagit.nytimesbrowser.helpers.Constants;
import com.unagit.nytimesbrowser.models.Article;

@Database(entities = {Article.class}, version = 1)
public abstract class LocalDatabase extends RoomDatabase {
    public abstract ArticleDao articleDao();

    private static LocalDatabase DB_INSTANCE;

    public static LocalDatabase getLocalDBInstance(Context context) {
        if (DB_INSTANCE == null) {
            DB_INSTANCE = Room.databaseBuilder(context, LocalDatabase.class, Constants.DB.localDBName)
                    .build();
        }
        return DB_INSTANCE;
    }
}
