package com.akadk.service;

import com.akadk.entity.News;

import java.util.Optional;

public interface NewsService {

    void addNews(News news);

    Optional<News> findNewsById(long id);

    void DeleteNews(long id);

    void updateNews(News news);
}
