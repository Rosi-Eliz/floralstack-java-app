package com.floralstack.floralstackbackend.resource;

import java.util.List;

public interface ResourceDataAccessServiceProvider {
    Integer createResource(Resource resource);
    Resource getResource(Integer id);
    List<Resource> getAllResources();
    Integer updateResource(Resource environment);
    Integer deleteResource(Integer id);
}
