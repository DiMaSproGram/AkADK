package com.akadk.repository;

import com.akadk.entity.Mark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkRepository extends JpaRepository<Mark,Long> {
    Mark findById(long id);
}
