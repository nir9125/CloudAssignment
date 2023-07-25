package com.stackroute.NewsService.repository;

import com.stackroute.NewsService.model.UserNews;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserNewsRepository extends MongoRepository<UserNews, String> {
}
