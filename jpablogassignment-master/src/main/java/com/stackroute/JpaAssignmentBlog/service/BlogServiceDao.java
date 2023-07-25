package com.stackroute.JpaAssignmentBlog.service;

import com.stackroute.JpaAssignmentBlog.exception.BlogAlreadyExistException;
import com.stackroute.JpaAssignmentBlog.exception.BlogNotFoundException;
import com.stackroute.JpaAssignmentBlog.model.Blog;

import java.util.List;

public interface BlogServiceDao {
    //to add a new blog
    public Blog addBlog(Blog blog) throws BlogAlreadyExistException;

    //to fetch all the blogs
    public List<Blog> viewAllBlog();

    // to delete the blog
    Blog deleteBlogById(int bId) throws BlogNotFoundException;

    //to update blog
    public Blog updateBlog(Blog blog) throws BlogNotFoundException;

}
