function showErrorInfo(errorCode)
{
	var codeList = [111111, 100001, 100002, 100003];
	var msgList = ["系统出错了，请稍后重试！", "手机号码没有对应的！", "手机号码对应多个！", "保存数据失败，请稍后重试！"];
	var errorCodeMsg = "系统出错了，请稍后重试！";
	
	for (var i = 0; i < codeList.length; i++)
	{
		if (errorCode == codeList[i])
		{
			errorCodeMsg = msgList[i];
			break;
		}
	}
	
	$(".msg_warn").addClass("js_show").show();
	console.log($(".msg_warn").find(".weui-msg__desc").text(errorCodeMsg));
}

function showSuccessInfo(msg)
{
	$(".msg_success").addClass("js_show").show();
	console.log($(".msg_success").find(".weui-msg__desc").text(msg));
}

function isNull(val)
{
	if(val.length == 0 || val == undefined || val == "" || val == null)
	{
		return true;
	}
	return false;
}