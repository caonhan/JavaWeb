package com.serp.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serp.dao.ElementDAO;
import com.serp.dao.LimitInventoryDAO;
import com.serp.dao.MaterialDao;
import com.serp.entity.ElementEntity;
import com.serp.entity.LimitInventoryDetailEntity;
import com.serp.entity.MaterialEntity;
import com.serp.model.Element;
import com.serp.model.LimitInventory;
import com.serp.model.LimitInventoryDetail;
import com.serp.model.Material;

@Service("limitInventoryService")
public class LimitInventoryServiceImpl implements LimitInventoryService {
	@Autowired
	protected LimitInventoryDAO limitInventoryDao;

	@Autowired
	protected MaterialDao materialDao;

	@Autowired
	protected ElementDAO elementDao;

	private static final Log log = LogFactory.getLog(LimitInventoryServiceImpl.class);

	@Override
	public boolean delete(Integer id) {
		log.info("delete function");
		try {
			limitInventoryDao.deleteLimitInventory(id);
			log.info("LimitInventory delete successful");
			return true;
		} catch (NullPointerException ne) {
			log.error("NullPointerException at execute id: " + id + " - Error message: " + ne.getMessage());
			System.err.println("LimitInventory delete failed");
			return false;
		} catch (Exception ex) {
			log.fatal("Error message: " + ex.getMessage());
			System.err.println("LimitInventory delete failed");
			return false;
		}

	}

	@Override
	public List<LimitInventory> findAll() {
		log.info("findAll function");

		try {
			return limitInventoryDao.getAllLimitInventories();
		} catch (NullPointerException ne) {
			log.error("NullPointerException error message: " + ne.getMessage());
		} catch (Exception ex) {
			log.fatal("Error message: " + ex.getMessage());
		}
		return null;
	}

	@Override
	public boolean saveOrUpdate(LimitInventory li) {
		log.info("saveOrUpdate function");
		try {
			
			limitInventoryDao.saveOrUpdateLimitInventory(li);
							
			return true;
		} catch (NullPointerException ne) {
			log.error("NullPointerException at execute: " + li + " - Error message: " + ne.getMessage());
			System.err.println("LimitInventory Save Or Update failed");
			return false;
		} catch (Exception ex) {
			log.fatal("Error message: " + ex.getMessage());
			System.err.println("LimitInventory Save Or Update failed");
			return false;
		}
	}

	@Override
	public LimitInventory findById(Integer id) {
		log.info("findById function");
		try {
			return limitInventoryDao.getLimitInventoryById(id);
		} catch (NullPointerException ne) {
			log.error("NullPointerException at execute id: " + id + " - Error message: " + ne.getMessage());
		} catch (Exception ex) {
			log.fatal("Error message: " + ex.getMessage());
		}
		return null;
	}

	@Override
	public boolean saveOrUpdateDetail(LimitInventoryDetail lid) {
		log.info("LimitInventoryService - saveOrUpdateDetail function");

		try {
			limitInventoryDao.saveOrUpdateLimitInventoryDetail(lid);
			log.info("save or update Limit Inventory Detail successful");
			return true;
		} catch (NullPointerException ne) {
			log.error("NullPointerException at execute: " + lid + " - Error message: " + ne.getMessage());
			System.err.println("Limit Inventory Detail save or update failed");
			return false;
		} catch (Exception e) {
			log.fatal("Error message: " + e.getMessage());
			System.err.println("Limit Inventory Detail save or update failed");
			return false;
		}
	}

	@Override
	public boolean deleteDetail(Integer id) {
		log.info("LimitInventoryService - deleteDetail function");

		try {
			limitInventoryDao.deleteLimitInventoryDetail(id);
			log.info("Limit Inventory Detail detele successful");
			return true;
		} catch (NullPointerException ne) {
			log.error("NullPointerException at execute id: " + id + " - Error message: " + ne.getMessage());
			System.err.println("Limit Inventory Detail delete failed");
			return false;
		} catch (Exception e) {
			log.fatal("Error message: " + e.getMessage());
			System.err.println("Limit Inventory Detail delete failed");
			return false;
		}
	}

	@Override
	public List<LimitInventoryDetail> findAllDetailById(Integer id) {
		log.info("findAllDetailById function");

		try {
			return limitInventoryDao.getAllDetailByLimitInventoryId(id);
		} catch (NullPointerException ne) {
			log.error("NullPointerException error message: " + ne.getMessage());
			System.err.println("findAllDetailById failed");
		} catch (Exception e) {
			log.fatal("Error message: " + e.getMessage());
			System.err.println("findAllDetailById failed");
		}
		return null;
	}

