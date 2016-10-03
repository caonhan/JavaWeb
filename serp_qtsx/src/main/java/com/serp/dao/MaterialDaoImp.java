package com.serp.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.serp.model.Material;

/**
 * @author ThanhVan
 * @version 1.0 Apr 13, 2016
 */
@Repository("MaterialDao")
@Transactional
public class MaterialDaoImp implements MaterialDao {
    private static final Log log = LogFactory.getLog(MaterialDaoImp.class);
    @Autowired
    private SessionFactory session;

    /**
     * This is function add material
     * @param material
     */
    @Override
    public void addMaterial(Material material) {
        log.debug("adding material instance ");
        try {
            session.getCurrentSession().persist(material);
            log.debug("adding successful");
        } catch (RuntimeException re) {
            log.error("adding failed", re);
            throw re;
        }
    }

    /**
     * This is function delete material
     * @param material
     */
    @Override
    public void deleteMaterial(Material material) {
        log.debug("deleting material instance ");
        try {
            session.getCurrentSession().delete(material);
            log.debug("deleting successful");
        } catch (RuntimeException re) {
            log.error("deleting failed", re);
            throw re;
        }
    }

    /**
     * This is function update material
     * @param material
     */
    @Override
    public void updateMaterial(Material material) {
        log.debug("updating material instance ");
        try {
            session.getCurrentSession().update(material);
            log.debug("updating successful");
        } catch (RuntimeException re) {
            log.error("updating failed", re);
            throw re;
        }
    }

    /**
     * This is function get material
     * @param id
     * @return material
     */
    @Override
    public Material getMaterial(String id) {
        log.debug("getting material");
        try {
            Material material = (Material) session.getCurrentSession().get(Material.class, id);
            log.debug("getting successful");
            return material;
        } catch (RuntimeException re) {
            log.error("getting failed");
            throw re;
        }

    }

    /**
     * This is function get all material
     * @return list material
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Material> getAllMaterial() {
        log.debug("getting all material");
        try {
            List<Material> list = session.getCurrentSession().createCriteria("com.serp.model.Material").list();
            log.debug("getting all successful");
            return list;
        } catch (RuntimeException re) {
            log.error("getting all failed", re);
            throw re;
        }

    }

}
