package com.serp.dao;

import java.util.List;

import com.serp.model.Estimate;

public interface EstimateDAO {

	void saveOrUpdate(Estimate transientInstance);

	void delete(Estimate persistentInstance);

	Estimate findById(Integer id);

	List<Estimate> list();

	/**add new Estimate
	 * @param transientInstance
	 */
	void persist(Estimate transientInstance);

}
