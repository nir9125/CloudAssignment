package com.stackroute.NewsService.aop;

import com.stackroute.NewsService.model.News;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Aspect
@Component
public class NewsAspect {

    @Around(value = "execution(* com.stackroute.Newz.mongodb.service.NewsServiceImpl.addNews(..))")
    public News aroundAdviceForAddNews(ProceedingJoinPoint joinPoint){
        System.out.println("Inside Around Advice in Aspect : Business logic to Save patient started at "+ new Date());
        try
        {
            //over-riding the request from postman
            News[] allnews = new News[1];
            News news = new News();
            news.setNewsId("912");
            news.setAuthor("Shay is a good boy");
            news.setTitle("Niranjan ");
            news.setDescription("ram is ram, ram is ram");
            news.setContent("This is the content section for the news");
            news.setUrl("this is url holder");
            news.setUrlToIamge("this will contain the url to image");
            news.setPublishedAt("today date");

            allnews[0]= news;
            News news1 = (News) joinPoint.proceed(allnews);
            log.info("this is the log for news1");
            return  news1;
        }
        catch (Throwable a ){
            System.out.println("Inside Around method business logic failed"+ a.getMessage());

        }
        System.out.println("Inside Around Advice in Aspect the Business logic  ends at "+new Date());
        return null;
    }

}
