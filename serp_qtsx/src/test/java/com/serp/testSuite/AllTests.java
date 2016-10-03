package com.serp.testSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.serp.dao.EstimateDAOImplTest;
import com.serp.dao.FunctionDaoTest;
import com.serp.dao.QuotationDAOTest;
import com.serp.dao.QuotationDetailDAOTest;
import com.serp.dao.RoleDaoTest;
import com.serp.service.EstimateServiceImplTest;
import com.serp.service.FunctionServiceTest;
import com.serp.service.RoleServiceTest;

@RunWith(Suite.class)
@SuiteClasses({RoleDaoTest.class, RoleServiceTest.class, FunctionDaoTest.class, FunctionServiceTest.class, QuotationDAOTest.class, QuotationDetailDAOTest.class,EstimateDAOImplTest.class,EstimateServiceImplTest.class})
public class AllTests {

}
