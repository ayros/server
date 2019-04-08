package com.ayros.server;


import com.ayros.server.dao.NewsRepository;
import com.ayros.server.model.News;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class NewsRepTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private NewsRepository newsRepository;

    @Test
    public void TestAll(){
        List<News> list = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            News news = new News();
            news.setTitle("Title" + i);
            news.setDescription("Description" + i);
            list.add(news);
            entityManager.persist(news);
            entityManager.flush();
        }
        List<News> all = newsRepository.findAll();
        System.out.println(all);
        assertThat(list).isEqualTo(all);
    }

    @Test
    public void TestAfter() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String str_date1 = "24-01-2019 02:12:01";
        Date date1 = sdf.parse(str_date1);
        News news1 = new News();
        news1.setTitle("Title");
        news1.setTime(date1);

        String str_date2 = "25-02-2018 03:10:01";
        Date date2 = sdf.parse(str_date2);
        News news2 = new News();
        news2.setTitle("Title");
        news2.setTime(date2);

        String str_date3 = "21-02-2019 03:12:01";
        Date date3 = sdf.parse(str_date3);
        News news3 = new News();
        news3.setTitle("Title");
        news3.setTime(date3);

        List<News> news = new ArrayList<>();
        news.add(news1);
        news.add(news2);
        news.add(news3);

        for (News n: news){
            entityManager.persist(n);
            entityManager.flush();
        }

        String test_str_date = "20-02-2019 02:12:01";
        Date testDate = sdf.parse(test_str_date);
        List<News> result = newsRepository.findByTimeAfter(testDate);
        System.out.println(result);
        assertThat(result).contains(news3);
    }
}
