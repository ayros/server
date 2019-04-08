package com.ayros.server.controller;

import com.ayros.server.model.News;
import com.ayros.server.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NotificationService service;

    @RequestMapping(value = "/get_update", method = RequestMethod.POST)
    //public List<News> get_update(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestBody Date date){
    public List<News> get_update(@RequestBody Integer id){
        System.out.println(id);
        List<News> news = service.getSince(id);
        System.out.println(news);
        return news;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public List<News> create(){
        List<News> n = service.getAll();
        System.out.println(n);
        return n;
    }

}
