package com.stackroute.JpaAssignmentBlog.service;

import com.stackroute.JpaAssignmentBlog.exception.BlogAlreadyExistException;
import com.stackroute.JpaAssignmentBlog.exception.BlogNotFoundException;
import com.stackroute.JpaAssignmentBlog.model.Blog;
import com.stackroute.JpaAssignmentBlog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//this file contains the business logic
//Exception handling is done
@Service
public class BlogServiceDaoImpl implements BlogServiceDao {
    @Autowired
    BlogRepository repository;

    //logic implementation for add a new blog
    @Override
    public Blog addBlog(Blog blog) throws BlogAlreadyExistException {
        Blog blog1;
        //to handle NULL pointer Exception we use optional class
        Optional<Blog> optionalBlog = repository.findById(blog.getBlogId());
        if(optionalBlog.isPresent()){
            throw new BlogAlreadyExistException("Duplicate blogIds not allowed");
        }else
            blog1 = repository.save(blog);

        return blog1;
    }

    //logic implementation for fetching al blogs
    @Override
    public List<Blog> viewAllBlog() {
        return repository.findAll();
    }

    //logic implementation for delete all blogs
    @Override
    public Blog deleteBlogById(int bId) throws BlogNotFoundException {
        Optional<Blog> optionalBlog = repository.findById(bId);
        if(optionalBlog.isPresent()){
            repository.deleteById(bId);
            return optionalBlog.get();
        }else
            throw new BlogNotFoundException("Blog Not Found");

    }

    @Override
    public Blog updateBlog(Blog blog) throws BlogNotFoundException {
        Optional<Blog> optionalBlog = repository.findById(blog.getBlogId());
        if(optionalBlog.isPresent()){
            return repository.save(blog);
        }else
            throw new BlogNotFoundException("Blog Not Found");
    }
}
