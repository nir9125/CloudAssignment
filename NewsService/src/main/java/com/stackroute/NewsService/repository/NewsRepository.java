package com.stackroute.NewsService.repository;

import com.stackroute.NewsService.model.News;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NewsRepository extends MongoRepository<News,String> {

//    List<Medicine> findByPriceGreaterThan(int price);
    News findByTitle(String news);
}
