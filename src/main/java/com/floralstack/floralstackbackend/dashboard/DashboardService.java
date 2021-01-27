package com.floralstack.floralstackbackend.dashboard;

import com.floralstack.floralstackbackend.plant.Plant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {
    private DashboardDataAccessServiceProvider dashboardDataAccessServiceProvider;

    @Autowired
    public DashboardService(DashboardDataAccessServiceProvider dashboardDataAccessServiceProvider) {
        this.dashboardDataAccessServiceProvider = dashboardDataAccessServiceProvider;
    }

    public Overview getOverview() {
        return dashboardDataAccessServiceProvider.getOverview();
    }
}
