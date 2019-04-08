package com.ayros.server.dao;

import com.ayros.server.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    public News findByTitle(String title);

    public List<News>  findByTimeAfter(Date date);

    public List<News> findByIdAfter(Integer id);

}
