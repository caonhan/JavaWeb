SET FOREIGN_KEY_CHECKS = 0; 
SET @tables = NULL;
SELECT GROUP_CONCAT(table_schema, '.', table_name) INTO @tables
  FROM information_schema.tables 
  WHERE table_schema = 'serp'; -- specify DB name here.

SET @tables = CONCAT('DROP TABLE ', @tables);
PREPARE stmt FROM @tables;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;
SET FOREIGN_KEY_CHECKS = 1;

use serp;

-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 27, 2016 at 11:51 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `serp`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(200) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `assignee` varchar(50) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `mobile_phone` varchar(20) DEFAULT NULL,
  `fax` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customer_id`, `company_name`, `address`, `assignee`, `telephone`, `mobile_phone`, `fax`, `email`, `website`, `created_by`, `created_date`, `modified_by`, `description`) VALUES
(1, 'FPT Software', 'HTP District 9, HCM', 'Nguyễn Minh Trung', '083876543', '0987654321', '083876543', 'trungnm@fsoft.com', 'fpt.com.vn', 'admin', '2016-04-03', NULL, NULL),
(2, 'Đại học KHTN TPHCM', '227 Nguyễn Văn Cừ', 'Nguyễn Hoàng Vương', '083876543', '0973546238', '083876543', 'info@hcmus.edu.vn', 'hcmus.edu.vn', 'admin', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `element`
--

CREATE TABLE IF NOT EXISTS `element` (
  `e_id` varchar(50) NOT NULL,
  `e_name` varchar(200) NOT NULL,
  `e_unit` varchar(50) NOT NULL,
  PRIMARY KEY (`e_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `estimate`
--

