
package com.serp.service;

import java.util.List;

import com.serp.model.EstimateDetail;

/**
 * @author vuong-bt
 *
 */
public interface EstimateDetailService {
	EstimateDetail findById(int id);
	List<EstimateDetail> list();
}
