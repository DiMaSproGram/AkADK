package com.akadk.service.impl;

import com.akadk.common.Constant;
import com.akadk.entity.News;
import com.akadk.exception.CantDeleteException;
import com.akadk.exception.CantUpdateException;
import com.akadk.repository.NewsRepository;
import com.akadk.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;


@Service
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public void addNews(News news) {
        newsRepository.save(news);
    }

    @Override
    public Optional<News> findNewsById(long id) {
        return newsRepository.findById(id);
    }

    @Override
    public void DeleteNews(long id) {
        newsRepository.delete(findNewsById(id).
                orElseThrow(() ->
                        new CantDeleteException(String.format(Constant.CANT_DELETE_BY_ID_EXCEPTION,
                                id,
                                News.class))
                ));
    }

    @Override
    public void updateNews(News news) {
        findNewsById(news.getId()).orElseThrow(() ->
                new CantUpdateException(String.format(Constant.CANT_UPDATE_BY_ID_EXCEPTION,
                        news.getId(),
                        News.class))
        );
        newsRepository.save(news);
    }
}
