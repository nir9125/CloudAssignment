package com.stackroute.JpaAssignmentBlog.aop;

import com.stackroute.JpaAssignmentBlog.model.Blog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Aspect
@Component
public class BlogAspect {

    @Around(value = "execution(* com.stackroute.blog_assignment.service.BlogServiceDaoImp.addBlog(..))")
    public Blog aroundAdviceForAddBlog(ProceedingJoinPoint joinPoint){
        System.out.println("Inside Around Advice in Aspect : Business logic to Save patient started at "+ new Date());
        try
        {
            Blog [] blogs = new Blog[1];
            Blog blog = new Blog();
            blog.setBlogId(70);
            blog.setBlogContent("this is the content section for the blog.");
            blog.setBlogTitle("Ram");
            blog.setAuthorName("Krishna");

            blogs[0]=blog;
            Blog blog1 = (Blog) joinPoint.proceed(blogs);
            log.info("this is where the values are set through around annotation.");
            return  blog1;
        }
        catch (Throwable a ){
            System.out.println("Inside Around method business logic failed"+ a.getMessage());

        }
        System.out.println("Inside Around Advice in Aspect the Business logic  ends at "+new Date());
        return null;
    }


}
