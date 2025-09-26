package com.blogger.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.blogger.entity.BlogEntity;
import com.blogger.service.BlogService;

@RestController
@RequestMapping("/blog")
public class BloggerController {
    private Map<Long, BlogEntity> blogStore = new HashMap<>();

    //can be used with autowired/auto injection also
    @Autowired
    private BlogService blogService;

    // private final BlogService blogService;

    // public BloggerController(BlogService blogService) {
    //     this.blogService = blogService;
    // }

    @GetMapping("/getAllBlogs")
    public BlogEntity[] getAllBlogs() {
        return blogService.getAllBlogs();
    }

    @PostMapping("/createBlog")
    public BlogEntity createBlog(@RequestBody BlogEntity blog) {
        BlogEntity createdBlog = blogService.addBlog(blog.getTitle(), blog.getContent(), blog.getAuthor());
        return createdBlog;
    }

    @GetMapping("/get/{id}")
    public BlogEntity getBlogById(@PathVariable Long id) {
        return blogService.getBlogById(id);
    }

    @DeleteMapping("/delete/{id}")
    public BlogEntity deleteBlogById(@PathVariable Long id) {
        return blogService.deleteBlogById(id) ;
    }

    @PutMapping("/update/{id}")
    public BlogEntity updateBlogById(@PathVariable Long id, @RequestBody BlogEntity updatedBlog) {
        return blogService.updateBlog(id, updatedBlog.getTitle(), updatedBlog.getContent(), updatedBlog.getAuthor());
    }

}
