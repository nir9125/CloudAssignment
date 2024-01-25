package com.stackroute.JpaAssignmentBlog.controller;

import com.stackroute.JpaAssignmentBlog.exception.BlogAlreadyExistException;
import com.stackroute.JpaAssignmentBlog.exception.BlogNotFoundException;
import com.stackroute.JpaAssignmentBlog.service.BlogServiceDaoImpl;
import com.stackroute.JpaAssignmentBlog.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("blog")
public class BlogController {
    @Autowired
    BlogServiceDaoImpl service;

    //add new blog
    @PostMapping("/add")
    public ResponseEntity<?> addBlog(@RequestBody Blog blog){
        try{
            Blog blog1 = service.addBlog(blog);
            return new ResponseEntity<Blog>(blog1, HttpStatus.CREATED);
        }catch(BlogAlreadyExistException e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    //to view all data
    @GetMapping("viewall")
    public ResponseEntity<?> fetchAllBlog(){
        List<Blog> list = service.viewAllBlog();
        return new ResponseEntity<List>(list, HttpStatus.OK);
    }

    //to delete the blog by specific id
    @DeleteMapping("/delete/{bid}")
    public ResponseEntity<?> deleteBlog(@PathVariable("bid") String id){
        try{
            Blog result = service.deleteBlogById(Integer.parseInt(id));
            return new ResponseEntity<String>("Blog deleted", HttpStatus.OK);
        }catch (BlogNotFoundException e){
            return new ResponseEntity<String>("Blog Not Found", HttpStatus.CONFLICT);
        }catch (NumberFormatException e){
            return new ResponseEntity<String>("ID should be number", HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateBlog(@RequestBody Blog b){
        try{
            Blog b1 = service.updateBlog(b);
            return new ResponseEntity<Blog>(b1, HttpStatus.OK);
        }catch (BlogNotFoundException e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
