package com.stackroute.NewsService.service;

import com.stackroute.NewsService.exception.NewsIdAlreadyExistsException;
import com.stackroute.NewsService.exception.NewsIdNotFoundException;
import com.stackroute.NewsService.model.News;

import java.util.List;

public interface NewsServiceDao {
    public News addNews(News news) throws NewsIdAlreadyExistsException;
    public List<News> getAllNews();

    boolean deleteNewsById(String id) throws NewsIdNotFoundException;

//    List<Medicine> getMedicinceBasedOnCost(int price);

    News getNewsByTitle(String title);
}
