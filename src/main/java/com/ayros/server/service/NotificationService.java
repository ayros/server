package com.ayros.server.service;

import com.ayros.server.model.News;

import java.util.Date;
import java.util.List;

public interface NotificationService {

    public List<News> getAll();

    public List<News> getSince(Integer id);
}
