package com.stackroute.NewsService.service;

import com.stackroute.NewsService.exception.NewsIdNotFoundException;
import com.stackroute.NewsService.exception.TitleNotFoundException;
import com.stackroute.NewsService.exception.UserIdDoesNotExistsException;
import com.stackroute.NewsService.model.News;
import com.stackroute.NewsService.model.UserNews;
import com.stackroute.NewsService.repository.UserNewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserNewsServiceDaoImpl implements UserNewsServiceDao{
    @Autowired
    UserNewsRepository repository;

    @Override
    public  UserNews addUserNews(UserNews user){
        Optional<UserNews> userNews= repository.findById(user.getUserId());
        if(userNews.isEmpty()){
            return repository.save(user);
        }
        else{
            UserNews user1= userNews.get();
            List<News> existNews=user1.getNewsList();
            List<News> newNews=user.getNewsList();
            user1.setNewsList(existNews);
            return repository.save(user1);
        }
    }


    @Override
    public List<UserNews> getAllUserNews(){
        return repository.findAll();
    }

    @Override
    public UserNews addNews(String userId, News news) throws UserIdDoesNotExistsException{
        Optional<UserNews> user = repository.findById(userId);
        if(user.isEmpty()){
            throw new UserIdDoesNotExistsException();
        }
        else {
            UserNews user1 = user.get();
            List<News> existNews = user1.getNewsList();
            existNews.add(news);
            user1.setNewsList(existNews);
            return repository.save(user1);

        }
    }

    @Override
    public boolean  deleteNews(String userId, String newsId) throws UserIdDoesNotExistsException, NewsIdNotFoundException {
        Optional<UserNews> user = repository.findById(userId);
        if(user.isEmpty()){
            throw new UserIdDoesNotExistsException();
        }
        else {
            UserNews user1 = user.get();
            List<News> existNews = user1.getNewsList();
            existNews.removeIf((news)->news.getNewsId().equals(newsId));
            user1.setNewsList(existNews);
             repository.save(user1);
             return true;
        }
    }



    @Override
    public News getNewsByTitle(String userId, String title) throws UserIdDoesNotExistsException, TitleNotFoundException{
        Optional <UserNews> user=repository.findById(userId);
        if(user.isEmpty()){
            throw  new UserIdDoesNotExistsException();
        }
        else {
            UserNews existUser=user.get();
            List<News> existNewsList=existUser.getNewsList();
            Optional<News> newsResult=existNewsList.stream().filter(news->news.getTitle()
                    .equals(title)).findFirst();

            if(newsResult.isEmpty()){
                throw new TitleNotFoundException();
            }else {
                return newsResult.get();
            }
        }
    }
}
