package com.stackroute.NewsService.controller;

import com.stackroute.NewsService.exception.NewsIdNotFoundException;
import com.stackroute.NewsService.exception.TitleNotFoundException;
import com.stackroute.NewsService.exception.UserIdDoesNotExistsException;
import com.stackroute.NewsService.model.News;
import com.stackroute.NewsService.model.UserNews;
import com.stackroute.NewsService.service.UserNewsServiceDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    UserNewsServiceDaoImpl service;


    @PostMapping("adduser")
    public ResponseEntity<?> addUser(@RequestBody UserNews user){
        UserNews userNews=service.addUserNews(user);
        return new ResponseEntity<UserNews>(userNews,HttpStatus.CREATED);

    }

    @PostMapping("/addnews/{userId}")
    public ResponseEntity<?> addNews(@PathVariable String userId, @RequestBody News news){
        UserNews userNews=null;
        try{
            userNews=service.addNews(userId, news);
            return new ResponseEntity<>(userNews, HttpStatus.OK);
        }catch (UserIdDoesNotExistsException e){
            return new ResponseEntity<String >("Invalid User Id", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("viewAllUser")
    public ResponseEntity<?> viewAllUser(){
        List<UserNews> userNews= service.getAllUserNews();
        return new ResponseEntity<List> (userNews, HttpStatus.OK);
    }

    @DeleteMapping("/delete/news/{userId}/{newsId}")
    public ResponseEntity<?> deleteNews(@PathVariable String userId, @PathVariable String newsId){
        try{
            boolean result = service.deleteNews(userId, newsId);
            return new ResponseEntity<String >("News is deleted", HttpStatus.OK);
        }catch (UserIdDoesNotExistsException e){
            return  new ResponseEntity<String>("Invalid userId", HttpStatus.NOT_FOUND);
        }catch (NewsIdNotFoundException e){
            return  new ResponseEntity<String>("Invalid medicine id", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/viewbytitle/news/{userid}/{newsId}")
    public ResponseEntity<?> getNewsByTitle(@PathVariable("userid") String userid, @PathVariable String title){
        try {
            News news= service.getNewsByTitle(userid, title);
            return new ResponseEntity<News>(news, HttpStatus.OK);
        }catch (UserIdDoesNotExistsException e){
            return  new ResponseEntity<String>("invalid userid ", HttpStatus.NOT_FOUND);
        }catch (TitleNotFoundException e){
            return new ResponseEntity<>("News Title not found", HttpStatus.NOT_FOUND);
        }
    }



}
