package com.blogger.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blogger.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username); //add method to find user by username
    List<UserEntity> findByRole(UserEntity.Role role);
    
    //custom queries
    @Query("SELECT u FROM UserEntity u WHERE u.role = ?1 AND SIZE(u.blogs) > ?2")
    List<UserEntity> findUsersByRoleWithBlogs(UserEntity.Role role, int size);
}
