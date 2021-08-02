package com.akadk.repository;

import com.akadk.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News,Long> {
    News findById(long id);
}
