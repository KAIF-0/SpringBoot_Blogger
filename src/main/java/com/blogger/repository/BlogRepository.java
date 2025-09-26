package com.blogger.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blogger.entity.BlogEntity;

public interface BlogRepository extends JpaRepository<BlogEntity, Long> {
    
}
