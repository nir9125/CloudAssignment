package com.stackroute.NewsService.service;


import com.stackroute.NewsService.exception.NewsIdAlreadyExistsException;
import com.stackroute.NewsService.exception.NewsIdNotFoundException;
import com.stackroute.NewsService.model.News;
import com.stackroute.NewsService.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceDaoImpl implements NewsServiceDao{

    @Autowired
    NewsRepository repository;

    @Override
    public News addNews(News news) throws NewsIdAlreadyExistsException{
        Optional<News> optionalNews = repository.findById(news.getNewsId());
        if(optionalNews.isEmpty()){
            News news1=repository.save(news);
            return news1;
        }
        else
            throw new NewsIdAlreadyExistsException();
    }

    @Override
    public List<News> getAllNews(){
        return repository.findAll();
    }


    @Override
    public boolean deleteNewsById(String id) throws NewsIdNotFoundException{
        Optional<News> optionalNews=repository.findById(id);
        if(optionalNews.isPresent()){
            repository.deleteById(id);
        }
        else
            throw new NewsIdNotFoundException();
        return  true;
    }


    @Override
    public News getNewsByTitle(String name){
        return repository.findByTitle(name);
    }
}
