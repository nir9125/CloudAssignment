package com.stackroute.NewsService.controller;


import com.stackroute.NewsService.exception.NewsIdAlreadyExistsException;
import com.stackroute.NewsService.exception.NewsIdNotFoundException;
import com.stackroute.NewsService.model.News;
import com.stackroute.NewsService.service.NewsServiceDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("news")
public class NewsController {

    @Autowired
    NewsServiceDaoImpl service;

    @PostMapping("addnews")
    public ResponseEntity<?> addnews(@RequestBody News news){
        try{
            News news1=service.addNews(news);
            return new ResponseEntity<News>(news1, HttpStatus.CREATED);
        }catch (NewsIdAlreadyExistsException e){
            return new ResponseEntity<String>("Id already exist in DB", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("viewall")
    public ResponseEntity<?> getAllNews(){
        List<News> newsList=service.getAllNews();
        return new ResponseEntity<List>(newsList, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteNewsById(@PathVariable String id){
        try {
            boolean result= service.deleteNewsById(id);
            return new ResponseEntity<String>("News deleted", HttpStatus.OK);
        }catch(NewsIdNotFoundException e) {
            return  new ResponseEntity<String >("Id not found in DB", HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping("viewbytitle/{title}")
    public ResponseEntity<News> viewByTitle(@PathVariable String title){
            News news1=service.getNewsByTitle(title);
            return  new ResponseEntity<>(news1,HttpStatus.OK);

    }























}
