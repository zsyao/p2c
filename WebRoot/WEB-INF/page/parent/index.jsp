<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
		<title>测试</title>
		<link rel="stylesheet" href="<%=path%>/resources/css/weui.css" />
		<link rel="stylesheet" href="<%=path%>/resources/css/example.css" />
		<script src="<%=path%>/resources/js/zepto.min.js"></script>
		<script src="<%=path%>/resources/js/pageManage.js"></script>
		
	</head>
	<body>
		<div class="container" id="container">
		</div>
		<script type="text/html" id="tpl_home">
			<c:import url="/parent/listSchool"></c:import>
		</script>
		<script type="text/html" id="tpl_listClasses">
			<c:import url="/parent/listClasses"></c:import>
		</script>
	</body>
</html>