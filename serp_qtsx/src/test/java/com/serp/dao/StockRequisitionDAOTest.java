package com.serp.dao;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.serp.model.Customer;
import com.serp.model.Element;
import com.serp.model.LimitInventory;
import com.serp.model.LimitInventoryDetail;
import com.serp.model.Material;
import com.serp.model.Orders;
import com.serp.model.Role;
import com.serp.model.Status;
import com.serp.model.StockRequisition;
import com.serp.model.StockRequisitionDetails;
import com.serp.model.User;

/**
 * The Class StockRequisitionDAOTest.
 */
public class StockRequisitionDAOTest {
	
	/** The formatter. */
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

	/** The session. */
	@Mock
	Session session;

	/** The query. */
	@Mock
	org.hibernate.Query query;

	/** The session factory. */
	@Mock
	SessionFactory sessionFactory;

	/** The stock requisition dao. */
	@InjectMocks
	StockRequisitionDAOImpl stockRequisitionDao;

	/** The srs. */
	@Spy
	List<StockRequisition> srs = new ArrayList<StockRequisition>();

	/**
	 * Before method.
	 */
	@BeforeMethod
	public void beforeMethod() {
		MockitoAnnotations.initMocks(this);
		session = mock(Session.class);
		query = mock(org.hibernate.Query.class);
		sessionFactory = mock(SessionFactory.class);
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.createQuery(anyString())).thenReturn(query);

		stockRequisitionDao.setSessionFactory(sessionFactory);

