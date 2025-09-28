package com.blogger.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blogger.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
}
