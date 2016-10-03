package com.serp.service;

import java.util.List;

import com.serp.model.Estimate;
import com.serp.model.User;

public interface EstimateService {

    void saveOrUpdate(Estimate estimate);

    void delete(Estimate estimate);

    Estimate findById(Integer id);

    List<Estimate> list();

    boolean hasEditRole(User u);

    boolean hasApproveRole(User u);

    boolean addEstimate(Estimate estimate);
    
    
}
