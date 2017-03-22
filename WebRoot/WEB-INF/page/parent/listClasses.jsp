<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<div class="page">
    <!-- loading toast -->
    <div id="loadingClasses" style="display:none;">
        <div class="weui-mask_transparent"></div>
        <div class="weui-toast">
            <i class="weui-loading weui-icon_toast"></i>
            <p class="weui-toast__content">数据加载中</p>
        </div>
    </div>
	<div class="weui-cells__title">带说明、跳转的列表项</div>
	<div class="weui-cells weui-cells_form">
		<div class="weui-cell weui-cell_select">
			<div class="weui-cell__bd">
				<select class="weui-select" name="classes" id="classes">
					<option selected="" value="1">微信号</option>
					<option value="2">QQ号</option>
					<option value="3">Email</option>
				</select>
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__bd">
				<textarea class="weui-textarea" id="reason" placeholder="请输入文本" rows="3"></textarea>
				<div class="weui-textarea-counter">
					<span>0</span>/200
				</div>
			</div>
		</div>
	</div>
	<div class="weui-btn-area">
		<a class="weui-btn weui-btn_primary" href="javascript:" id="btnRequest">申请</a>
	</div>
</div>
<script type="text/javascript">
	$(function() {
		var $classes = $('#classes'), $loadingClasses = $('#loadingClasses'), $reason = $('#reason');

		function init() {
			if (window.pageManager && window.pageManager.parameters && window.pageManager.parameters.id) {
				$loadingClasses.show();
				showClassesList(window.pageManager.parameters.id);
			} else {
				//点击刷新按钮后的效果
				window.location = "index";
				//history.back();
			}
			
			$("#btnRequest").on("click", function(){
				console.log("classes:" + $classes.val());
				console.log("reason:" + $reason.val());
				$.ajax({
					type : 'post',
					url : 'requestJoinClasses',
					data : {
						classesId : $classes.val(),
						reason : $reason.val(),
					},
					success : function(data) {
						var jsonData = $.parseJSON(data);
						if (jsonData.isSuccess) {
				            window.pageManager.back();
						}
						$loadingClasses.hide();
					},
					error : function(xhr, type) {
						$loadingClasses.hide();
					}
				});
			});
		}

		function showClassesList(id) {
			$.ajax({
				type : 'post',
				url : 'searchClasses',
				data : {
					id : id
				},
				success : function(data) {
					var jsonData = $.parseJSON(data);
					if (jsonData.isSuccess) {
						var classesList = jsonData.classesList;
						$classes.empty();
						
						for (var i = 0; i < classesList.length; i++) {
							var classes = classesList[i];
							$classes.append("<option value='" + classes.serialNo + "'>" + classes.name + "</option>");
						}
					}
					$loadingClasses.hide();
				},
				error : function(xhr, type) {
					$loadingClasses.hide();
				}
			});
		}
		init();
	});
</script>