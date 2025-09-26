package com.blogger.service;

import com.blogger.entity.BlogEntity;
import com.blogger.repository.BlogRepository;

import org.springframework.stereotype.Service;

@Service
public class BlogService {
    
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public BlogEntity[] getAllBlogs() {
        return blogRepository.findAll().toArray(new BlogEntity[0]);
    }

    public BlogEntity addBlog(String title, String content, String author) {
        BlogEntity blog = new BlogEntity(title, content, author);
        blogRepository.save(blog);

        return blog;
    }

    public BlogEntity getBlogById(Long id) {
        return blogRepository.findById(id).orElse(null);
    }

    public BlogEntity deleteBlogById(Long id) {
        BlogEntity blog = blogRepository.findById(id).orElse(null);
        if (blog != null) {
            blogRepository.deleteById(id);
        }
        return blog;
    }

    public BlogEntity updateBlog(Long id, String title, String content, String author) {
        BlogEntity blog = blogRepository.findById(id).orElse(null);
        if (blog != null) {
            blog.setTitle(title);
            blog.setContent(content);
            blog.setAuthor(author);
            blogRepository.save(blog);
        }
        return blog;
    }

}
