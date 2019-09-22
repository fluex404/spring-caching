package com.fluex404.springcaching.service;

import com.fluex404.springcaching.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    private List<User> users = new ArrayList<>();

    @Autowired
    UserService(){}

    @PostConstruct
    private void initUsers() {
        users.addAll(Arrays.asList(
                new User("Isa", 18),
                new User("Rara", 17),
                new User("Ahmad", 19)
        ));
    }
    public List<User> findAll() {
        simulateSlowService();
        return this.users;
    }
    private void simulateSlowService() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
