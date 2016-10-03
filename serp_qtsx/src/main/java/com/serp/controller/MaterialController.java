package com.serp.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.serp.model.Material;
import com.serp.service.MaterialService;

/**
 * 
 * @author Thanh Van
 *
 */
@Controller
public class MaterialController {
    private static final Logger logger = Logger.getLogger(QuotationController.class);

    @Autowired
    MaterialService materialService;

    @RequestMapping(value = { "/material" }, produces = "application/json", method = RequestMethod.GET)
    public ModelAndView handleRequest(HttpServletResponse response, HttpServletRequest request) {
        try {
            request.setAttribute("materialList", materialService.getAllMaterial());
            response.setContentType("text/html");
            return new ModelAndView("material/material");
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
    }

    @RequestMapping(value = { "/addmaterial" }, method = RequestMethod.POST)
    public void addMaterialRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            String materialId = request.getParameter("materialId");
            String materialName = request.getParameter("materialName");
            double materialPrice = Double.parseDouble(request.getParameter("materialPrice"));
            Material material = new Material(materialId, materialName, materialPrice);

            materialService.addMaterial(material);
            logger.debug("material:" + materialId + " name:" + materialName + " price:" + materialPrice + " object"
                    + material);
            response.sendRedirect("material");
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @RequestMapping(value = { "/updatematerial" }, method = RequestMethod.GET)
    public ModelAndView updateMaterialRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            logger.debug("Update material");
            String materialId = request.getParameter("materialId");

            request.setAttribute("material", materialService.getMaterial(materialId));
            return new ModelAndView("material/updateMaterial");
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
    }

    @RequestMapping(value = { "/save" }, method = RequestMethod.GET)
    public void saveMaterial(HttpServletRequest request, HttpServletResponse response) {
        try {
            String materialId = request.getParameter("materialId");
            String materialName = request.getParameter("materialName");
            double materialPrice = Double.parseDouble(request.getParameter("materialPrice"));
            Material material = new Material(materialId, materialName, materialPrice);
            logger.debug("save material:" + materialId + " name:" + materialName + " price:" + materialPrice + " object"
                    + material);
            materialService.updateMaterial(material);

            response.sendRedirect("material");
        } catch (IOException e) {
            logger.error(e);
        }
    }

    @RequestMapping(value = { "/deletematerial" }, method = RequestMethod.GET)
    public void deleteMaterialRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            String materialId = request.getParameter("materialId");
            Material material = materialService.getMaterial(materialId);
            logger.debug("material:" + materialId + "object:" + material);
            materialService.deleteMaterial(material);
            response.sendRedirect("material");
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
