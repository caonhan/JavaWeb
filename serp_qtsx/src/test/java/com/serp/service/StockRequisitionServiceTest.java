package com.serp.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.serp.dao.ElementDAO;
import com.serp.dao.MaterialDao;
import com.serp.dao.StockRequisitionDAO;
import com.serp.entity.ElementEntity;
import com.serp.entity.MaterialEntity;
import com.serp.entity.StockRequisitionDetailsEntity;
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
 * The Class StockRequisitionServiceTest.
 */
public class StockRequisitionServiceTest {
	
	/** The formatter. */
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

	/** The stock requisition dao. */
	@Mock
	StockRequisitionDAO stockRequisitionDao;

	/** The material dao. */
	@Mock
	MaterialDao materialDao;

	/** The element dao. */
	@Mock
	ElementDAO elementDao;

	/** The stock requisition service. */
	@InjectMocks
	StockRequisitionServiceImpl stockRequisitionService;

	/** The srs. */
	@Spy
	List<StockRequisition> srs = new ArrayList<StockRequisition>();

	/** The materials. */
	@Spy
	List<Material> materials = new ArrayList<Material>();

	/** The elements. */
	@Spy
	List<Element> elements = new ArrayList<Element>();

	/** The material entities. */
	@Spy
	List<MaterialEntity> materialEntities = new ArrayList<MaterialEntity>();

	/** The element entities. */
	@Spy
	List<ElementEntity> elementEntities = new ArrayList<ElementEntity>();

	/** The srdes. */
	@Spy
	List<StockRequisitionDetailsEntity> srdes = new ArrayList<StockRequisitionDetailsEntity>();

	/**
	 * Before class.
	 */
	@BeforeClass
	public void beforeClass() {
		MockitoAnnotations.initMocks(this);
		srs = getStockRequisitionList();
		elements = getElements();
		materials = getMaterials();
		elementEntities = getElementEntity();
		materialEntities = getMaterialEntity();
		srdes = getAllDetailEntity();
	}

