<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<div class="page">
    <!-- loading toast -->
    <div id="loadingToast" style="display:none;">
        <div class="weui-mask_transparent"></div>
        <div class="weui-toast">
            <i class="weui-loading weui-icon_toast"></i>
            <p class="weui-toast__content">数据加载中</p>
        </div>
    </div>
	<div class="weui-search-bar" id="searchBar">
		<form class="weui-search-bar__form">
			<div class="weui-search-bar__box">
				<i class="weui-icon-search"></i> <input type="search"
					class="weui-search-bar__input" id="searchInput" placeholder="搜索"
					required /> <a href="javascript:" class="weui-icon-clear"
					id="searchClear"></a>
			</div>
			<label class="weui-search-bar__label" id="searchText"> <i
				class="weui-icon-search"></i> <span>搜索</span>
			</label>
		</form>
		<a href="javascript:" class="weui-search-bar__cancel-btn"
			id="searchConfirm">搜索</a>
	</div>
	<div class="weui-panel weui-panel_access">
		<div class="weui-panel__hd">图文组合列表</div>
		<div class="weui-panel__bd" id="searchResult">
			<c:forEach items="${schoolList}" var="school">
				<a href="javascript:void(0);" data-id="${school.serialNo}"
					class="weui-media-box weui-media-box_appmsg">
					<div class="weui-media-box__hd">
						<img class="weui-media-box__thumb"
							src="<%=path%>/upload/headImage/default.png" alt="">
					</div>
					<div class="weui-media-box__bd">
						<h4 class="weui-media-box__title">${school.name}</h4>
						<p class="weui-media-box__desc">${school.address}</p>
					</div>
				</a>
			</c:forEach>
		</div>
		<div class="weui-panel__ft">
			<a href="javascript:void(0);"
				class="weui-cell weui-cell_access weui-cell_link">
				<div class="weui-cell__bd">请通过名称搜索进行筛选</div>
			</a>
		</div>
	</div>
</div>

<script type="text/javascript">
    $(function(){
        var $searchBar = $('#searchBar'),
        	$container = $('#container'),
            $searchResult = $('#searchResult'),
            $searchText = $('#searchText'),
            $searchInput = $('#searchInput'),
            $searchClear = $('#searchClear'),
            $loadingToast = $('#loadingToast'),
            $weuiMediaBox = $('.weui-media-box'),
            $searchConfirm = $('#searchConfirm');

        function cancelSearch(){
            $searchInput.val('');
        }
        
        function confirmSearch(){
        	if(!$searchInput.val().length){
	            $searchBar.removeClass('weui-search-bar_focusing');
	            return;
        	}
        	$loadingToast.show();
        	$.ajax({
    			type : 'post',
    			url : 'searchSchool',
    			data:
    			{
    				name:		$searchInput.val()
    			},
    			success : function(data)
    			{
    				var jsonData = $.parseJSON(data);
    				if (jsonData.isSuccess)
    				{
    					var schoolList = jsonData.schoolList;
    					var html = '';
    					for (var i = 0; i < schoolList.length; i++)
    					{
    						var school = schoolList[i];
    						html += '<a href="javascript:void(0);" data-id="' + school.serialNo + '" class="weui-media-box weui-media-box_appmsg">'
    							+ '<div class="weui-media-box__hd">'
    							+ '<img class="weui-media-box__thumb" src="<%=path%>/upload/headImage/default.png" alt="">'
    							+ '</div>'
    							+ '<div class="weui-media-box__bd">'
    							+ '<h4 class="weui-media-box__title">' + school.name + '</h4>'
    							+ '<p class="weui-media-box__desc">' + school.address + '</p>'
    							+ '</div>'
    							+ '</a>';
    					}
    					$searchResult.html(html);

    			        $(".weui-media-box").on('click', function(){
    			        	bindMediaBoxEvent($(this));
    			        });
    				}
    	        	$loadingToast.hide();
    	        	console.log(data);
    			},
    			error: function(xhr, type)
    			{
    				$loadingToast.hide();
    			}
        	});
        }
        
        function bindMediaBoxEvent(obj)
        {
        	console.log(obj);
        	window.pageManager.parameters = {id : $(obj).data('id')};
            window.pageManager.go("listClasses");
        }
        
        $weuiMediaBox.on('click', function(){
        	bindMediaBoxEvent($(this));
        });
        $searchInput.on('blur', function () {
        	if(!this.value.length)
        	{
        		cancelSearch();
	            $searchBar.removeClass('weui-search-bar_focusing');
        	}
        });
        $searchText.on('click', function(){
            $searchBar.addClass('weui-search-bar_focusing');
            $searchInput.focus();
        });
        $searchClear.on('click', function(){
        	cancelSearch();
            $searchInput.focus();
        });
        $searchConfirm.on('click', function(){
            confirmSearch();
            $searchInput.blur();
        });
    });
</script>