package com.stackroute.NewsService.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class UserNews {

    String userId;
    List<News> newsList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }
}
