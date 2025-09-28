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
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.blogger.entity.BlogEntity;
import com.blogger.service.BlogService;

@RestController
@RequestMapping("/blog")
public class BloggerController {
    private Map<Long, BlogEntity> blogStore = new HashMap<>();

    // can be used with autowired/auto injection also
    @Autowired
    private BlogService blogService;

    // private final BlogService blogService;

    // public BloggerController(BlogService blogService) {
    // this.blogService = blogService;
    // }

    @GetMapping("/getAllBlogs")
    public ResponseEntity<BlogEntity[]> getAllBlogs() {
        try {
            BlogEntity[] blogs = blogService.getAllBlogs();
            return new ResponseEntity<>(blogs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createBlog")
    public ResponseEntity<?> createBlog(@RequestBody BlogEntity blog) {  //question marks here means anything as body
        try {
            BlogEntity createdBlog = blogService.addBlog(blog.getTitle(), blog.getContent(), blog.getAuthor());
            return new ResponseEntity<>("Blog Created Successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BlogEntity> getBlogById(@PathVariable Long id) {
        try {
            BlogEntity blog = blogService.getBlogById(id);
            if (blog != null) {
                return new ResponseEntity<>(blog, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BlogEntity> deleteBlogById(@PathVariable Long id) {
        try {
            BlogEntity deletedBlog = blogService.deleteBlogById(id);
            if (deletedBlog != null) {
                return new ResponseEntity<>(deletedBlog, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BlogEntity> updateBlogById(@PathVariable Long id, @RequestBody BlogEntity updatedBlog) {
        try {
            BlogEntity blog = blogService.updateBlog(id, updatedBlog.getTitle(), updatedBlog.getContent(),
                    updatedBlog.getAuthor());
            if (blog != null) {
                return new ResponseEntity<>(blog, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
