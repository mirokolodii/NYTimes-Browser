package com.unagit.nytimesbrowser.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.unagit.nytimesbrowser.models.Article;

@Database(entities = {Article.class}, version = 1)
public abstract class LocalDataBase extends RoomDatabase {
    public abstract ArticleDao articleDao();
}
