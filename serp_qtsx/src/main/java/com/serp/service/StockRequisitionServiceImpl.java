package com.serp.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serp.dao.ElementDAO;
import com.serp.dao.MaterialDao;
import com.serp.dao.StockRequisitionDAO;
import com.serp.entity.ElementEntity;
import com.serp.entity.MaterialEntity;
import com.serp.entity.StockRequisitionDetailsEntity;
import com.serp.model.Element;
import com.serp.model.LimitInventory;
import com.serp.model.LimitInventoryDetail;
import com.serp.model.Material;
import com.serp.model.StockRequisition;
import com.serp.model.StockRequisitionDetails;

/**
 * The Class StockRequisitionServiceImpl.
 *
 * @author KhangNDD
 */
@Service("stockRequisitionService")
public class StockRequisitionServiceImpl implements StockRequisitionService {

	/** The stock requisition dao. */
	@Autowired
	protected StockRequisitionDAO stockRequisitionDao;

	/** The material dao. */
	@Autowired
	protected MaterialDao materialDao;

	/** The element dao. */
	@Autowired
	protected ElementDAO elementDAO;

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(StockRequisitionServiceImpl.class.getSimpleName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.serp.service.StockRequisitionService#saveOrUpdate(com.serp.model.
	 * StockRequisition)
	 */
	@Override
	public boolean saveOrUpdate(StockRequisition sr, List<Integer> deletedIds) {
		log.info("saveOrUpdate function");
		System.out.println("Stock id: " + sr.getRequisitionId());
		try {
			if (null == sr.getRequisitionId()) {
				stockRequisitionDao.saveStockRequisition(sr);
			} else {
				// Update new detail to database
				stockRequisitionDao.updateStockRequisition(sr);

				// Remove items in database
				Iterator<Integer> iterator = deletedIds.iterator();
				while (iterator.hasNext()) {
					Integer detailId = iterator.next();

					// If detail id is valid and still exist in the database,
					// then delete it
					if (-1 != detailId && null != stockRequisitionDao.getDetailById(detailId)) {
						deleteDetails(detailId);
					}
				}
			}

			return true;
		} catch (Exception ex) {
			log.fatal("Error message: " + ex.getMessage());
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.serp.service.StockRequisitionService#findById(java.lang.Integer)
	 */
	@Override
	public StockRequisition findById(Integer id) {
		log.info("findById function");
		try {
			StockRequisition instance = stockRequisitionDao.getStockRequisitionById(id);
			return instance;
		} catch (Exception ex) {
			log.fatal("Error message: " + ex.getMessage());
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.serp.service.StockRequisitionService#delete(java.lang.Integer)
	 */
	@Override
	public boolean delete(Integer id) {
		log.info("delete function");
		try {
			stockRequisitionDao.deleteStockRequisition(id);
			log.info("Stock Requisition delete successful");
			return true;
		} catch (Exception ex) {
			log.fatal("Error message: " + ex.getMessage());
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.serp.service.StockRequisitionService#findAll()
	 */
	@Override
	public List<StockRequisition> findAll() {
		log.info("findAll function");
		try {
			return stockRequisitionDao.getAllStockRequisitions();
		} catch (Exception ex) {
			log.fatal("Error message: " + ex.getMessage());
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.serp.service.StockRequisitionService#saveOrUpdateDetails(com.serp.
	 * model.StockRequisitionDetails)
	 */
	@Override
	public boolean saveOrUpdateDetails(StockRequisitionDetails srd) {
		log.info("StockRequisitionService - saveOrUpdateDetails function");

		try {
			stockRequisitionDao.saveOrUpdateStockRequisitionDetails(srd);
			log.info("Save or Update Stock Requisition Details successful");
			return true;
		} catch (Exception ex) {
			log.fatal("Error message: " + ex.getMessage());
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.serp.service.StockRequisitionService#deleteDetails(java.lang.Integer)
	 */
	@Override
	public boolean deleteDetails(Integer id) {
		log.info("StockRequisitionService - deleteDetails function");

		try {
			stockRequisitionDao.deleteStockRequisitionDetails(id);
			log.info("Stock Requisition Details delete successful");
			return true;
		} catch (Exception ex) {
			log.fatal("Error message: " + ex.getMessage());
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.serp.service.StockRequisitionService#findAllDetailsById(java.lang.
	 * Integer)
	 */
	@Override
	public List<StockRequisitionDetails> findAllDetailsById(Integer id) {
		log.info("findAllDetailsById function");
		try {
			return stockRequisitionDao.getAllDetailsByRequisitionId(id);
		} catch (Exception ex) {
			log.fatal("Error message: " + ex.getMessage());
			throw ex;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.serp.service.StockRequisitionService#findAllMaterialEntity()
	 */
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
		} catch (Exception ex) {
			log.fatal("Error message: " + ex.getMessage());
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.serp.service.StockRequisitionService#convertEntityToModel(com.serp.
	 * entity.StockRequisitionDetailsEntity)
	 */
	public StockRequisitionDetails convertEntityToModel(StockRequisitionDetailsEntity srde) {
		log.info("convertEntityToModel function");
		StockRequisitionDetails srd = new StockRequisitionDetails();

		try {
			srd.setStockRequisition(stockRequisitionDao.getStockRequisitionById(srde.getStockRequisitionId()));
			srd.setMaterial(materialDao.getMaterial(srde.getMaterialId()));
			srd.setElement(elementDAO.findById(srde.getElementId()));
			srd.setPhi(srde.getPhi());
			srd.setLength(srde.getLength());
			srd.setWidth(srde.getWidth());
			srd.setHeight(srde.getHeight());
			srd.setQuantity(srde.getQuantity());
			srd.setNote(srde.getNote());

			log.debug("convertEntityToModel successful");
			return srd;
		} catch (Exception ex) {
			log.fatal("Error message: " + ex.getMessage());
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.serp.service.StockRequisitionService#findAllElementEntity()
	 */
	@Override
	public List<ElementEntity> findAllElementEntity() {
		log.info("findAllElementEntity function");

		List<ElementEntity> eList = new ArrayList<ElementEntity>();
		try {
			List<Element> tempList = elementDAO.findAll();
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
		} catch (Exception ex) {
			log.fatal("Error message: " + ex.getMessage());
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.serp.service.StockRequisitionService#convertModelToEntity(com.serp.
	 * model.StockRequisitionDetails)
	 */
	@Override
	public StockRequisitionDetailsEntity convertModelToEntity(StockRequisitionDetails srd) {
		log.info("convertModelToEntity function");
		try {
			StockRequisitionDetailsEntity srde = new StockRequisitionDetailsEntity();
			srde.setElementId(srd.getElement().getEId());
			srde.setElementName(srd.getElement().getEName());
			srde.setElementUnit(srd.getElement().getEUnit());
			srde.setHeight(srd.getHeight());
			srde.setLength(srd.getLength());
			srde.setMaterialId(srd.getMaterial().getmaterialId());
			srde.setMaterialName(srd.getMaterial().getmaterialName());
			srde.setNote(srd.getNote());
			srde.setPhi(srd.getPhi());
			srde.setQuantity(srd.getQuantity());
			srde.setStockRequisitionDetailsId(srd.getStockRequisitionDetailsId());
			srde.setStockRequisitionId(srd.getStockRequisition().getRequisitionId());
			srde.setWidth(srd.getWidth());

			log.debug("convertModelToEntity successful");
			return srde;
		} catch (Exception ex) {
			log.error("convertModelToEntity failed");
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.serp.service.StockRequisitionService#findAllDetailsEntity(java.lang.
	 * Integer)
	 */
	@Override
	public List<StockRequisitionDetailsEntity> findAllDetailsEntity(Integer id) {
		log.info("StockRequisitionServiceImpl - findAllDetailsEntity function");
		List<StockRequisitionDetailsEntity> srdeList = new ArrayList<StockRequisitionDetailsEntity>();

		try {
			List<StockRequisitionDetails> tempList = stockRequisitionDao.getAllDetailsByRequisitionId(id);
			Iterator<StockRequisitionDetails> iterator = tempList.iterator();

			while (iterator.hasNext()) {
				StockRequisitionDetails srd = iterator.next();
				StockRequisitionDetailsEntity srde = convertModelToEntity(srd);
				srdeList.add(srde);
			}

			return srdeList;
		} catch (Exception ex) {
			log.fatal("Error message: " + ex.getMessage());
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.serp.service.StockRequisitionService#getDataFromLimitInventory(com.
	 * serp.model.LimitInventory)
	 */
	@Override
	public StockRequisition getDataFromLimitInventory(LimitInventory limitInventory) {
		log.info("StockRequisitionServiceImpl - getDataFromLimitInventory function");

		try {
			StockRequisition sr = new StockRequisition();

			sr.setDateWanted(limitInventory.getDateWanted());
			sr.setLimitInventory(limitInventory);
			sr.setOrders(limitInventory.getOrders());

			// Get all details from Limit Inventory
			Iterator<LimitInventoryDetail> iterator = limitInventory.getLimitInventoryDetails().iterator();
			while (iterator.hasNext()) {
				LimitInventoryDetail lid = iterator.next();

				StockRequisitionDetails srd = new StockRequisitionDetails();
				srd.setElement(lid.getElement());
				srd.setPhi(lid.getO());
				srd.setLength(lid.getX());
				srd.setWidth(lid.getY());
				srd.setHeight(lid.getZ());
				srd.setMaterial(lid.getMaterial());
				srd.setNote(lid.getNote());
				srd.setQuantity(lid.getQuantity());

				sr.getStockRequisitionDetailses().add(srd);
			}

			return sr;
		} catch (Exception ex) {
			log.fatal("getDataFromLimitInventory - Error message: " + ex.getMessage());
			return null;
		}
	}
}
