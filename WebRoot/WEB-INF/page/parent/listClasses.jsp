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
	<div class="weui-cells" id="classesList">
	</div>
</div>
<script type="text/javascript">
	$(function(){
		var $classesList = $('#classesList'),
	        $loadingClasses = $('#loadingClasses');
		
		function init(){
        	if (window.pageManager && window.pageManager.parameters 
        			&& window.pageManager.parameters.id)
        	{
            	$loadingClasses.show();
            	showClassesList(window.pageManager.parameters.id);
        	}
        	else
        	{
        		//点击刷新按钮后的效果
        		window.location="index";
        		//history.back();
        	}
		}
		
		function showClassesList(id)
		{
			$.ajax({
    			type : 'post',
    			url : 'searchClasses',
    			data:
    			{
    				id:		id
    			},
    			success : function(data)
    			{
    				var jsonData = $.parseJSON(data);
    				if (jsonData.isSuccess)
    				{
    					var classesList = jsonData.classesList;
    					var html = '';
    					for (var i = 0; i < classesList.length; i++)
    					{
    						var classes = classesList[i];
    						html += '<a class="weui-cell weui-cell_access" data-id="' + classes.serialNo + '" href="javascript:;">'
    							+ '<div class="weui-cell__bd">'
    							+ '<p>' + classes.name + '</p>'
    							+ '</div>'
    							+ '<div class="weui-cell__ft">申请加入</div>'
    							+ '</a>';
    					}
    					$classesList.html(html);
    					$(".weui-cell_access").on("click", function(){
    						submitRequest($(this).data("id"));
    					});
    				}
    	        	$loadingClasses.hide();
    			},
    			error: function(xhr, type)
    			{
    				$loadingClasses.hide();
    			}
        	});
		}
		
		function submitRequest(id)
		{
			console.log(id);
		}
		init();
	});
</script>