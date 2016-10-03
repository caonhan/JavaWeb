package com.serp.dao;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.serp.configuration.AppConfig;
import com.serp.model.Material;
/**
 * 
 * @author Thanh Van
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MaterialDaoTest {
    static AbstractApplicationContext context;
    static MaterialDao dao;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        dao = (MaterialDao) context.getBean("MaterialDao");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        context.close();
    }

    @Test
    public void testAddMaterial() {
        Material material = new Material("MM", "MMM", 15000);
        dao.addMaterial(material);
    }

    @Test(expected = RuntimeException.class)
    public void testAddMaterialException() {
        Material material = null;
        dao.addMaterial(material);
        assertNotNull(dao.getMaterial("MM"));
    }

    @Test
    public void testUpdateMaterial() {
        Material material = dao.getMaterial("Gold");
        material.setmaterialName("mmm");
        dao.updateMaterial(material);
        assertEquals("mmm", dao.getMaterial("Gold").getmaterialName());
    }

    @Test(expected = RuntimeException.class)
    public void testUpdateMaterialException() {
        Material material = null;
        dao.updateMaterial(material);
    }

    @Test(expected = RuntimeException.class)
    public void testGetMaterialException() {
        dao.getMaterial(null);
    }

    @Test()
    public void testGetAllMaterial() {
        assertEquals(true, dao.getAllMaterial().size() > 0);
    }

    // @Test(expected = RuntimeException.class)
    // public void testGetAllMaterialException() {
    // context.close();
    // dao.getAllMaterial();
    // }

    @Test
    public void testDeleteMaterial() {
        dao.deleteMaterial(dao.getMaterial("MM"));
        assertEquals(null, dao.getMaterial("MM"));
    }

    @Test(expected = RuntimeException.class)
    public void testDeleteMaterialException() {
        dao.deleteMaterial(null);
    }
}
