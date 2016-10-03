package com.serp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
public class MaterialServiceTest {

    static AbstractApplicationContext context;
    static MaterialService ser;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        ser = (MaterialService) context.getBean("MaterialService");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        context.close();
    }

    @Test
    public void testAddMaterial() {
        Material material = new Material("MM", "MMM", 15000);
        ser.addMaterial(material);
    }

    @Test(expected = RuntimeException.class)
    public void testAddMaterialException() {
        Material material = null;
        ser.addMaterial(material);
        assertNotNull(ser.getMaterial("MM"));
    }

    @Test
    public void testUpdateMaterial() {
        Material material = ser.getMaterial("Gold");
        material.setmaterialName("mmm");
        ser.updateMaterial(material);
        assertEquals("mmm", ser.getMaterial("Gold").getmaterialName());
    }

    @Test(expected = RuntimeException.class)
    public void testUpdateMaterialException() {
        Material material = null;
        ser.updateMaterial(material);
    }

    @Test(expected = RuntimeException.class)
    public void testGetMaterialException() {
        ser.getMaterial(null);
    }

    @Test()
    public void testGetAllMaterial() {
        assertEquals(true, ser.getAllMaterial().size() > 0);
    }

    @Test
    public void testDeleteMaterial() {
        ser.deleteMaterial(ser.getMaterial("MM"));
        assertEquals(null, ser.getMaterial("MM"));
    }

    @Test(expected = RuntimeException.class)
    public void testDeleteMaterialException() {
        ser.deleteMaterial(null);
    }

}
