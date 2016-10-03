<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="navbar navbar-default" role="navigation">
        <div class="navbar-inner">
            <button type="button" class="navbar-toggle pull-left animated flip">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="home"> <img alt="Charisma Logo" src="${pageContext.request.contextPath}/resources/themes/charisma-master/img/logo20.png" class="hidden-xs"/>
                <span>SERP</span></a>

            <!-- user dropdown starts -->
            <div class="btn-group pull-right" id="LogInLogOut">
                <!-- 
                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    <i class="glyphicon glyphicon-user"></i><span class="hidden-sm hidden-xs"> admin</span>
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li>
                    	<a class="ajax-link" href="user">
                    		<i class="glyphicon glyphicon-user"></i>
                    		<span> <r:message code="user.view" /></span>
                    	</a>
                    </li>
                    <li class="divider"></li>
                    <li><a href="<c:url value="/logout" />">Logout</a></li>
                </ul>
                 -->
            </div>
            <!-- user dropdown ends -->

            <ul class="collapse navbar-collapse nav navbar-nav top-menu">
                <!-- <li><a href="#"><i class="glyphicon glyphicon-globe"></i> Visit Site</a></li> -->
                <li class="dropdown">
                    <a href="#" data-toggle="dropdown"><i class="glyphicon glyphicon-star"></i> <r:message code="dropbox.language"/> <span
                            class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="?lang=en"><r:message code="language.en"/></a></li>
                        <li class="divider"></li>
                        <li><a href="?lang=vn"><r:message code="language.vn"/></a></li>
                    </ul>
                </li>
                
<%--                 <li>
                    <form class="navbar-search pull-left">
                        <input placeholder="Search" class="search-query form-control col-md-10" name="query"
                               type="text">
                    </form>
                </li> --%>
            </ul>

        </div>
    </div>
