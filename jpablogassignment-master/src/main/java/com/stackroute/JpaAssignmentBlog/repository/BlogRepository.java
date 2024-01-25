package com.stackroute.JpaAssignmentBlog.repository;

import com.stackroute.JpaAssignmentBlog.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {

//    List<Blog> findByblogId(int id);

}
