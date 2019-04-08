package com.ayros.server.service;

import com.ayros.server.dao.NewsRepository;
import com.ayros.server.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NewsService implements NotificationService {

    @Autowired
    private NewsRepository repository;

    @Override
    public List<News> getAll() {
        return repository.findAll();
    }

    @Override
    public List<News> getSince(Integer id) {
        return repository.findByIdAfter(id);
    }
}
