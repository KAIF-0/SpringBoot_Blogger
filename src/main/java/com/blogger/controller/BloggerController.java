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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import com.blogger.entity.BlogEntity;



@RestController
@RequestMapping("/blog")
public class BloggerController {
    private Map<Long, BlogEntity> blogStore = new HashMap<>();


    @GetMapping("/getAllBlogs")
    public Collection<BlogEntity> getAllBlogs() {
        return blogStore.values();
    }

    @PostMapping("/createBlog")
    public String createBlog(@RequestBody BlogEntity blog) {
        blogStore.put(blog.getId(), blog);
        return "Blog created successfully!";
    }

    @GetMapping("/get/{id}")
    public BlogEntity getBlogById(@PathVariable Long id) {
        return blogStore.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public BlogEntity deleteBlogById(@PathVariable Long id) {
        return blogStore.remove(id);
    }

    @PutMapping("/update/{id}")
    public BlogEntity updateBlogById(@PathVariable Long id, @RequestBody BlogEntity updatedBlog) {
        return blogStore.put(id, updatedBlog);
    }
    
}
