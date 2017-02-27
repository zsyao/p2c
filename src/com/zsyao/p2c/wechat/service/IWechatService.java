package com.zsyao.p2c.wechat.service;



public interface IWechatService
{
	boolean checkEchostr(int wechatId, String signature, String timestamp, String nonce) throws Exception;
	
	String handleMessage(int wechatId, String content) throws Exception;

    String getAccessToken(int wechatId) throws Exception;

    String getJsapiTicket(int wechatId) throws Exception;
}