CREATE TABLE IF NOT EXISTS `estimate` (
  `es_id` int(11) NOT NULL AUTO_INCREMENT,
  `es_creator_id` varchar(50) NOT NULL,
  `es_order_id` varchar(50) NOT NULL,
  `es_published_date` date NOT NULL,
  `es_status_id` int(11) NOT NULL,
  `es_approver_id` varchar(50) DEFAULT NULL,
  `es_approve_content` text,
  PRIMARY KEY (`es_id`),
  KEY `es_approver_id` (`es_approver_id`),
  KEY `es_creator_id` (`es_creator_id`),
  KEY `es_order_id` (`es_order_id`),
  KEY `es_status_id` (`es_status_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=38 ;

-- --------------------------------------------------------

--
-- Table structure for table `estimate_detail`
--

CREATE TABLE IF NOT EXISTS `estimate_detail` (
  `ed_id` int(11) NOT NULL AUTO_INCREMENT,
  `ed_estimate_id` int(11) NOT NULL,
  `ed_element_id` varchar(50) NOT NULL,
  `ed_material_id` varchar(50) NOT NULL,
  `ed_quantity` int(11) NOT NULL,
  `ed_material_weight` double NOT NULL,
  `ed_phi` double NOT NULL,
  `ed_x` double NOT NULL,
  `ed_y` double NOT NULL,
  `ed_z` double NOT NULL,
  `ed_material_cost` int(11) NOT NULL,
  `ed_labor_cost` double NOT NULL,
  `ed_equipment_cost` double NOT NULL,
  `ed_tool_cost` double NOT NULL,
  `ed_external_cost` double NOT NULL,
  `ed_price` double NOT NULL,
  `ed_total_cost` double NOT NULL,
  PRIMARY KEY (`ed_id`),
  KEY `ed_estimate_id` (`ed_estimate_id`),
  KEY `ed_element_id` (`ed_element_id`),
  KEY `ed_material_id` (`ed_material_id`),
  KEY `ed_material_id_2` (`ed_material_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=24 ;

-- --------------------------------------------------------

--
-- Table structure for table `function`
--

CREATE TABLE IF NOT EXISTS `function` (
  `function_id` varchar(50) NOT NULL,
  `function_name` varchar(100) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`function_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `function`
--

INSERT INTO `function` (`function_id`, `function_name`, `description`) VALUES
('FT1', 'Tạo đơn hàng', NULL),
('FT2', 'Xác nhận đơn hàng', NULL),
('FT3', 'Tạo bảng dự toán', NULL),
('FT4', 'Xác nhận bảng dự toán', NULL),
('FT5', 'Tạo phiếu yêu cầu mua hàng', NULL),
('FT6', 'Duyệt phiếu yêu cầu mua hàng', NULL),
('FT7', 'Xóa phiếu yêu cầu mua hàng', NULL),
('FT8', 'Kiểm tra Công nghệ', NULL),
('FT9', 'Duyệt công nghệ', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `limit_inventory`
--

CREATE TABLE IF NOT EXISTS `limit_inventory` (
  `limit_inventory_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(50) NOT NULL,
  `date_wanted` date DEFAULT NULL,
  `time_modify` int(11) DEFAULT '0',
  `creator` varchar(45) NOT NULL,
  `created_date` date NOT NULL,
  `factory_manager_id` varchar(50) DEFAULT NULL,
  `factory_manager_sign_date` date DEFAULT NULL,
  `factory_manager_status` int(11) NOT NULL DEFAULT '0',
  `factory_manager_comment` tinytext,
  `directorate_id` varchar(50) DEFAULT NULL,
  `directorate_sign_date` date DEFAULT NULL,
  `directorate_status` int(11) NOT NULL DEFAULT '0',
  `directorate_comment` tinytext,
  PRIMARY KEY (`limit_inventory_id`),
  KEY `directorate_status` (`directorate_status`),
  KEY `directorate_id` (`directorate_id`),
  KEY `factory_manager_id` (`factory_manager_id`),
  KEY `order_id` (`order_id`),
  KEY `fk_li_u_idx` (`creator`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `limit_inventory`
--

INSERT INTO `limit_inventory` (`limit_inventory_id`, `order_id`, `date_wanted`, `time_modify`, `creator`, `created_date`, `factory_manager_id`, `factory_manager_sign_date`, `factory_manager_status`, `factory_manager_comment`, `directorate_id`, `directorate_sign_date`, `directorate_status`, `directorate_comment`) VALUES
(3, 'OrderFPT', '2016-04-30', 0, 'nvcn', '2016-04-30', NULL, NULL, 0, NULL, NULL, NULL, 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `limit_inventory_detail`
--

CREATE TABLE IF NOT EXISTS `limit_inventory_detail` (
  `limit_inventory_detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `limit_inventory_id` int(11) NOT NULL,
  `stock_name` varchar(50) NOT NULL,
  `material` varchar(50) NOT NULL,
  `quantity` int(11) NOT NULL,
  `O` double NOT NULL,
  `X` double NOT NULL,
  `Y` double NOT NULL,
  `Z` double NOT NULL,
  `note` tinytext,
  PRIMARY KEY (`limit_inventory_detail_id`),
  KEY `FK_limit_inventory_detail_idx` (`limit_inventory_id`),
  KEY `fk_lid_m_idx` (`material`),
  KEY `fk_lid_e_idx` (`stock_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

-- --------------------------------------------------------

--
-- Table structure for table `material`
--

CREATE TABLE IF NOT EXISTS `material` (
  `m_id` varchar(50) NOT NULL,
  `m_name` varchar(200) NOT NULL,
  `m_price` double NOT NULL,
  PRIMARY KEY (`m_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `material`
--

INSERT INTO `material` (`m_id`, `m_name`, `m_price`) VALUES
('Aluminum', 'Nhôm', 20000),
('Bronze', 'Đồng', 15000),
('Gold', 'Vàng', 50000),
('Iron', 'Sắt', 15000),
('Silver', 'Bạc', 30000),
('Wood', 'Gỗ', 25000);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE IF NOT EXISTS `orders` (
  `OrderID` varchar(50) NOT NULL,
  `CustomerID` int(11) DEFAULT NULL,
  `UserID` varchar(50) DEFAULT NULL,
  `ProjectName` varchar(50) DEFAULT NULL,
  `CreateDate` date DEFAULT NULL,
  `OrderContent` text,
  `Possibility` int(11) DEFAULT NULL,
  `JudgingContent` text,
  `ApproveStatus` int(11) DEFAULT NULL,
  `ApprovalContent` text,
  `Approver` varchar(50) DEFAULT NULL,
  `ProductName` varchar(50) DEFAULT NULL,
  `AmountOfProduct` int(11) DEFAULT NULL,
  `DueDate` date DEFAULT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `CustomerID` (`CustomerID`),
  KEY `UserID` (`UserID`),
  KEY `UserID_2` (`UserID`),
  KEY `Approver` (`Approver`),
  KEY `ApproveStatus` (`ApproveStatus`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`OrderID`, `CustomerID`, `UserID`, `ProjectName`, `CreateDate`, `OrderContent`, `Possibility`, `JudgingContent`, `ApproveStatus`, `ApprovalContent`, `Approver`, `ProductName`, `AmountOfProduct`, `DueDate`) VALUES
('OrderFPT', 1, 'nvcn', 'Hợp đồng sản xuất bàn ghế', '2016-04-03', 'Sản suất 5000 bộ bàn ghế bằng bạc', 1, NULL, 1, NULL, NULL, 'Bàn ghế', 4999, '2016-04-30');

-- --------------------------------------------------------

--
-- Table structure for table `processing_document`
--

CREATE TABLE IF NOT EXISTS `processing_document` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `created_date` date NOT NULL,
  `production_order` int(11) NOT NULL,
  `processing_technology_note` int(11) DEFAULT NULL,
  `program_note` varchar(50) DEFAULT NULL,
  `operation_trace_note` varchar(50) NOT NULL,
  `limit_inventory_note` int(11) NOT NULL,
  KEY `production_order` (`production_order`),
  KEY `processing_technology_note` (`processing_technology_note`),
  KEY `limit_inventory_note` (`limit_inventory_note`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `processing_program`
--

CREATE TABLE IF NOT EXISTS `processing_program` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `element_id` varchar(50) DEFAULT NULL,
  `creator_id` varchar(50) NOT NULL,
  `reciever_id` varchar(50) NOT NULL,
  `checked_id` varchar(50) DEFAULT NULL,
  `creator_date` date DEFAULT NULL,
  `reciever_date` date DEFAULT NULL,
  `checked_date` date DEFAULT NULL,
  `equipment` varchar(100) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `note` varchar(200) DEFAULT NULL,
  `image1` varchar(300) DEFAULT NULL,
  `image2` varchar(300) DEFAULT NULL,
  `image3` varchar(300) DEFAULT NULL,
  `image4` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_creator_id_idx` (`creator_id`),
  KEY `fk_reciever_id_idx` (`reciever_id`),
  KEY `fk_checked_id_idx` (`checked_id`),
  KEY `fk_element_id_idx` (`element_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Table structure for table `processing_program_detail`
--

CREATE TABLE IF NOT EXISTS `processing_program_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `processing_program_id` int(11) NOT NULL,
  `knife` varchar(50) NOT NULL,
  `parameter1` double NOT NULL,
  `parameter2` double NOT NULL,
  `parameter3` double NOT NULL,
  `parameter4` double NOT NULL,
  `regime1` double NOT NULL,
  `regime2` double NOT NULL,
  `regime3` double NOT NULL,
  `program_name` varchar(50) DEFAULT NULL,
  `theory_time` int(11) DEFAULT NULL,
  `real_time` int(11) DEFAULT NULL,
  `note` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_program_idx` (`processing_program_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=14 ;

-- --------------------------------------------------------

--
-- Table structure for table `processing_technology`
--

CREATE TABLE IF NOT EXISTS `processing_technology` (
  `pt_id` int(11) NOT NULL AUTO_INCREMENT,
  `pt_element_id` varchar(50) NOT NULL,
  `pt_drawing_symbols` varchar(50) DEFAULT NULL,
  `pt_creator_id` varchar(50) NOT NULL,
  `pt_leader_accept` varchar(50) DEFAULT NULL,
  `pt_manager_accept` varchar(50) DEFAULT NULL,
  `pt_created_day` date NOT NULL,
  `pt_check_day` date DEFAULT NULL,
  `pt_approve_day` date DEFAULT NULL,
  `pt_status` varchar(20) NOT NULL,
  `pt_note` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`pt_id`),
  KEY `fk_element_idx` (`pt_element_id`),
  KEY `fk_creator_user_idx` (`pt_creator_id`),
  KEY `fk_leader_accept_user_idx` (`pt_leader_accept`),
  KEY `fk_manager_accept_user_idx` (`pt_manager_accept`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Table structure for table `processing_technology_detail`
--

CREATE TABLE IF NOT EXISTS `processing_technology_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_processing_technology` int(11) DEFAULT NULL,
  `operation` varchar(100) DEFAULT NULL,
  `operation_content` varchar(200) DEFAULT NULL,
  `jig` varchar(50) DEFAULT NULL,
  `operation_drawing` varchar(100) DEFAULT NULL,
  `accessories` varchar(100) DEFAULT NULL,
  `note` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_processing_technology_idx` (`id_processing_technology`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=13 ;

-- --------------------------------------------------------

--
-- Table structure for table `production_order`
--

CREATE TABLE IF NOT EXISTS `production_order` (
  `po_id` int(11) NOT NULL AUTO_INCREMENT,
  `po_order_id` varchar(20) DEFAULT NULL,
  `po_content` varchar(1000) DEFAULT NULL,
  `po_quantity` int(11) DEFAULT NULL,
  `po_unit` varchar(50) DEFAULT NULL,
  `po_process_technology` tinyint(1) DEFAULT '0',
  `po_factory_manager` varchar(20) DEFAULT NULL,
  `po_approved_by` varchar(20) DEFAULT NULL,
  `po_status` varchar(20) DEFAULT NULL,
  `po_timelength` int(11) DEFAULT NULL,
  `po_starttime` date DEFAULT NULL,
  `po_finishtime` date DEFAULT NULL,
  PRIMARY KEY (`po_id`),
  KEY `po_id` (`po_id`),
  KEY `po_order_id` (`po_order_id`),
  KEY `po_factory_manager` (`po_factory_manager`),
  KEY `po_approved_by` (`po_approved_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `quotation`
--

CREATE TABLE IF NOT EXISTS `quotation` (
  `QuotationID` varchar(50) NOT NULL,
  `NumOfValidityDays` varchar(50) DEFAULT NULL,
  `NumOfDaysToComplete` int(11) DEFAULT NULL,
  `PaymentMethod1` varchar(50) DEFAULT NULL,
  `PaymentMethod2` varchar(55) DEFAULT NULL,
  `Status` int(11) DEFAULT NULL,
  `UserID` varchar(50) DEFAULT NULL,
  `PublishDate` date DEFAULT NULL,
  `Amount` double DEFAULT NULL,
  `VAT` double DEFAULT NULL,
  `TotalAmount` double DEFAULT NULL,
  `EstimateID` int(11) NOT NULL,
  `QuotationNo` int(11) DEFAULT NULL,
  PRIMARY KEY (`QuotationID`),
  KEY `UserID` (`UserID`),
  KEY `EstimateID` (`EstimateID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `quotationdetails`
--

CREATE TABLE IF NOT EXISTS `quotationdetails` (
  `QuotationDetailsID` int(11) NOT NULL AUTO_INCREMENT,
  `QuotationID` varchar(50) NOT NULL,
  `NameOfDetail` varchar(50) DEFAULT NULL,
  `Quantity` int(11) DEFAULT NULL,
  `Unit` varchar(50) DEFAULT NULL,
  `Price` double DEFAULT NULL,
  `Amount` double DEFAULT NULL,
  `Note` text,
  PRIMARY KEY (`QuotationDetailsID`),
  KEY `FK_QuoDe-Quo_idx` (`QuotationID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=36 ;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `role_id` varchar(50) NOT NULL,
  `role_name` varchar(100) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `modifier` varchar(50) DEFAULT NULL,
  `modified_date` date DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  KEY `creator` (`creator`),
  KEY `modifier` (`modifier`),
  KEY `creator_2` (`creator`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`role_id`, `role_name`, `description`, `creator`, `created_date`, `modifier`, `modified_date`) VALUES
('AD', 'Admin', NULL, NULL, NULL, NULL, NULL),
('BGD', 'Ban Giám Đốc', NULL, NULL, NULL, NULL, NULL),
('NVCN', 'Nhân viên công nghệ', NULL, NULL, NULL, NULL, NULL),
('PX', 'Phó Xưởng', 'Short Description', NULL, NULL, NULL, NULL),
('TCN', 'Tổ Công nghệ', 'tổ CN', 'admin', '2016-04-03', NULL, NULL),
('TPTCKT', 'Trưởng phòng Tài chính kế toán', '', NULL, NULL, NULL, NULL),
('TTTCN', 'Tổ trưởng tổ Công nghệ', NULL, 'admin', NULL, NULL, NULL),
('TX', 'Trưởng Xưởng', 'Trưởng xưởng', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `role_function`
--

CREATE TABLE IF NOT EXISTS `role_function` (
  `role_id` varchar(50) NOT NULL,
  `function_id` varchar(50) NOT NULL,
  PRIMARY KEY (`role_id`,`function_id`),
  KEY `fk_func_role_2` (`function_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `role_function`
--

INSERT INTO `role_function` (`role_id`, `function_id`) VALUES
('AD', 'FT1'),
('PX', 'FT1'),
('TCN', 'FT1'),
('AD', 'FT2'),
('TX', 'FT2'),
('AD', 'FT3'),
('TCN', 'FT3'),
('AD', 'FT4'),
('TX', 'FT4');

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE IF NOT EXISTS `status` (
  `status_id` int(11) NOT NULL AUTO_INCREMENT,
  `status_name` varchar(50) NOT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `status`
--

INSERT INTO `status` (`status_id`, `status_name`) VALUES
(1, 'approve_awaiting'),
(2, 'approved'),
(3, 'need_re_estimating'),
(4, 'cancelled');

-- --------------------------------------------------------

--
-- Table structure for table `stock_requisition`
--

CREATE TABLE IF NOT EXISTS `stock_requisition` (
  `requisition_id` int(11) NOT NULL AUTO_INCREMENT,
  `department` varchar(100) NOT NULL,
  `limit_inventory_id` int(11) NOT NULL,
  `order_id` varchar(50) NOT NULL,
  `date_wanted` date DEFAULT NULL,
  `creator` varchar(50) NOT NULL,
  `created_date` date NOT NULL,
  `recommend_supplier` tinytext,
  `reason` tinytext,
  `factory_manager` varchar(50) DEFAULT NULL,
  `factory_manager_status` int(1) NOT NULL DEFAULT '0',
  `factory_manager_sign_date` date DEFAULT NULL,
  `hfad` varchar(50) DEFAULT NULL,
  `hfad_status` int(1) NOT NULL DEFAULT '0',
  `hfad_sign_date` date DEFAULT NULL,
  `last_modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_user` varchar(50) NOT NULL,
  `factory_manager_comment` tinytext,
  `hfad_comment` tinytext,
  PRIMARY KEY (`requisition_id`),
  KEY `limit_inventory_id` (`limit_inventory_id`),
  KEY `order_id` (`order_id`),
  KEY `creator` (`creator`),
  KEY `factory_manager` (`factory_manager`),
  KEY `hfad` (`hfad`),
  KEY `last_modified_user` (`last_modified_user`),
  KEY `hfad_2` (`hfad`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

-- --------------------------------------------------------

--
-- Table structure for table `stock_requisition_details`
--

CREATE TABLE IF NOT EXISTS `stock_requisition_details` (
  `stock_requisition_details_id` int(11) NOT NULL AUTO_INCREMENT,
  `stock_requisition_id` int(11) NOT NULL,
  `name` varchar(80) NOT NULL,
  `phi` double NOT NULL,
  `length` double NOT NULL,
  `width` double NOT NULL,
  `height` double NOT NULL,
  `material` varchar(50) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `note` tinytext,
  PRIMARY KEY (`stock_requisition_details_id`),
  KEY `FK_stock_requisition_details__stock_requisition_idx` (`stock_requisition_id`),
  KEY `material` (`material`),
  KEY `material_2` (`material`),
  KEY `FK_srd_e_idx` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=14 ;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` varchar(50) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `phonenumber` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `department` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `role_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `password`, `name`, `birthdate`, `phonenumber`, `email`, `address`, `department`, `status`, `role_id`) VALUES
('admin', 'admin', 'Quản Văn Trị', NULL, NULL, NULL, NULL, NULL, NULL, 'AD'),
('bgd', 'bgd', 'Ban giám đốc', NULL, NULL, NULL, NULL, NULL, NULL, 'BGD'),
('nvcn', 'nvcn', 'Nhân viên Công nghệ', NULL, NULL, NULL, NULL, NULL, NULL, 'NVCN'),
('tcn1', 'tcn1', 'Tổ công nghệ 1', '2016-04-11', NULL, NULL, NULL, NULL, NULL, 'TCN'),
('tcn2', 'tcn2', 'Tổ công nghệ 2', NULL, NULL, NULL, NULL, NULL, NULL, 'TCN'),
('tttcn', 'tttcn', 'Tổ trưởng tổ Công nghệ', NULL, NULL, NULL, NULL, NULL, NULL, 'TTTCN'),
('txuong1', 'txuong1', 'Trưởng xưởng 1', NULL, NULL, NULL, NULL, NULL, NULL, 'TX'),
('txuong2', 'txuong2', 'Trưởng xưởng 2', NULL, NULL, NULL, NULL, NULL, NULL, 'TX');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `estimate`
--
ALTER TABLE `estimate`
  ADD CONSTRAINT `estimate_ibfk_1` FOREIGN KEY (`es_creator_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `estimate_ibfk_2` FOREIGN KEY (`es_order_id`) REFERENCES `orders` (`OrderID`),
  ADD CONSTRAINT `estimate_ibfk_3` FOREIGN KEY (`es_approver_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `estimate_ibfk_4` FOREIGN KEY (`es_status_id`) REFERENCES `status` (`status_id`);

--
-- Constraints for table `estimate_detail`
--
ALTER TABLE `estimate_detail`
  ADD CONSTRAINT `fk_estimate_detail_element` FOREIGN KEY (`ed_element_id`) REFERENCES `element` (`e_id`),
  ADD CONSTRAINT `fk_estimate_detail_estimate` FOREIGN KEY (`ed_estimate_id`) REFERENCES `estimate` (`es_id`),
  ADD CONSTRAINT `fk_estimate_detail_material` FOREIGN KEY (`ed_material_id`) REFERENCES `material` (`m_id`);

--
-- Constraints for table `limit_inventory`
--
ALTER TABLE `limit_inventory`
  ADD CONSTRAINT `fk_li_u` FOREIGN KEY (`creator`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `fk_li_u2` FOREIGN KEY (`factory_manager_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `fk_li_u3` FOREIGN KEY (`directorate_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `limit_inventory_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`OrderID`);

--
-- Constraints for table `limit_inventory_detail`
--
ALTER TABLE `limit_inventory_detail`
  ADD CONSTRAINT `fk_lid_e` FOREIGN KEY (`stock_name`) REFERENCES `element` (`e_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_lid_li` FOREIGN KEY (`limit_inventory_id`) REFERENCES `limit_inventory` (`limit_inventory_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_lid_m` FOREIGN KEY (`material`) REFERENCES `material` (`m_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`CustomerID`) REFERENCES `customer` (`customer_id`),
  ADD CONSTRAINT `fk_order_user` FOREIGN KEY (`UserID`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`ApproveStatus`) REFERENCES `status` (`status_id`),
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`Approver`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `processing_document`
--
ALTER TABLE `processing_document`
  ADD CONSTRAINT `processing_document_ibfk_3` FOREIGN KEY (`production_order`) REFERENCES `production_order` (`po_id`),
  ADD CONSTRAINT `processing_document_ibfk_1` FOREIGN KEY (`processing_technology_note`) REFERENCES `processing_technology` (`pt_id`),
  ADD CONSTRAINT `processing_document_ibfk_2` FOREIGN KEY (`limit_inventory_note`) REFERENCES `limit_inventory` (`limit_inventory_id`);

--
-- Constraints for table `processing_program`
--
ALTER TABLE `processing_program`
  ADD CONSTRAINT `fk_checked_id` FOREIGN KEY (`checked_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_creator_id` FOREIGN KEY (`creator_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_element_id` FOREIGN KEY (`element_id`) REFERENCES `element` (`e_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_reciever_id` FOREIGN KEY (`reciever_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `processing_program_detail`
--
ALTER TABLE `processing_program_detail`
  ADD CONSTRAINT `fk_program` FOREIGN KEY (`processing_program_id`) REFERENCES `processing_program` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `processing_technology`
--
ALTER TABLE `processing_technology`
  ADD CONSTRAINT `fk_creator_user` FOREIGN KEY (`pt_creator_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_element` FOREIGN KEY (`pt_element_id`) REFERENCES `element` (`e_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_leader_accept_user` FOREIGN KEY (`pt_leader_accept`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_manager_accept_user` FOREIGN KEY (`pt_manager_accept`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `processing_technology_detail`
--
ALTER TABLE `processing_technology_detail`
  ADD CONSTRAINT `fk_processing_technology` FOREIGN KEY (`id_processing_technology`) REFERENCES `processing_technology` (`pt_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `quotation`
--
ALTER TABLE `quotation`
  ADD CONSTRAINT `fk_quotation_estimate` FOREIGN KEY (`EstimateID`) REFERENCES `estimate` (`es_id`),
  ADD CONSTRAINT `fk_quotation_user` FOREIGN KEY (`UserID`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `quotationdetails`
--
ALTER TABLE `quotationdetails`
  ADD CONSTRAINT `FK_QuoDe-Quo` FOREIGN KEY (`QuotationID`) REFERENCES `quotation` (`QuotationID`);

--
-- Constraints for table `role`
--
ALTER TABLE `role`
  ADD CONSTRAINT `role_ibfk_1` FOREIGN KEY (`creator`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `role_ibfk_2` FOREIGN KEY (`modifier`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `role_function`
--
ALTER TABLE `role_function`
  ADD CONSTRAINT `fk_func_role_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  ADD CONSTRAINT `fk_func_role_2` FOREIGN KEY (`function_id`) REFERENCES `function` (`function_id`);

--
-- Constraints for table `stock_requisition`
--
ALTER TABLE `stock_requisition`
  ADD CONSTRAINT `stock_requisition_ibfk_1` FOREIGN KEY (`limit_inventory_id`) REFERENCES `limit_inventory` (`limit_inventory_id`),
  ADD CONSTRAINT `fk_sr_o` FOREIGN KEY (`order_id`) REFERENCES `orders` (`OrderID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_sr_u` FOREIGN KEY (`creator`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `fk_sr_u2` FOREIGN KEY (`factory_manager`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `fk_sr_u3` FOREIGN KEY (`hfad`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `fk_sr_u4` FOREIGN KEY (`last_modified_user`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `stock_requisition_details`
--
ALTER TABLE `stock_requisition_details`
  ADD CONSTRAINT `FK_srd_e` FOREIGN KEY (`name`) REFERENCES `element` (`e_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_stock_requisition_details__stock_requisition` FOREIGN KEY (`stock_requisition_id`) REFERENCES `stock_requisition` (`requisition_id`),
  ADD CONSTRAINT `stock_requisition_details_ibfk_1` FOREIGN KEY (`material`) REFERENCES `material` (`m_id`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

CREATE TABLE IF NOT EXISTS persistent_logins (
    username VARCHAR(64) NOT NULL,
    series VARCHAR(64) NOT NULL,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL,
    PRIMARY KEY (series)
);


INSERT INTO `serp`.`element` (`e_id`, `e_name`, `e_unit`) VALUES ('mat_ban', 'Mặt bàn', 'cái');
INSERT INTO `serp`.`estimate` (`es_id`, `es_creator_id`, `es_order_id`, `es_published_date`, `es_status_id`, `es_approver_id`, `es_approve_content`) VALUES (NULL, 'tcn1', 'OrderFPT', '2016-04-03', '2', NULL, NULL);
INSERT INTO `serp`.`estimate_detail` (`ed_id`, `ed_estimate_id`, `ed_element_id`, `ed_material_id`, `ed_quantity`, `ed_material_weight`, `ed_phi`, `ed_x`, `ed_y`, `ed_z`, `ed_material_cost`, `ed_labor_cost`, `ed_equipment_cost`, `ed_tool_cost`, `ed_external_cost`, `ed_price`, `ed_total_cost`) VALUES (NULL, '38', 'mat_ban', 'Gold', '5000', '5000', '0', '2000', '1200', '400', '100000', '200000', '200000', '100000', '', '600000', '3000000000');
INSERT INTO `serp`.`quotation`(QuotationID,NumOfValidityDays,NumOfDaysToComplete,PaymentMethod1,PaymentMethod2,Status,UserID,PublishDate,Amount,VAT,TotalAmount,EstimateID)
		VALUES (1,5,5,'Cash','Cash',3,'txuong1','2016-04-03',0,0,0,38);
INSERT INTO `serp`.`quotationdetails`(QuotationDetailsID,QuotationID,NameOfDetail, quantity, Unit,Price,Amount,note)
  VALUES(1,1,'Win 10',50,'Pieces',10000,500000,'Funny');
INSERT INTO `serp`.`quotationdetails`(QuotationDetailsID,QuotationID,NameOfDetail, quantity, Unit,Price,Amount,note)
  VALUES(2,1,'Win 10',50,'Pieces',10000,500000,'Funny');
INSERT INTO `production_order` (`po_id`, `po_order_id`, `po_content`, `po_quantity`, `po_unit`, `po_process_technology`, `po_factory_manager`, `po_approved_by`, `po_status`, `po_timelength`, `po_starttime`, `po_finishtime`) VALUES
(NULL, 'OrderFPT', 'San Xuat TV', 345, 'KG', NULL, 'txuong1', NULL, 1, 4, '2016-04-25', '2016-04-22');
INSERT INTO `serp`.`processing_technology` (`pt_id`, `pt_element_id`, `pt_drawing_symbols`, `pt_creator_id`, `pt_leader_accept`, `pt_manager_accept`, `pt_created_day`, `pt_check_day`, `pt_approve_day`, `pt_status`, `pt_note`) VALUES (NULL, 'mat_ban', 'DK_AG076B_1_002A', 'admin', 'admin', 'admin', '2016-05-01', '2016-05-10', '2016-05-10', '', NULL);
INSERT INTO `serp`.`processing_technology_detail` (`id`, `id_processing_technology`, `operation`, `operation_content`, `jig`, `operation_drawing`, `accessories`, `note`) VALUES (NULL, '2', 'Gia Cong', NULL, 'Eto', NULL, NULL, NULL);