		srs = getStockRequisitionList();
	}

	/**
	 * Test get stock requisition by id.
	 */
	@Test
	public void testGetStockRequisitionById() {
		StockRequisition stockRequisition = srs.get(0);

		when(session.get(any(Class.class), anyInt())).thenReturn(stockRequisition);
		Assert.assertEquals(stockRequisition, stockRequisitionDao.getStockRequisitionById(0));
	}

	/**
	 * Test get stock requisition by id throw exception.
	 */
	@Test(expectedExceptions = NullPointerException.class)
	public void testGetStockRequisitionByIdThrowException() {
		when(session.get(any(Class.class), anyInt())).thenThrow(new NullPointerException());
		stockRequisitionDao.getStockRequisitionById(0);
	}

	/**
	 * Test get stock requisition by id return null.
	 */
	@Test
	public void testGetStockRequisitionByIdReturnNull() {
		when(session.get(any(Class.class), anyInt())).thenReturn(null);
		stockRequisitionDao.getStockRequisitionById(0);
	}

	/**
	 * Test delete stock requisition by id.
	 */
	@Test
	public void testDeleteStockRequisitionById() {
		StockRequisition stockRequisition = srs.get(0);

		when(session.get(any(Class.class), anyInt())).thenReturn(stockRequisition);
		doNothing().when(session).delete(any(StockRequisition.class));

		stockRequisitionDao.deleteStockRequisition(1);

		verify(session, atLeastOnce()).get(any(Class.class), anyInt());
		verify(session, atLeastOnce()).delete(any(StockRequisition.class));
	}

	/**
	 * Test delete stock requisition by id throw exception.
	 */
	@Test(expectedExceptions = RuntimeException.class)
	public void testDeleteStockRequisitionByIdThrowException() {
		StockRequisition stockRequisition = srs.get(0);

		when(session.get(any(Class.class), anyInt())).thenReturn(stockRequisition);
		doThrow(new RuntimeException()).doNothing().when(session).delete(any(StockRequisition.class));

		stockRequisitionDao.deleteStockRequisition(1);

		verify(session, atLeastOnce()).get(any(Class.class), anyInt());
		verify(session, atLeastOnce()).delete(any(StockRequisition.class));
	}

	/**
	 * Test get all stock requisitions.
	 */
	@Test
	public void testGetAllStockRequisitions() {
		when(query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)).thenReturn(query);
		when(query.list()).thenReturn(srs);

		stockRequisitionDao.getAllStockRequisitions();

		verify(query, atLeastOnce()).list();
	}

	/**
	 * Test get all stock requisitions throw exception.
	 */
	@Test(expectedExceptions = RuntimeException.class)
	public void testGetAllStockRequisitionsThrowException() {
		when(query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)).thenReturn(query);
		when(query.list()).thenThrow(new RuntimeException());
		stockRequisitionDao.getAllStockRequisitions();
		verify(query, atLeastOnce()).list();
	}

	/**
	 * Test save detail.
	 */
	@Test
	public void testSaveDetail() {
		when(session.save(any(StockRequisitionDetails.class))).thenReturn(1);
		stockRequisitionDao.saveOrUpdateStockRequisitionDetails(new StockRequisitionDetails());
		verify(session, atLeastOnce()).save(any(StockRequisitionDetails.class));
	}

	/**
	 * Test update detail.
	 */
	@Test
	public void testUpdateDetail() {
		StockRequisitionDetails stockRequisitionDetails = srs.get(0).getStockRequisitionDetailses().iterator().next();
		doNothing().when(session).update(any(StockRequisitionDetails.class));
		stockRequisitionDao.saveOrUpdateStockRequisitionDetails(stockRequisitionDetails);
		verify(session, atLeastOnce()).update(any(StockRequisitionDetails.class));
	}

	/**
	 * Test save or update detail throw exception.
	 */
	@Test(expectedExceptions = RuntimeException.class)
	public void testSaveOrUpdateDetailThrowException() {
		StockRequisitionDetails stockRequisitionDetails = srs.get(0).getStockRequisitionDetailses().iterator().next();

		doThrow(new RuntimeException()).doNothing().when(session).update(any(StockRequisitionDetails.class));
		stockRequisitionDao.saveOrUpdateStockRequisitionDetails(stockRequisitionDetails);
		verify(session, atLeastOnce()).update(any(StockRequisitionDetails.class));
	}

	/**
	 * Test delete detail.
	 */
	@Test
	public void testDeleteDetail() {
		StockRequisitionDetails stockRequisitionDetails = srs.get(0).getStockRequisitionDetailses().iterator().next();

		when(session.get(any(Class.class), anyInt())).thenReturn(stockRequisitionDetails);
		doNothing().when(session).delete(any(StockRequisitionDetails.class));

		stockRequisitionDao.deleteStockRequisitionDetails(1);

		verify(session, atLeastOnce()).get(any(Class.class), anyInt());
		verify(session, atLeastOnce()).delete(any(StockRequisitionDetails.class));
	}

	/**
	 * Test delete detail throw exception.
	 */
	@Test(expectedExceptions = RuntimeException.class)
	public void testDeleteDetailThrowException() {
		StockRequisitionDetails stockRequisitionDetails = srs.get(0).getStockRequisitionDetailses().iterator().next();

		when(session.get(any(Class.class), anyInt())).thenReturn(stockRequisitionDetails);
		doThrow(new RuntimeException()).doNothing().when(session).delete(any(StockRequisitionDetails.class));

		stockRequisitionDao.deleteStockRequisitionDetails(1);

		verify(session, atLeastOnce()).get(any(Class.class), anyInt());
		verify(session, atLeastOnce()).delete(any(StockRequisitionDetails.class));
	}

	/**
	 * Test get all details by id.
	 */
	@Test
	public void testGetAllDetailsById() {
		List<StockRequisitionDetails> details = new ArrayList<>(srs.get(0).getStockRequisitionDetailses());

		when(query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)).thenReturn(query);
		when(query.list()).thenReturn(details);

		stockRequisitionDao.getAllDetailsByRequisitionId(1);
		verify(query, atLeastOnce()).list();
	}

	/**
	 * Test get all details by id throw exception.
	 */
	@Test(expectedExceptions = NullPointerException.class)
	public void testGetAllDetailsByIdThrowException() {
		when(query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)).thenReturn(query);
		when(query.list()).thenThrow(new NullPointerException());

		stockRequisitionDao.getAllDetailsByRequisitionId(1);
		verify(query, atLeastOnce()).list();
	}

	/**
	 * Test get detail by id.
	 */
	@Test
	public void testGetDetailById() {
		StockRequisitionDetails stockRequisitionDetails = srs.get(0).getStockRequisitionDetailses().iterator().next();

		when(session.get(any(Class.class), anyInt())).thenReturn(stockRequisitionDetails);
		Assert.assertEquals(stockRequisitionDetails, stockRequisitionDao.getDetailById(1));
	}

	/**
	 * Test get detail by id throw exception.
	 */
	@Test(expectedExceptions = RuntimeException.class)
	public void testGetDetailByIdThrowException() {
		when(session.get(any(Class.class), anyInt())).thenThrow(new RuntimeException());
		stockRequisitionDao.getDetailById(0);
	}

	/**
	 * Test get detail by id return null.
	 */
	@Test
	public void testGetDetailByIdReturnNull() {
		when(session.get(any(Class.class), anyInt())).thenReturn(null);
		Assert.assertNull(stockRequisitionDao.getDetailById(0));
	}

	/**
	 * Test save stock requisition.
	 */
	@Test
	public void testSaveStockRequisition() {
		when(session.save(any(StockRequisition.class))).thenReturn(anyInt());
		stockRequisitionDao.saveStockRequisition(srs.get(0));
		verify(session, atLeastOnce()).save(any(StockRequisition.class));
	}
	
	/**
	 * Test save stock requisition throw exception.
	 */
	@Test(expectedExceptions = RuntimeException.class)
	public void testSaveStockRequisitionThrowException(){
		when(session.save(any(StockRequisition.class))).thenThrow(new RuntimeException());
		stockRequisitionDao.saveStockRequisition(srs.get(0));
		verify(session, atLeastOnce()).save(any(StockRequisition.class));
	}
	
	/**
	 * Test update stock requisition.
	 */
	@Test
	public void testUpdateStockRequisition() {
		doNothing().when(session).update(any(StockRequisition.class));
		stockRequisitionDao.updateStockRequisition(srs.get(0));
		verify(session, atLeastOnce()).update(any(StockRequisition.class));
	}
	
	/**
	 * Test update stock requisition throw exception.
	 */
	@Test(expectedExceptions = RuntimeException.class)
	public void testUpdateStockRequisitionThrowException(){
		doThrow(new RuntimeException()).when(session).update(any(StockRequisition.class));
		stockRequisitionDao.updateStockRequisition(srs.get(0));
		verify(session, atLeastOnce()).save(any(StockRequisition.class));
	}

	/**
	 * Gets the stock requisition list.
	 *
	 * @return the stock requisition list
	 */
	public List<StockRequisition> getStockRequisitionList() {
		try {
			// Create status for testing
			Status status1 = new Status();
			status1.setStatusId(1);
			status1.setStatusName("approve_awaiting");

			Status status2 = new Status();
			status2.setStatusId(2);
			status2.setStatusName("approved");

			// Create roles for testing
			Role role1 = new Role();
			role1.setRoleId("NVCN");
			role1.setRoleName("Nhan vien cong nghe");

			Role role2 = new Role();
			role2.setRoleId("BGD");
			role2.setRoleName("Ban Giam Doc");

			Role role3 = new Role();
			role3.setRoleId("TX");
			role3.setRoleName("Truong Xuong");

			Role role4 = new Role();
			role4.setRoleId("TPTCKT");
			role4.setRoleName("Truong phong Tai chinh Ke toan");

			// Create users for testing
			User user1 = new User();
			user1.setName("Nhan vien cong nghe");
			user1.setUserId("nvcn");
			user1.setPassword("nvcn");
			user1.setRole(role1);

			User user2 = new User();
			user2.setName("Ban Giam Doc");
			user2.setUserId("bgd");
			user2.setPassword("bgd");
			user2.setRole(role2);

			User user3 = new User();
			user3.setName("Truong Xuong");
			user3.setUserId("txuong");
			user3.setPassword("txuong");
			user3.setRole(role3);

			User user4 = new User();
			user4.setName("Truong phong Tai chinh Ke toan");
			user4.setUserId("tptckt");
			user4.setPassword("tptckt");
			user4.setRole(role4);

			// Create customers for testing
			Customer customer1 = new Customer();
			customer1.setCustomerId(1);
			customer1.setCompanyName("FPT Software");

			Customer customer2 = new Customer();
			customer2.setCustomerId(2);
			customer2.setCompanyName("HCM University of Science");

			// Create orders for testing
			Orders order1 = new Orders();
			order1.setOrderId("NEP-001");
			order1.setCustomer(customer2);
			order1.setUserByUserId(user1);
			order1.setProjectName("Hợp đồng sản xuất ống lọc nước");
			order1.setCreateDate(formatter.parse("2016/4/5"));
			order1.setOrderContent("Sản xuất 30 ống lọc nước cho bình dung tích 5l");
			order1.setPossibility(1);
			order1.setStatus(status1);
			order1.setProductName("Ống lọc nước");
			order1.setAmountOfProduct(50);
			order1.setDueDate(formatter.parse("2016/5/10"));

			Orders order2 = new Orders();
			order2.setOrderId("OrderFPT");
			order2.setCustomer(customer1);
			order2.setUserByUserId(user1);
			order2.setProjectName("Hợp đồng sản xuất bàn ghế");
			order2.setCreateDate(formatter.parse("2016/4/3"));
			order2.setOrderContent("Sản suất 5000 bộ bàn ghế bằng bạc");
			order2.setPossibility(1);
			order2.setStatus(status1);
			order2.setProductName("Bàn ghế");
			order2.setAmountOfProduct(5000);
			order2.setDueDate(formatter.parse("2016/4/30"));

			// Create LimitInventory for testing
			LimitInventory li1 = new LimitInventory();
			li1.setCreatedDate(formatter.parse("2016/4/10"));
			li1.setDateWanted(formatter.parse("2016/10/20"));
			li1.setDirectorateStatus(1);
			li1.setFactoryManagerStatus(1);
			li1.setOrders(order2);
			li1.setTimeModify(0);
			li1.setUserByCreator(user1);
			li1.setUserByDirectorateId(user2);
			li1.setUserByFactoryManagerId(user3);

			LimitInventoryDetail lid1 = new LimitInventoryDetail();
			lid1.setElement(new Element());
			lid1.setMaterial(new Material());
			lid1.setZ(230.4);
			lid1.setX(672.4);
			lid1.setY(732.3);
			lid1.setO(0);
			lid1.setQuantity(20);
			lid1.setLimitInventoryDetailId(1);

			li1.getLimitInventoryDetails().add(lid1);

			LimitInventory li2 = new LimitInventory();
			li2.setCreatedDate(formatter.parse("2016/4/7"));
			li2.setDateWanted(formatter.parse("2016/10/5"));
			li2.setDirectorateStatus(1);
			li2.setFactoryManagerStatus(1);
			li2.setOrders(order1);
			li2.setTimeModify(0);
			li2.setUserByCreator(user1);
			li2.setUserByDirectorateId(user2);
			li2.setUserByFactoryManagerId(user3);

			// Create StockRequisition for testing
			StockRequisition sr1 = new StockRequisition();
			sr1.setRequisitionId(1);
			sr1.setCreatedDate(formatter.parse("2016/4/10"));
			sr1.setDateWanted(formatter.parse("2016/10/20"));
			sr1.setDepartment("Department 1");
			sr1.setFactoryManagerStatus(0);
			sr1.setHfadStatus(0);
			sr1.setLastModifiedDate(new Date());
			sr1.setLimitInventory(li1);
			sr1.setOrders(order2);
			sr1.setUserByCreator(user1);
			sr1.setUserByLastModifiedUser(user1);

			StockRequisitionDetails srd1 = new StockRequisitionDetails();
			srd1.setElement(new Element());
			srd1.setMaterial(new Material());
			srd1.setHeight(230.4);
			srd1.setLength(672.4);
			srd1.setWidth(732.3);
			srd1.setPhi(0);
			srd1.setQuantity(20);
			srd1.setStockRequisition(sr1);
			srd1.setStockRequisitionDetailsId(1);

			sr1.getStockRequisitionDetailses().add(srd1);

			StockRequisition sr2 = new StockRequisition();
			sr2.setRequisitionId(2);
			sr2.setCreatedDate(formatter.parse("2016/4/7"));
			sr2.setDateWanted(formatter.parse("2016/10/5"));
			sr2.setDepartment("Department 2");
			sr2.setFactoryManagerStatus(0);
			sr2.setHfadStatus(0);
			sr2.setLastModifiedDate(new Date());
			sr2.setLimitInventory(li2);
			sr2.setOrders(order1);
			sr2.setUserByCreator(user1);
			sr2.setUserByLastModifiedUser(user1);

			srs.add(sr1);
			srs.add(sr2);
		} catch (ParseException pe) {
			System.err.println("Error when getting StockRequisition List");
		}

		return srs;
	}
}
