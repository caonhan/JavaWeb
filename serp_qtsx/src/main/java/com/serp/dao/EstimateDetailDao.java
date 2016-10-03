/**
 * 
 */
package com.serp.dao;

import java.util.List;

import com.serp.model.EstimateDetail;

/**
 * @author Blacky
 *
 */
public interface EstimateDetailDao {
	EstimateDetail findById(Integer id);

	/**
	 * @return
	 */
	List<EstimateDetail> list();
}
