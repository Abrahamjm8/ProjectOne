package com.example.boot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.boot.repository.UserDao;
import com.example.boot.entities.User;
import com.example.boot.exceptions.EntityNotFound;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User getUserByUsername(String username) {
        Optional<User> possibleUser = this.userDao.getUserByUsername(username);
        if (possibleUser.isPresent()) {
            return possibleUser.get();
        } else {
            throw new EntityNotFound("This user does not exist");
        }
    }

    public String createUser(User user) {
        this.userDao.createUser(user.getUsername(), user.getPassword());
        return "User has been created successfully";
    }
}