	/**
	 * Test save stock requisition.
	 */
	@Test
	public void testSaveStockRequisition() {
		StockRequisition sr3 = new StockRequisition();
		try {
			sr3.setCreatedDate(formatter.parse("2016/4/7"));
			sr3.setDateWanted(formatter.parse("2016/10/5"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		sr3.setDepartment("Department 3");
		sr3.setFactoryManagerStatus(0);
		sr3.setHfadStatus(0);
		sr3.setLastModifiedDate(new Date());
		sr3.setLimitInventory(srs.get(0).getLimitInventory());
		sr3.setOrders(srs.get(0).getOrders());
		sr3.setUserByCreator(srs.get(0).getUserByCreator());
		sr3.setUserByLastModifiedUser(srs.get(0).getUserByLastModifiedUser());

		doNothing().when(stockRequisitionDao).saveStockRequisition(any(StockRequisition.class));
		stockRequisitionService.saveOrUpdate(sr3, new ArrayList<Integer>());
		verify(stockRequisitionDao, atLeastOnce()).saveStockRequisition(any(StockRequisition.class));
	}

	/**
	 * Test update stock requisition.
	 */
	@Test
	public void testUpdateStockRequisition() {
		StockRequisition stockRequisition = srs.get(0);
		StockRequisitionDetails details = srs.get(0).getStockRequisitionDetailses().iterator().next();
		doNothing().when(stockRequisitionDao).updateStockRequisition(any(StockRequisition.class));
		doNothing().when(stockRequisitionDao).deleteStockRequisition(anyInt());
		when(stockRequisitionDao.getDetailById(anyInt())).thenReturn(details);

		List<Integer> deleteIds = new ArrayList<Integer>();
		deleteIds.add(1);
		stockRequisitionService.saveOrUpdate(stockRequisition, deleteIds);
		verify(stockRequisitionDao, atLeastOnce()).updateStockRequisition(any(StockRequisition.class));
	}

	/**
	 * Test save or update stock requisition null exception.
	 */
	@Test(expectedExceptions = NullPointerException.class)
	public void testSaveOrUpdateStockRequisitionNullException() {
		doThrow(new NullPointerException()).doNothing().when(stockRequisitionDao)
				.updateStockRequisition(any(StockRequisition.class));
		
		List<Integer> deleteIds = new ArrayList<Integer>();
		deleteIds.add(1);
		
		Assert.assertFalse(stockRequisitionService.saveOrUpdate(any(StockRequisition.class), deleteIds));
	}

	/**
	 * Test find by id.
	 */
	@Test
	public void testFindById() {
		StockRequisition sRequisition = srs.get(0);
		when(stockRequisitionDao.getStockRequisitionById(anyInt())).thenReturn(sRequisition);
		Assert.assertEquals(stockRequisitionService.findById(sRequisition.getRequisitionId()), sRequisition);
	}

	/**
	 * Test find unexisting id.
	 */
	@Test
	public void testFindUnexistingId() {
		when(stockRequisitionDao.getStockRequisitionById(anyInt())).thenThrow(new NullPointerException());
		Assert.assertNull(stockRequisitionService.findById(srs.size() + 999999999));
	}

	/**
	 * Test find all stock requisitions.
	 */
	@Test
	public void testFindAllStockRequisitions() {
		when(stockRequisitionDao.getAllStockRequisitions()).thenReturn(srs);
		Assert.assertEquals(stockRequisitionService.findAll(), srs);
		verify(stockRequisitionDao, atLeastOnce()).getAllStockRequisitions();
	}

	/**
	 * Test delete stock requisition by id.
	 */
	@Test
	public void testDeleteStockRequisitionById() {
		doNothing().when(stockRequisitionDao).deleteStockRequisition(anyInt());
		stockRequisitionService.delete(anyInt());
		verify(stockRequisitionDao, atLeastOnce()).deleteStockRequisition(anyInt());
	}

	/**
	 * Test delete stock requisition by id not exist.
	 */
	@Test
	public void testDeleteStockRequisitionByIdNotExist() {
		doThrow(NullPointerException.class).when(stockRequisitionDao).deleteStockRequisition(anyInt());
		Assert.assertFalse(stockRequisitionService.delete(3));
	}

	/**
	 * Test save or update details.
	 */
	@Test
	public void testSaveOrUpdateDetails() {
		StockRequisitionDetails stockRequisitionDetails = srs.get(0).getStockRequisitionDetailses().iterator().next();

		doNothing().when(stockRequisitionDao).saveOrUpdateStockRequisitionDetails(any(StockRequisitionDetails.class));
		stockRequisitionService.saveOrUpdateDetails(stockRequisitionDetails);
		verify(stockRequisitionDao, atLeastOnce())
				.saveOrUpdateStockRequisitionDetails(any(StockRequisitionDetails.class));
	}

	/**
	 * Test save or update null details.
	 */
	@Test
	public void testSaveOrUpdateNullDetails() {
		doThrow(new NullPointerException()).doNothing().when(stockRequisitionDao)
				.saveOrUpdateStockRequisitionDetails(any(StockRequisitionDetails.class));
		Assert.assertFalse(stockRequisitionService.saveOrUpdateDetails(any(StockRequisitionDetails.class)));
	}

	/**
	 * Test delete stock requisition details by id.
	 */
	@Test
	public void testDeleteStockRequisitionDetailsById() {
		doNothing().when(stockRequisitionDao).deleteStockRequisitionDetails(anyInt());
		stockRequisitionService.deleteDetails(anyInt());
		verify(stockRequisitionDao, atLeastOnce()).deleteStockRequisitionDetails(anyInt());
	}

	/**
	 * Test delete stock requisition details by id not exist.
	 */
	@Test
	public void testDeleteStockRequisitionDetailsByIdNotExist() {
		doThrow(NullPointerException.class).when(stockRequisitionDao).deleteStockRequisitionDetails(anyInt());
		Assert.assertFalse(stockRequisitionService.deleteDetails(24));
	}

	/**
	 * Test find all details by id.
	 */
	@Test
	public void testFindAllDetailsById() {
		List<StockRequisitionDetails> srds = new ArrayList<StockRequisitionDetails>(
				srs.get(0).getStockRequisitionDetailses());

		when(stockRequisitionDao.getAllDetailsByRequisitionId(0)).thenReturn(srds);
		Assert.assertEquals(srds, stockRequisitionService.findAllDetailsById(0));
	}

	/**
	 * Test find all details by id not exist.
	 */
	@Test(expectedExceptions = Exception.class)
	public void testFindAllDetailsByIdNotExist() {
		when(stockRequisitionDao.getAllDetailsByRequisitionId(anyInt())).thenThrow(new NullPointerException());
		Assert.assertNull(stockRequisitionService.findAllDetailsById(anyInt()));
	}

	/**
	 * Testfind all material entity.
	 */
	@Test
	public void testfindAllMaterialEntity() {
		when(materialDao.getAllMaterial()).thenReturn(materials);
		Assert.assertEquals(materialEntities.get(0), stockRequisitionService.findAllMaterialEntity().get(0));
	}

	/**
	 * Testfind all material entity null.
	 */
	@Test
	public void testfindAllMaterialEntityNull() {
		when(materialDao.getAllMaterial()).thenReturn(null);
		Assert.assertNull(stockRequisitionService.findAllMaterialEntity());
	}

	/**
	 * Test find all element entity.
	 */
	@Test
	public void testFindAllElementEntity() {
		when(elementDao.findAll()).thenReturn(elements);
		Assert.assertEquals(elementEntities.get(0), stockRequisitionService.findAllElementEntity().get(0));
	}

	/**
	 * Testfind all element entity null.
	 */
	@Test
	public void testfindAllElementEntityNull() {
		when(elementDao.findAll()).thenReturn(null);
		Assert.assertNull(stockRequisitionService.findAllElementEntity());
	}

	/**
	 * Test get data from limit inventory.
	 */
	@Test
	public void testGetDataFromLimitInventory() {
		LimitInventory li = srs.get(0).getLimitInventory();
		StockRequisition sr = stockRequisitionService.getDataFromLimitInventory(li);

		Assert.assertEquals(li.getDateWanted(), sr.getDateWanted());
		Assert.assertEquals(li.getOrders(), sr.getOrders());
		Assert.assertEquals(li, sr.getLimitInventory());

		LimitInventoryDetail expected = li.getLimitInventoryDetails().iterator().next();
		StockRequisitionDetails actual = sr.getStockRequisitionDetailses().iterator().next();

		Assert.assertEquals(expected.getNote(), actual.getNote());
		Assert.assertEquals(expected.getO(), actual.getPhi(), 0.0f);
		Assert.assertEquals(expected.getX(), actual.getLength(), 0.0f);
		Assert.assertEquals(expected.getY(), actual.getWidth(), 0.0f);
		Assert.assertEquals(expected.getZ(), actual.getHeight(), 0.0f);
		Assert.assertEquals(expected.getQuantity(), actual.getQuantity());
	}

	/**
	 * Test get data from null limit inventory.
	 */
	@Test
	public void testGetDataFromNullLimitInventory() {
		Assert.assertNull(stockRequisitionService.getDataFromLimitInventory(null));
	}

	/**
	 * Test find all detail entity.
	 */
	@Test
	public void testFindAllDetailEntity() {
		List<StockRequisitionDetails> srds = new ArrayList<StockRequisitionDetails>(
				srs.get(0).getStockRequisitionDetailses());
		when(stockRequisitionDao.getAllDetailsByRequisitionId(anyInt())).thenReturn(srds);

		StockRequisitionDetailsEntity actual = stockRequisitionService.findAllDetailsEntity(anyInt()).get(0);

		Assert.assertEquals(srds.get(0).getHeight(), actual.getHeight(), 0.0f);
	}

	/**
	 * Test find all detail entity with fake id.
	 */
	@Test
	public void testFindAllDetailEntityWithFakeId() {
		when(stockRequisitionDao.getAllDetailsByRequisitionId(anyInt())).thenReturn(null);
		Assert.assertNull(stockRequisitionService.findAllDetailsEntity(23));
	}

	/**
	 * Test convert model to entity with null model.
	 */
	@Test
	public void testConvertModelToEntityWithNullModel() {
		Assert.assertNull(stockRequisitionService.convertModelToEntity(null));
	}

	/**
	 * Test convert entity to model.
	 */
	@Test
	public void testConvertEntityToModel() {
		Material material = materials.get(0);
		Element element = elements.get(0);
		StockRequisition stockRequisition = srs.get(0);

		when(materialDao.getMaterial(anyString())).thenReturn(material);
		when(elementDao.findById(anyString())).thenReturn(element);
		when(stockRequisitionDao.getStockRequisitionById(anyInt())).thenReturn(stockRequisition);

		StockRequisitionDetailsEntity entity = srdes.get(0);

		@SuppressWarnings("unused")
		StockRequisitionDetails actual = stockRequisitionService.convertEntityToModel(entity);
		verify(stockRequisitionDao, atLeastOnce()).getStockRequisitionById(anyInt());
		verify(materialDao, atLeastOnce()).getMaterial(anyString());
		verify(elementDao, atLeastOnce()).findById(anyString());
	}

	/**
	 * Test convert entity to model with null entity.
	 */
	@Test
	public void testConvertEntityToModelWithNullEntity() {
		Assert.assertNull(stockRequisitionService.convertEntityToModel(null));
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

	/**
	 * Gets the materials.
	 *
	 * @return the materials
	 */
	public List<Material> getMaterials() {
		Material m1 = new Material();
		m1.setmaterialId("Gold");
		m1.setmaterialName("Vang");
		m1.setmaterialPrice(1000000);
		materials.add(m1);

		Material m2 = new Material();
		m2.setmaterialId("Bronze");
		m2.setmaterialName("Dong");
		m2.setmaterialPrice(15000);
		materials.add(m2);

		return materials;
	}

	/**
	 * Gets the elements.
	 *
	 * @return the elements
	 */
	public List<Element> getElements() {
		Element e1 = new Element();
		e1.setEId("BB-01");
		e1.setEName("Ban bac");
		e1.setEUnit("Piece");
		elements.add(e1);

		Element e2 = new Element();
		e2.setEId("GB-01");
		e2.setEName("Ghe bac");
		e2.setEUnit("Piece");
		elements.add(e2);

		return elements;
	}

	/**
	 * Gets the material entity.
	 *
	 * @return the material entity
	 */
	public List<MaterialEntity> getMaterialEntity() {
		MaterialEntity m1 = new MaterialEntity();
		m1.setMId("Gold");
		m1.setMName("Vang");
		m1.setMPrice(1000000);
		materialEntities.add(m1);

		MaterialEntity m2 = new MaterialEntity();
		m2.setMId("Bronze");
		m2.setMName("Dong");
		m2.setMPrice(15000);
		materialEntities.add(m2);

		return materialEntities;
	}

	/**
	 * Gets the element entity.
	 *
	 * @return the element entity
	 */
	public List<ElementEntity> getElementEntity() {
		ElementEntity e1 = new ElementEntity();
		e1.setEId("BB-01");
		e1.setEName("Ban bac");
		e1.setEUnit("Piece");
		elementEntities.add(e1);

		ElementEntity e2 = new ElementEntity();
		e2.setEId("GB-01");
		e2.setEName("Ghe bac");
		e2.setEUnit("Piece");
		elementEntities.add(e2);

		return elementEntities;
	}

	/**
	 * Gets the all detail entity.
	 *
	 * @return the all detail entity
	 */
	public List<StockRequisitionDetailsEntity> getAllDetailEntity() {
		StockRequisitionDetailsEntity srde1 = new StockRequisitionDetailsEntity();
		srde1.setElementId("BB-01");
		srde1.setElementName("Ban bac");
		srde1.setElementUnit("Piece");
		srde1.setMaterialId("Gold");
		srde1.setMaterialName("Vang");
		srde1.setHeight(230.4);
		srde1.setLength(672.4);
		srde1.setWidth(732.3);
		srde1.setPhi(0);
		srde1.setQuantity(20);
		srde1.setStockRequisitionId(1);
		srde1.setStockRequisitionDetailsId(1);

		srdes.add(srde1);
		return srdes;
	}
}
