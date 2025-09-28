package com.blogger.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "blogs")
// @Getter  //used lombok to avoid boilerplate code of getters and setters
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
@Data  //combination of @Getter, @Setter, @RequiredArgsConstructor, @ToString, and @EqualsAndHashCode
public class BlogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // public BlogEntity() {
    //     this.createdAt = LocalDateTime.now();
    // }

    public BlogEntity(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = LocalDateTime.now();
    }

    // // getter and setter methods
    // public Long getId() {
    //     return id;
    // }

    // public void setId(Long id) {
    //     this.id = id;
    // }

    // public String getTitle() {
    //     return title;
    // }

    // public void setTitle(String title) {
    //     this.title = title;
    // }

    // public String getContent() {
    //     return content;
    // }

    // public void setContent(String content) {
    //     this.content = content;
    // }

    // public String getAuthor() {
    //     return author;
    // }

    // public void setAuthor(String author) {
    //     this.author = author;
    // }

    // public LocalDateTime getCreatedAt() {
    //     return createdAt;
    // }

    // public void setCreatedAt(LocalDateTime createdAt) {
    //     this.createdAt = createdAt;
    // }
}