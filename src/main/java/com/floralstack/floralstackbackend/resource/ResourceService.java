package com.floralstack.floralstackbackend.resource;

import com.floralstack.floralstackbackend.exception.ApiRequestExceptionFactory;
import com.floralstack.floralstackbackend.plant.PlantDataAccessServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {
    private final ResourceDataAccessServiceProvider resourceDataAccessServiceProvider;

    @Autowired
    public ResourceService(ResourceDataAccessServiceProvider resourceDataAccessServiceProvider) {
        this.resourceDataAccessServiceProvider = resourceDataAccessServiceProvider;
    }

    public void createResource(Resource resource) {
        Integer insertion = resourceDataAccessServiceProvider.createResource(resource);
        if (insertion == 0) {
            throw ApiRequestExceptionFactory.failedCreationException;
        }
    }

    public Resource getResource(Integer id) {
        return resourceDataAccessServiceProvider.getResource(id);
    }

    public List<Resource> getAllResources() {
        return resourceDataAccessServiceProvider.getAllResources();
    }

    public void updateResource(Resource environment) {
        resourceDataAccessServiceProvider.updateResource(environment);
    }

    public void deleteResource(Integer id) {
         resourceDataAccessServiceProvider.deleteResource(id);
    }
}
