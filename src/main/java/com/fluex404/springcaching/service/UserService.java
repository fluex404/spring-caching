package com.fluex404.springcaching.service;

import com.fluex404.springcaching.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = {"users"}) // tell spring caching functionality
public class UserService {
    private List<User> users = new ArrayList<>();

    @Autowired
    UserService(){}

    @PostConstruct
    private void initUsers() {
        users.addAll(Arrays.asList(
                new User(101, "Isa", 18),
                new User(102, "Rara", 17),
                new User(103, "Ahmad", 19)
        ));
    }

    @Cacheable // caches the result of findAll() method
    public List<User> findAll() {
        simulateSlowService();
        return this.users;
    }

    @CachePut
    public User insert(User user) {
        users.add(user);
        return user;
    }

    @CacheEvict(allEntries = true)
    public boolean delete(Integer id) {
        users = users.stream().filter(u -> u.getId() != id).collect(Collectors.toList());
        return true;
    }

    private void simulateSlowService() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
