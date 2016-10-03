package com.serp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.serp.configuration.AppConfig;
import com.serp.dao.RoleDao;
import com.serp.model.Material;
import com.serp.model.Role;
import com.serp.model.User;

/**
 * 
 * @author Hai Dang
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {

    static AbstractApplicationContext context;
    static UserService ser;
    @Autowired
	RoleDao roledao;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        ser = (UserService) context.getBean("UserService");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        context.close();
    }

    @Test
    public void testAddUser() throws ParseException {
    	String userId = "txuong11";
        String password = "txuong11";
        String name = "Truong xuong 11";
        SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");
        Date birthdate = in.parse("1994-12-19");
        String phonenumber = "0902706561";
        String email = "tx11@mail.com";
        String address = "add tx11";
        String department = "dep tx11";           
        String r = "TX";
        Role role = roledao.findById(r);
        Integer status = Integer.parseInt("11");
        
        User user = new User(userId, password, name, birthdate, phonenumber, email, address, department, role, status);
        ser.addUser(user);
    }

    @Test(expected = RuntimeException.class)
    public void testAddUserException() {
        User user = null;
        ser.addUser(user);
        assertNotNull(ser.findByUserID("U"));
    }

    @Test
    public void testUpdateUser() {
    	User user = ser.findByUserID("txuong2");
        user.setName("UU");
        ser.updateUser(user);
        assertEquals("UU", ser.findByUserID("txuong2").getName());
    }

    @Test(expected = RuntimeException.class)
    public void testUpdateUserException() {
        User user = null;
        ser.updateUser(user);
    }

    @Test(expected = RuntimeException.class)
    public void testGetUserException() {
        ser.findByUserID(null);
    }

    @Test()
    public void testGetAllUser() {
        assertEquals(true, ser.getAllUser().size() > 0);
    }

    @Test
    public void testDeleteUser() {
        ser.deleteUser(ser.findByUserID("txuong1"));
        assertEquals(null, ser.findByUserID("txuong1"));
    }

    @Test(expected = RuntimeException.class)
    public void testDeleteUserException() {
        ser.deleteUser(null);
    }

}
