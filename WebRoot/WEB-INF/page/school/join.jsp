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
		<script src="<%=path%>/resources/js/common.js"></script>
		
		<script type="text/javascript">
			$(function() {

				var $schoolId = $('#schoolId'), 
					$classesId = $('#classesId'), 
					$relation = $('#relation'), 
					$mobile = $('#mobile'), 
					$loadingClasses = $('#loadingClasses');
				
				$(".weui-cell_access").on("click", function(){
					console.log($(this).attr("data-id"));
					$classesId.val($(this).attr("data-id"));
					$("#page1").hide();
					$("#page2").show();
				});
				
				$("#page2").find(".weui-btn").on("click", function(){
					
					if (isNull($classesId.val()) || isNull($relation.val()) || isNull($mobile.val()))
					{
						return;
					}
					
					$loadingClasses.show();
					$.ajax({
						type : 'post',
						url : '../classes/joinClasses',
						data : {
							schoolId : $schoolId.val(),
							classesId : $classesId.val(),
							relation : $relation.val(),
							mobile : $mobile.val()
						},
						success : function(data) {
							console.log(data);
							var jsonData = $.parseJSON(data);
							if (jsonData.isSuccess) {
								showSuccessInfo("绑定成功了");
							}
							else{
								showErrorInfo(jsonData.errorCode);
							}
							$loadingClasses.hide();
						},
						error : function(xhr, type) {
							$loadingClasses.hide();
						}
					});
				});
				
				function init()
				{
					$(".msg_success").find(".weui-btn_primary").text("返回我的首页");
					$(".msg_success").find(".weui-btn_default").text("继续绑定");
					
					$(".msg_success").find(".weui-btn_default").on("click", function(){
						window.location = "../school/join?schoolId=" + $schoolId.val();
					});
					$(".msg_success").find(".weui-btn_primary").on("click", function(){
						window.location = "../parent/index?schoolId=" + $schoolId.val();
					});
					
					$(".msg_warn").find(".weui-btn_primary").text("再试一次");
					$(".msg_warn").find(".weui-btn_default").text("一会再来");
					
					$(".msg_warn").find(".weui-btn_primary").on("click", function(){
						$(".msg_warn").removeClass("js_show").hide();
					});
					$(".msg_warn").find(".weui-btn_default").on("click", function(){
						window.location = "../parent/index?schoolId=" + $schoolId.val();
					});
				}
				
				init();
			});
		</script>
	</head>
	<body>
		<input type="hidden" name="schoolId" id="schoolId" value="${schoolId}">
		<input type="hidden" name="classesId" id="classesId">
		
		<div class="weui-cells" id="page1">
			<c:forEach items="${classesList}" var="classes">
				<div class="weui-cell weui-cell_access" data-id="${classes.serialNo}">
					<div class="weui-cell__bd">${classes.name}</div>
					<div class="weui-cell__ft" style="font-size: 0">
						<span style="vertical-align:middle; font-size: 17px;">加入</span>
					</div>
				</div>
			</c:forEach>
		</div>
		<div id="page2" style="display: none;">
			<div class="weui-cells">
				<div class="weui-cell weui-cell_select">
					<div class="weui-cell__bd">
						<select class="weui-select" name="relation" id="relation">
							<option selected="" value="">请选择</option>
							<c:forEach var="parameter" items="${parameterList}">
								<option value="${parameter.parameterValue}">${parameter.displayName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="weui-cell">
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" name="mobile" id="mobile" placeholder="请输入已经绑定的手机号码">
					</div>
				</div>
			</div>
			<div class="weui-btn-area">
				<a class="weui-btn weui-btn_primary" href="javascript:" id="showTooltips">确定</a>
			</div>
		</div>
		
		
		<c:import url="/msg/warn"></c:import>
		<c:import url="/msg/success"></c:import>
	    <!-- loading toast -->
	    <div id="loadingClasses" style="display:none;">
	        <div class="weui-mask_transparent"></div>
	        <div class="weui-toast">
	            <i class="weui-loading weui-icon_toast"></i>
	            <p class="weui-toast__content">数据加载中</p>
	        </div>
	    </div>
	</body>
</html>