package com.floralstack.floralstackbackend.environment;


import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface EnvironmentDataAccessServiceProvider {
    Integer createEnvironment(Environment environmnet);
    Environment getEnvironment(Integer id);
    List<Environment> getAllEnvironments();
    Integer updateEnvironment(Environment environment);
    Integer deleteEnvironment(Integer id);
}
