<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx"%>
<tilesx:useAttribute name="css" classname="java.util.List" id="cssItems"/>
<tilesx:useAttribute name="headjs" classname="java.util.List" id="headjsItems"/>
<tilesx:useAttribute name="js" classname="java.util.List" id="jsItems"/>
<tilesx:useAttribute name="fonts" classname="java.util.List" id="fontItems"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <title><tiles:insertAttribute name="title"></tiles:insertAttribute></title>
    <c:forEach items="${cssItems}" var="css">
        <link href="<spring:url value="${css}"/>" type="text/css" rel="stylesheet" media="all"/>
    </c:forEach>
    <c:forEach items="${headjsItems}" var="headjs">
        <script src="<spring:url value="${headjs}"/>"></script>
    </c:forEach>
    <c:forEach items="${fontItems}" var="font">
        <link href="<spring:url value="${font}"/>" rel="stylesheet" type="text/css">
    </c:forEach>
    <!--animate-->
	<link href="/css/animate.css" rel="stylesheet" type="text/css" media="all">
	<script src="/js/wow.min.js"></script>
</head>
<body class="cbp-spmenu-push">
	<div class="main-content">
		<tiles:insertAttribute name="header"></tiles:insertAttribute>
		<tiles:insertAttribute name="body"></tiles:insertAttribute>
		<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	   <!-- Other js file -->
	   <c:forEach items="${jsItems}" var="js">
	            <script src="<spring:url value="${js}"/>"></script>
	   </c:forEach>
   </div>
</body>
</html>