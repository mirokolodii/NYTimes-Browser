package com.unagit.nytimesbrowser.models;

import java.util.List;

public class DataWrapper {
    private List<Article> results;
    private String status;

    public List<Article> getArticles() {
        return results;
    }

    public String getStatus() {
        return status;
    }
}
