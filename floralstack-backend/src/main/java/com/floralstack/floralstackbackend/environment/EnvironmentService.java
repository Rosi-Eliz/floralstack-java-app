package com.floralstack.floralstackbackend.environment;

import com.floralstack.floralstackbackend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvironmentService {
    private final EnvironmentDataAccessServiceProvider environmentDataAccessServiceProvider;

    @Autowired
    public EnvironmentService(EnvironmentDataAccessServiceProvider environmentDataAccessServiceProvider) {
        this.environmentDataAccessServiceProvider = environmentDataAccessServiceProvider;
    }

    public void createEnvironment(Environment environment) {
        environmentDataAccessServiceProvider.createEnvironment(environment);
    }

    public Environment getEnvironment(Integer id) {
        return environmentDataAccessServiceProvider.getEnvironment(id);
    }

    public List<Environment> getAllEnvironments() {
        return environmentDataAccessServiceProvider.getAllEnvironments();
    }

    public void updateEnvironment(Environment environment) {
        environmentDataAccessServiceProvider.updateEnvironment(environment);
    }

    public void deleteEnvironment(Integer id) {
        environmentDataAccessServiceProvider.deleteEnvironment(id);
    }

}
