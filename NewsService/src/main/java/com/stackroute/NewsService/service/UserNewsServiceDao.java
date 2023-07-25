package com.stackroute.NewsService.service;

import com.stackroute.NewsService.exception.NewsIdNotFoundException;
import com.stackroute.NewsService.exception.TitleNotFoundException;
import com.stackroute.NewsService.exception.UserIdDoesNotExistsException;
import com.stackroute.NewsService.model.News;
import com.stackroute.NewsService.model.UserNews;

import java.util.List;

public interface UserNewsServiceDao {
    UserNews addUserNews(UserNews user);
    List<UserNews> getAllUserNews();

    UserNews addNews(String userId, News news) throws UserIdDoesNotExistsException;

    boolean deleteNews(String userid, String newsid) throws NewsIdNotFoundException,UserIdDoesNotExistsException;
    public News getNewsByTitle(String userid,String title ) throws UserIdDoesNotExistsException, TitleNotFoundException;
}
