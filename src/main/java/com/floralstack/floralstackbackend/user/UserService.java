package com.floralstack.floralstackbackend.user;

import com.floralstack.floralstackbackend.exception.ApiRequestException;
import com.floralstack.floralstackbackend.exception.ApiRequestExceptionFactory;
import com.floralstack.floralstackbackend.plant.Plant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
    private final UserDataAccessServiceProvider userDataAccessServiceProvider;

    @Autowired
    public UserService(UserDataAccessServiceProvider userDataAccessServiceProvider) {
        this.userDataAccessServiceProvider = userDataAccessServiceProvider;
    }

    public void createUser(User user) {
        userDataAccessServiceProvider.createUser(user);
    }

    public User getUser(Integer id) {
        return userDataAccessServiceProvider.getUser(id);
    }
    public List<User> getAllUsers(){
        return userDataAccessServiceProvider.getAllUsers();
    }

    public void updateUser(User user) {
        if (user.getId() == null) {
            throw ApiRequestExceptionFactory.missingIdException;
        }
        Integer update = userDataAccessServiceProvider.updateUser(user);
        if (update == 0) {
            throw ApiRequestExceptionFactory.failedUpdateException;
        }
    }

    public void deleteUser(Integer id) {
        Integer delete = userDataAccessServiceProvider.deleteUser(id);
        if (delete == 0) {
            throw ApiRequestExceptionFactory.failedDeleteException;
        }
    }

}