	@Override
	public List<MaterialEntity> findAllMaterialEntity() {
		log.info("findAllMaterialEntity function");

		List<MaterialEntity> mList = new ArrayList<MaterialEntity>();

		try {
			List<Material> tempList = materialDao.getAllMaterial();
			Iterator<Material> iterator = tempList.iterator();

			while (iterator.hasNext()) {
				Material material = iterator.next();
				MaterialEntity materialEntity = new MaterialEntity();

				materialEntity.setMId(material.getmaterialId());
				materialEntity.setMName(material.getmaterialName());
				materialEntity.setMPrice(material.getmaterialPrice());

				mList.add(materialEntity);
			}

			return mList;
		} catch (NullPointerException ne) {
			log.error("NullPointerException error message: " + ne.getMessage());
			System.err.println("findAllMaterialEntity failed");
		} catch (Exception ex) {
			log.fatal("Error message: " + ex.getMessage());
			System.err.println("findAllMaterialEntity failed");
		}

		return null;
	}

	@Override
	public List<ElementEntity> findAllElementEntity() {
		log.info("findAllElementEntity function");
		List<ElementEntity> eList = new ArrayList<ElementEntity>();

		try {
			List<Element> tempList = elementDao.findAll();
			Iterator<Element> iterator = tempList.iterator();

			while (iterator.hasNext()) {
				Element element = iterator.next();
				ElementEntity elementEntity = new ElementEntity();

				elementEntity.setEId(element.getEId());
				elementEntity.setEName(element.getEName());
				elementEntity.setEUnit(element.getEUnit());

				eList.add(elementEntity);
			}
			return eList;

		} catch (NullPointerException ne) {
			log.error("NullPointerException error message: " + ne.getMessage());
			System.err.println("NullPointerException - findAllElementEntity failed");
		} catch (Exception ex) {
			log.fatal("Error message: " + ex.getMessage());
			System.err.println("findAllElementEntity failed");
		}
		return null;
	}

	@Override
	public List<LimitInventoryDetailEntity> findAllDetailEntity(Integer id) {
		log.info("LimitInventoryServiceImpl - findAllDetailEntity function");
		List<LimitInventoryDetailEntity> lideList = new ArrayList<LimitInventoryDetailEntity>();

		try {
			List<LimitInventoryDetail> tempList = limitInventoryDao.getAllDetailByLimitInventoryId(id);
			Iterator<LimitInventoryDetail> iterator = tempList.iterator();

			while (iterator.hasNext()) {
				LimitInventoryDetail lid = iterator.next();
				LimitInventoryDetailEntity lide = convertModelToEntity(lid);

				lideList.add(lide);
			}

			return lideList;
		} catch (NullPointerException ne) {
			log.error("NullPointerException error message: " + ne.getMessage());
			System.err.println("NullPointerException - findAllDetailEntity failed");
		} catch (Exception ex) {
			log.fatal("Error message: " + ex.getMessage());
			System.err.println("findAllDetailEntity failed");
		}
		return null;
	}

	@Override
	public LimitInventoryDetail convertEntityToModel(LimitInventoryDetailEntity lide) {
		log.info("convertEntityToModel function");
		LimitInventoryDetail lid = new LimitInventoryDetail(); // ko dua vo try?

		try {
			lid.setLimitInventory(limitInventoryDao.getLimitInventoryById(lide.getLimitInventoryId()));
			lid.setMaterial(materialDao.getMaterial(lide.getMaterialId()));
			lid.setElement(elementDao.findById(lide.getElementId()));
			lid.setO(lide.getPhi());
			lid.setX(lide.getLength());
			lid.setY(lide.getWidth());
			lid.setZ(lide.getHeight());
			lid.setQuantity(lide.getQuantity());
			lid.setNote(lide.getNote());
			lid.toString();

			System.out.println("convertEntityToModel successful: " + lid.toString());
			log.debug("convertEntityToModel successful " + lid.toString());

			return lid;

		} catch (NullPointerException ne) {
			log.error("NullPointerException error message: " + ne.getMessage());
			System.err.println("NullPointerException - convertEntityToModel failed");
		} catch (Exception ex) {
			log.fatal("Error message: " + ex.getMessage());
			System.err.println("convertEntityToModel failed, message: " + ex.getMessage());
		}
		return null;
	}

	@Override
	public LimitInventoryDetailEntity convertModelToEntity(LimitInventoryDetail lid) {
		log.info("convertModelToEntity function");

		try {
			LimitInventoryDetailEntity lide = new LimitInventoryDetailEntity();
			
			lide.setElementId(lid.getElement().getEId());
			lide.setElementName(lid.getElement().getEName());
			lide.setElementUnit(lid.getElement().getEUnit());
			lide.setMaterialId(lid.getMaterial().getmaterialId());
			lide.setMaterialName(lid.getMaterial().getmaterialName());
			lide.setPhi(lid.getO());
			lide.setLength(lid.getX());
			lide.setWidth(lid.getY());
			lide.setHeight(lid.getZ());
			lide.setQuantity(lid.getQuantity());
			// neu Note trong thi xuat ra ""
			lide.setNote((lid.getNote() == null) ? "" : lid.getNote());
			lide.setLimitInventoryDetailsId(lid.getLimitInventoryDetailId());
			lide.setLimitInventoryId(lid.getLimitInventory().getLimitInventoryId());

			log.debug("convertModelToEntity successful");

			return lide;

		} catch (Exception ex) {
			log.error("convertModelToEntity failed");
			System.err.println("convertModelToEntity failed");
		}

		return null;
	}

}
