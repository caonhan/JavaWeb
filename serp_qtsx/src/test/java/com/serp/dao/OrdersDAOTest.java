package com.serp.dao;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Random;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.serp.configuration.AppConfig;
import com.serp.model.Orders;
import com.serp.model.Role;

public class OrdersDAOTest {

	static AbstractApplicationContext context;
	static OrdersDAO orderDao;
	static CustomerHome cusDao;
	static StatusDAO sttDao;
	static UserDAO userDao;
    static Orders or = new Orders();
    static String tmp = generateID();
	
	public static String generateID()
	{
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		return sb.toString();
	}
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		orderDao= (OrdersDAO) context.getBean("ordersDAO");
		or.setOrderId(tmp);
		or.setPossibility(1);		
		or.setProjectName("XXXXX");
		or.setCreateDate(new Date());
		or.setOrderContent("XXXX");
		or.setProductName("XXXX");
		or.setAmountOfProduct(12);
		or.setDueDate(new Date());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}

	@Test
	public void testGetAllOrder() {
		assertEquals(true, orderDao.getAllOrder().size()>=0);
	}
	
    @Test(expected=Exception.class)
    public void ztestGetAllOrderException() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
        Class<?> c = orderDao.getClass();
        Field field = c.getDeclaredField("sessionFactory");
        field.set(orderDao, null);
        orderDao.getAllOrder();
    }
	
	@Test
	public void testPersist() {
		orderDao.persist(or);
        assertNotNull(orderDao.findById(tmp));
	}
	
	@Test(expected=RuntimeException.class)
	public void testPersistException() {
		Orders or2 = null;
		orderDao.persist(or2);
		assertNotNull(orderDao.findById(or2.getOrderId()));
	}
	
	public void testAttachDirty() {
		
		or.setOrderContent("Sản suất 5000 bộ bàn ghế bằng bạc");
		or.setJudgingContent("Judging Content");
		or.setApprovalContent("Approval Content");
		orderDao.attachDirty(or);
		assertNull(orderDao.findById(tmp));
	
	}
	
	@Test(expected= RuntimeException.class)
	public void testAttachDirtyException() {
		Orders or2 = null;
		orderDao.attachDirty(or2);
	}
	
	@Test(expected=RuntimeException.class)
	public void testDeleteException() {
		orderDao.delete(orderDao.findById("007"));
		//assertEquals(dao.findById("GD"), null);
	}
	
	@Test
	public void testDeleteAAAA() {
		orderDao.delete(or);
		assertEquals(orderDao.findById(tmp), null);
	}

	@Test
	public void testFindByIdAA() {
		assertNotNull(orderDao.findById("OrderFPT"));
		assertNull(orderDao.findById("XxxxxX"));
	}
	
	@Test(expected=RuntimeException.class)
	public void testFindByIdException() {
		assertNull(orderDao.findById(null));
	}

}
