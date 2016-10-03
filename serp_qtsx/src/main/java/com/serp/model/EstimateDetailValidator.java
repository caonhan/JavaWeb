/**
 * 
 */
package com.serp.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.serp.service.ElementService;

/**
 * @author vuong-bt
 *
 */
@Component
public class EstimateDetailValidator implements Validator {
    @Autowired
    ElementService elementService;

    @Override
    public boolean supports(Class<?> clazz) {
	return EstimateDetail.class.equals(clazz);
    }

    @Override
    public void validate(Object estimateDetail, Errors error) {
	EstimateDetail e = (EstimateDetail) estimateDetail;
	if (elementService.findById(e.getElement().getEId()) != null) {
	    error.rejectValue("element.EId", "NonUniq.Element");
	}
    }

}
