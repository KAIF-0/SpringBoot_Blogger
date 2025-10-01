package com.blogger.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blogger.entity.UserEntity;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username); //add method to find user by username
}
