package com.serp.dao;

import static org.junit.Assert.*;

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
import com.serp.model.Material;
import com.serp.model.Role;
import com.serp.model.User;
/**
 * 
 * @author Hai Dang
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoTest {
    static AbstractApplicationContext context;
    static UserDAO dao;
    @Autowired
	RoleDao roledao;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        dao = (UserDAO) context.getBean("UserDAO");
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
        dao.addUser(user);
    }

    @Test(expected = RuntimeException.class)
    public void testAddMaterialException() {
        User user = null;
        dao.addUser(user);
        assertNotNull(dao.findById("U"));
    }

    @Test
    public void testUpdateUser() {
        User user = dao.findById("U");
        user.setName("UU");
        dao.attachDirty(user);
        assertEquals("mmm", dao.findById("U").getName());
    }

    @Test(expected = RuntimeException.class)
    public void testUpdateUserException() {
        User user = null;
        dao.attachDirty(user);;
    }

    @Test(expected = RuntimeException.class)
    public void testGetUserException() {
        dao.findById(null);
    }

    @Test()
    public void testGetAllUser() {
        assertEquals(true, dao.getAllUser().size() > 0);
    }

    @Test
    public void testDeleteUser() {
        dao.delete(dao.findById("UU"));
        assertEquals(null, dao.findById("UU"));
    }

    @Test(expected = RuntimeException.class)
    public void testDeleteUserException() {
        dao.delete(null);
    }
}
