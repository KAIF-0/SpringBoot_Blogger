package com.blogger.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.blogger.entity.APIResponseEntity;
import com.blogger.entity.BlogEntity;
import com.blogger.entity.APIErrorEntity;
import com.blogger.service.BlogService;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/getAllBlogs/{userId}")
    public ResponseEntity<?> getAllBlogsforUser(@PathVariable Long userId) {
        try {
            BlogEntity[] blogs = blogService.getAllBlogsforUser(userId);
            return ResponseEntity.ok(new APIResponseEntity<>(200, "Blogs fetched successfully!", blogs));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIErrorEntity(500, e.getMessage()));
        }
    }

    @PostMapping("/createBlog/{userId}")
    public ResponseEntity<?> createBlog(@RequestBody BlogEntity blog, @PathVariable Long userId) {
        try {
            BlogEntity createdBlog = blogService.addBlogforUser(blog.getTitle(), blog.getContent(), blog.getAuthor(), userId);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new APIResponseEntity<>(201, "Blog Created Successfully!", createdBlog));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIErrorEntity(500, e.getMessage()));
        }
    }

    @GetMapping("/get/{userId}/{id}")
    public ResponseEntity<?> getBlogById(@PathVariable Long userId, @PathVariable Long id) {
        try {
            BlogEntity blog = blogService.getBlogforUserById(userId, id);
            if (blog != null) {
                return ResponseEntity.ok(new APIResponseEntity<>(200, "Blog fetched successfully!", blog));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new APIErrorEntity(404, "Blog not found!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIErrorEntity(500, e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBlogById(@PathVariable Long id) {
        try {
            BlogEntity deletedBlog = blogService.deleteBlogById(id);
            if (deletedBlog != null) {
                return ResponseEntity.ok(new APIResponseEntity<>(200, "Blog deleted successfully!", deletedBlog));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new APIErrorEntity(404, "Blog not found!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIErrorEntity(500, e.getMessage()));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBlogById(@PathVariable Long id, @RequestBody BlogEntity updatedBlog) {
        try {
            BlogEntity blog = blogService.updateBlog(id, updatedBlog.getTitle(), updatedBlog.getContent(),
                    updatedBlog.getAuthor());
            if (blog != null) {
                return ResponseEntity.ok(new APIResponseEntity<>(200, "Blog updated successfully!", blog));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new APIErrorEntity(404, "Blog not found!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIErrorEntity(500, e.getMessage()));
        }
    }
}
