package com.blogger.service;

import com.blogger.entity.BlogEntity;
import com.blogger.entity.UserEntity;
import com.blogger.repository.BlogRepository;
import com.blogger.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    public BlogService(BlogRepository blogRepository, UserRepository userRepository) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
    }

    public BlogEntity[] getAllBlogsforUser(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found!"));
        return user.getBlogs().toArray(BlogEntity[]::new);
    }

    public BlogEntity addBlogforUser(String title, String content, String author, Long userId) {
        BlogEntity blog = new BlogEntity(title, content, author);
        // fetch user and set in blog
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found!"));
        blog.setUser(user);
        blogRepository.save(blog);
        return blog;
    }

    public BlogEntity getBlogforUserById(Long userId, Long id) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found!"));
        return user.getBlogs().stream().filter(b -> b.getId().equals(id)).findFirst().orElse(null);
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
