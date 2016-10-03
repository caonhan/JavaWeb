package com.vn.gcs.serp.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.vn.gcs.serp.model.Users;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao <Integer, Users> implements UserDao {
	@SuppressWarnings("unchecked")
	public List<Users> findAllUsers() {
		Criteria criteria = createEntityCriteria();
        return (List<Users>) criteria.list();
	}
}
