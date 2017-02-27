package com.zsyao.p2c.wechat.model;

import net.sf.json.JSONObject;

import com.zsyao.core.model.BaseEntity;
import com.zsyao.util.JSONObjectUtil;
import com.zsyao.util.MD5;


@SuppressWarnings("serial")
public class WMUserInfo extends BaseEntity
{
	private String openId;
	private String nickName;
	private String sex;//值为1时是男性，值为2时是女性，值为0时是未知
	private String country;
	private String province;
	private String city;
	private String headimgUrl;
	private String subscribe;
	private String subscribeTime;
	private String unionId;
	private String remark;
	private String groupId;
	private String customerId;
	private Integer wechatId;
	
	public String getOpenId()
	{
		return openId;
	}
	
	public String getNickName()
	{
		return nickName;
	}
	
	public String getSex()
	{
		return sex;
	}
	
	public String getCountry()
	{
		return country;
	}
	
	public String getProvince()
	{
		return province;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public String getHeadimgUrl()
	{
		return headimgUrl;
	}
	
	public String getSubscribe()
	{
		return subscribe;
	}
	
	public String getSubscribeTime()
	{
		return subscribeTime;
	}
	
	public String getUnionId()
	{
		return unionId;
	}
	
	public String getRemark()
	{
		return remark;
	}
	
	public String getGroupId()
	{
		return groupId;
	}
	
	public String getCustomerId()
	{
		return customerId;
	}
	
	public void setOpenId(String openId)
	{
		this.openId = openId;
	}
	
	public void setNickName(String nickName)
	{
		this.nickName = nickName;
	}
	
	public void setSex(String sex)
	{
		this.sex = sex;
	}
	
	public void setCountry(String country)
	{
		this.country = country;
	}
	
	public void setProvince(String province)
	{
		this.province = province;
	}
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	public void setHeadimgUrl(String headimgUrl)
	{
		this.headimgUrl = headimgUrl;
	}
	
	public void setSubscribe(String subscribe)
	{
		this.subscribe = subscribe;
	}
	
	public void setSubscribeTime(String subscribeTime)
	{
		this.subscribeTime = subscribeTime;
	}
	
	public void setUnionId(String unionId)
	{
		this.unionId = unionId;
	}
	
	public void setRemark(String remark)
	{
		this.remark = remark;
	}
	
	public void setGroupId(String groupId)
	{
		this.groupId = groupId;
	}
	
	public void setCustomerId(String customerId)
	{
		this.customerId = customerId;
	}

	public Integer getWechatId()
	{
		return wechatId;
	}

	public void setWechatId(Integer wechatId)
	{
		this.wechatId = wechatId;
	}
	

	public static WMUserInfo fromJSON(String json)
	{
		return fromJSONObject(JSONObject.fromObject(json));
	}
	
	public static WMUserInfo fromJSONObject(JSONObject json)
	{
		WMUserInfo user = new WMUserInfo();
		user.openId = JSONObjectUtil.getJSONStringValue(json, "openid");
		user.customerId = MD5.code(user.openId, 16);
		user.nickName = JSONObjectUtil.getJSONStringValue(json, "nickname");
		user.sex = JSONObjectUtil.getJSONStringValue(json, "sex");
		user.country = JSONObjectUtil.getJSONStringValue(json, "country");
		user.province = JSONObjectUtil.getJSONStringValue(json, "province");
		user.city = JSONObjectUtil.getJSONStringValue(json, "city");
		user.headimgUrl = JSONObjectUtil.getJSONStringValue(json, "headimgurl");
		user.subscribe = JSONObjectUtil.getJSONStringValue(json, "subscribe");
		user.subscribeTime = JSONObjectUtil.getJSONStringValue(json, "subscribe_time");
		user.unionId = JSONObjectUtil.getJSONStringValue(json, "unionid");
		user.remark = JSONObjectUtil.getJSONStringValue(json, "remark");
		user.groupId = JSONObjectUtil.getJSONStringValue(json, "groupId");
		return user;
	}
}
