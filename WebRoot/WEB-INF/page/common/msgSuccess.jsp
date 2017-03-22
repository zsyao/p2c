<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<div class="page msg_success" style="display: none">
	<div class="weui-msg">
		<div class="weui-msg__icon-area">
			<i class="weui-icon-success weui-icon_msg"></i>
		</div>
		<div class="weui-msg__text-area">
			<h2 class="weui-msg__title">操作成功</h2>
			<p class="weui-msg__desc">
				内容详情，可根据实际需要安排，如果换行则不超过规定长度，居中展现
			</p>
		</div>
		<div class="weui-msg__opr-area">
			<p class="weui-btn-area">
				<a href="#" class="weui-btn weui-btn_primary">确定</a>
				<a href="#" class="weui-btn weui-btn_default">取消</a>
			</p>
		</div>
	</div>
</div>