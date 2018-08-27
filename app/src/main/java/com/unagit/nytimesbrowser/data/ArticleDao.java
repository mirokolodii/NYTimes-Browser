package com.unagit.nytimesbrowser.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.unagit.nytimesbrowser.models.Article;

import java.util.List;

@Dao
public interface ArticleDao {
    @Insert
    void insert(Article article);

    @Delete
    void delete(Article article);

    @Query("SELECT * from favorites_table")
    List<Article> getFavorites();

}
