package com.zsyao.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.WritableResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class WechatUtil
{
	private static Logger logger = Logger.getLogger(WechatUtil.class);
	
	public static boolean sendCard(String accessToken, String apiTicket, String openId, String cardId)
	{
		OutputStream output = null;
		InputStream input = null;
		try
		{
			URL url = new URL("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + accessToken);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			output = http.getOutputStream();
			
			JSONObject sendData = new JSONObject();
			sendData.put("touser", openId);
			sendData.put("msgtype", "wxcard");
			JSONObject jsonCard = new JSONObject();
			jsonCard.put("card_id", cardId);
			
			long timestamp = System.currentTimeMillis() / 1000;
			JSONObject jsonCardExt = new JSONObject();
			jsonCardExt.put("code", "");
			jsonCardExt.put("openid", "");
			jsonCardExt.put("timestamp", timestamp);
			
			String nonceStr = UUID.randomUUID().toString();

			jsonCardExt.put("nonceStr", nonceStr);
			
			//api_ticket、timestamp、card_id、code、openid、nonce_str
			String[] arrays = {apiTicket, "" + timestamp, cardId, "", "", nonceStr};
			Arrays.sort(arrays);
	        
			String encodingStr = "";
			for (String str : arrays)
			{
				encodingStr += str;
			}
			String signature = "";
			try
			{
				signature = SHA1.enCoding(encodingStr);
			}
			catch(Exception e)
			{
			}
			jsonCardExt.put("signature", signature);
			jsonCard.put("card_ext", jsonCardExt);
			sendData.put("wxcard", jsonCard);
			
			output.write(sendData.toString().getBytes("UTF-8"));// 传入参数
			output.flush();
			
			input = http.getInputStream();
			String result = new String(InputStreamUtil.inputStreamToBytes(input), "UTF-8");
			JSONObject jsonReturn = JSONObject.fromObject(result);
			String errCode = JSONObjectUtil.getJSONStringValue(jsonReturn, "errcode");
			if (StringUtil.stringIsEmptyStr(errCode))
			{
				return true;
			}
			logger.info(result);
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		finally
		{
			if (output != null)
			{
				try
				{
					output.close();
				}
				catch (Exception e)
				{
					logger.error(e);
				}
			}
			if (input != null)
			{
				try
				{
					input.close();
				}
				catch (Exception e)
				{
					logger.error(e);
				}
			}
		}
		return false;
	}
	
	public static JSONObject uploadCardLogo(String accessToken, String filePath)
	{
		WritableResource resource = new FileSystemResource(new File(filePath));
		MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
		data.add("buffer", resource);
		String urlString = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=" + accessToken;
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.postForObject(urlString, data, String.class);
		logger.info(result);
		
		JSONObject jsonReturn = JSONObject.fromObject(result);
		return jsonReturn;
	}
	
	public static boolean updateRemark(String accessToken, String openId, String remark)
	{
		OutputStream output = null;
		InputStream input = null;
		try
		{
			URL url = new URL("https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=" + accessToken);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			output = http.getOutputStream();
			JSONObject parameter = new JSONObject();
			parameter.put("openid", openId);
			parameter.put("remark", remark);
			output.write(parameter.toString().getBytes("UTF-8"));// 传入参数
			output.flush();
			
			input = http.getInputStream();
			String result = new String(InputStreamUtil.inputStreamToBytes(input), "UTF-8");
			
			JSONObject jsonReturn = JSONObject.fromObject(result);
			
			if ("0".equals(jsonReturn.getString("errcode")))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (Exception e)
		{
			logger.error(e);
			return false;
		}
		finally
		{
			if (output != null)
			{
				try
				{
					output.close();
				}
				catch (Exception e)
				{
					logger.error(e);
				}
			}
			if (input != null)
			{
				try
				{
					input.close();
				}
				catch (Exception e)
				{
					logger.error(e);
				}
			}
		}
	}
	
	public static boolean checkEchostr(String token, String signature, String timestamp, String nonce)
	{
		String[] tmpArray = new String[]{token, timestamp, nonce};
		Arrays.sort(tmpArray);
        
		String encodingStr = "";
		for (String str : tmpArray)
		{
			encodingStr += str;
		}
		try
		{
			String encodedStr = SHA1.enCoding(encodingStr);
			if (encodedStr != null && encodedStr.equalsIgnoreCase(signature))
			{
				return true;
			}
		}
		catch(Exception e)
		{
			logger.error(e);
			return false;
		}
		return false;
	}

	public static String getApiTicket(String accessToken)
	{
		String apiTicket = "";
		InputStream input = null;
		try
		{
			String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accessToken + "&type=wx_card";

			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			input = http.getInputStream();
			JSONObject tokenJson = JSONObject.fromObject(new String(InputStreamUtil.inputStreamToBytes(input), "UTF-8"));
			apiTicket = tokenJson.getString("ticket");
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		finally
		{
			if (input != null)
			{
				try
				{
					input.close();
				}
				catch (Exception e)
				{
				}
			}
		}
		logger.info("apiTicket:" + apiTicket);
		return apiTicket;
	}

	public static String getJsapiTicket(String accessToken)
	{
		String jsapiTicket = null;
		InputStream input = null;
		try
		{
			String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accessToken + "&type=jsapi";

			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			input = http.getInputStream();
			JSONObject tokenJson = JSONObject.fromObject(new String(InputStreamUtil.inputStreamToBytes(input), "UTF-8"));
			jsapiTicket = tokenJson.getString("ticket");
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		finally
		{
			if (input != null)
			{
				try
				{
					input.close();
				}
				catch (Exception e)
				{
				}
			}
		}
		logger.info("jsapiTicket:" + jsapiTicket);
		return jsapiTicket;
	}
	
	public static JSONObject getUnsubscribeUser(String openId, String accessToken)
	{
		InputStream input = null;
		try
		{
			String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openId + "&lang=zh_CN";

			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			input = http.getInputStream();
			return JSONObject.fromObject(new String(InputStreamUtil.inputStreamToBytes(input), "UTF-8"));
		}
		catch (Exception e)
		{
			logger.error(e);
			return null;
		}
		finally
		{
			if (input != null)
			{
				try
				{
					input.close();
				}
				catch (Exception e)
				{
				}
			}
		}
	}
	
	public static JSONObject getUserInfo(String accessToken, String openId)
	{
		logger.info("accessToken:" + accessToken);
		logger.info("openId:" + openId);
		InputStream input = null;
		try
		{
			String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + accessToken + "&openid=" + openId + "&lang=zh_CN";

			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			input = http.getInputStream();
			JSONObject jsonReturn = JSONObject.fromObject(new String(InputStreamUtil.inputStreamToBytes(input), "UTF-8"));
			logger.info("getUserInfo:" + jsonReturn);
			return JSONObject.fromObject(jsonReturn);
		}
		catch (Exception e)
		{
			logger.error(e);
			return null;
		}
		finally
		{
			if (input != null)
			{
				try
				{
					input.close();
				}
				catch (Exception e)
				{
				}
			}
		}
	}

	public static String getAccessToken(String appId, String appSecret)
	{
		logger.info("开始获取AccessToken");
		InputStream input = null;
		try
		{
			String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential" + "&appid=" + appId + "&secret=" + appSecret;

			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			input = http.getInputStream();
			JSONObject tokenJson = JSONObject.fromObject(new String(InputStreamUtil.inputStreamToBytes(input), "UTF-8"));

			logger.info("AccessToken：" + tokenJson);
			return tokenJson.getString("access_token");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
			if (input != null)
			{
				try
				{
					input.close();
				}
				catch (Exception e)
				{
				}
			}
		}
	}
	public static String downLoadImage(String accessToken, String mediaId, String saveFilePath)
	{
		logger.info("Start downLoadImage mediaId:" + mediaId);
		String url = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=" + accessToken + "&media_id=" + mediaId;

		InputStream input = null;
		FileOutputStream fo = null;
		
		try
		{
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();

			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			input = http.getInputStream();

			String fileName = System.currentTimeMillis() + ".jpg";
			try
			{
				String contentType = http.getHeaderField("Content-Type");
				logger.info(contentType);
				
				if (contentType != null && contentType.equals("image/jpeg"))
				{
					File imageFolder = new File(saveFilePath);
					if (!imageFolder.exists())
					{
						imageFolder.mkdirs();
					}
					
					logger.info("File:" + (saveFilePath + fileName));
					File imageFile = new File(saveFilePath + fileName);
					fo = new FileOutputStream(imageFile);
					int pos = -1;
					byte[] byteContent = new byte[1024];
					while ((pos = input.read(byteContent)) != -1)
					{
						fo.write(byteContent, 0, pos);
					}
					fo.flush();
					return fileName;
				}
				else if (contentType != null && contentType.equals("text/plain"))
				{
					ByteArrayOutputStream bo = new ByteArrayOutputStream();
					int pos = -1;
					byte[] byteContent = new byte[1024];
					while ((pos = input.read(byteContent)) != -1)
					{
						bo.write(byteContent, 0, pos);
					}
					bo.flush();
					
					String content = new String(bo.toByteArray());
					logger.info("内容：" + content);
					
					JSONObject jsonContent = JSONObject.fromObject(content);
					logger.info("errcode:" + jsonContent.getString("errcode"));
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				logger.error(e);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			logger.error(e);
		}
		finally
		{
			try
			{
				input.close();
			}
			catch (Exception e)
			{
			}
			try
			{
				if (fo != null)
				{
					fo.close();;
				}
			}
			catch (Exception e)
			{
			}
		}
		return null;
	}
	
	public static JSONObject getAccessTokenByToken(String appId, String appSecret, String code)
	{
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId
			+ "&secret=" + appSecret + "&code=" + code + "&grant_type=authorization_code";

		InputStream input = null;
		try
		{
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();

			http.setRequestMethod("GET");//必须是get方式请求
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			input = http.getInputStream();

			String message = new String(InputStreamUtil.inputStreamToBytes(input), "UTF-8");
			JSONObject json = JSONObject.fromObject(message);
			logger.info("getOpenId Message:" + message);
			return json;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				input.close();
			}
			catch (Exception e)
			{
			}
		}
		return null;
	}

	public static String getOpenId(String appId, String appSecret, String code)
	{
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId
			+ "&secret=" + appSecret + "&code=" + code + "&grant_type=authorization_code";

		InputStream input = null;
		try
		{
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();

			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			input = http.getInputStream();

			String message = new String(InputStreamUtil.inputStreamToBytes(input), "UTF-8");
			JSONObject json = JSONObject.fromObject(message);
			logger.info("getOpenId Message:" + message);
//			System.out.println(json.get("openid"));
			return json.getString("openid");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				input.close();
			}
			catch (Exception e)
			{
			}
		}
		return null;
	}
	
	public static String getTemplateId(String accessToken, String shortId) throws Exception
	{
		OutputStream output = null;
		InputStream input = null;
		try
		{
			URL url = new URL("https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=" + accessToken);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			output = http.getOutputStream();
			output.write(("{\"template_id_short\":\"" + shortId + "\"}").getBytes("UTF-8"));// 传入参数
			output.flush();
			
			input = http.getInputStream();
			String result = new String(InputStreamUtil.inputStreamToBytes(input), "UTF-8");
			
			JSONObject jsonReturn = JSONObject.fromObject(result);
			
			if ("0".equals(jsonReturn.getString("errcode")))
			{
				return jsonReturn.getString("template_id");
			}
			else
			{
				throw new Exception(jsonReturn.getString("errmsg"));
			}
		}
		catch (Exception e)
		{
			logger.error(e);
			throw e;
		}
		finally
		{
			if (output != null)
			{
				try
				{
					output.close();
				}
				catch (Exception e)
				{
					logger.error(e);
				}
			}
			if (input != null)
			{
				try
				{
					input.close();
				}
				catch (Exception e)
				{
					logger.error(e);
				}
			}
		}
	}
	
	public static boolean sendTmpelateMessage(String accessToken, String openId, String templateId, String viewDetailUrl, String topColor, JSONObject sendData) throws Exception
	{
		JSONObject jsonSendContent = new JSONObject();
		jsonSendContent.put("touser", openId);
		jsonSendContent.put("template_id", templateId);
		jsonSendContent.put("url", viewDetailUrl);
		jsonSendContent.put("data", sendData);
		
		if (!StringUtil.stringIsEmptyStr(topColor))
		{
			jsonSendContent.put("topcolor", topColor);
		}
		
		OutputStream output = null;
		InputStream input = null;
		try
		{
			URL url = new URL("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			output = http.getOutputStream();
			output.write(jsonSendContent.toString().getBytes("UTF-8"));// 传入参数
			output.flush();
			
			input = http.getInputStream();
			String result = new String(InputStreamUtil.inputStreamToBytes(input), "UTF-8");
			JSONObject jsonReturn = JSONObject.fromObject(result);
			logger.info("sendTmpelateMessage Result:" + jsonReturn);
			if ("0".equals(jsonReturn.getString("errcode")))
			{
				return true;
			}
			else
			{
				logger.info(jsonReturn.toString());
				return false;
			}
		}
		catch (Exception e)
		{
			logger.error(e);
			throw e;
		}
		finally
		{
			if (output != null)
			{
				try
				{
					output.close();
				}
				catch (Exception e)
				{
					logger.error(e);
				}
			}
			if (input != null)
			{
				try
				{
					input.close();
				}
				catch (Exception e)
				{
					logger.error(e);
				}
			}
		}
	}
	
	public static boolean sendTmpelateMessage(String accessToken, String openId, String templateId, String viewDetailUrl, JSONObject sendData) throws Exception
	{
		return sendTmpelateMessage(accessToken, openId, templateId, viewDetailUrl, null, sendData);
	}
	
	public static List<String> getUserOpenIdList(String accessToken, String nextOpenId)
	{
		logger.info("获取关注用户所有OpenId");
		String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + accessToken;
		if (!StringUtil.stringIsEmptyStr(nextOpenId))
		{
			url +=  "&next_openid=" + nextOpenId;
		}
		logger.info("访问的地址为：" + url);
		
		List<String> openIdList = new ArrayList<String>();
		InputStream input = null;
		try
		{
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();

			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			input = http.getInputStream();

			String message = new String(InputStreamUtil.inputStreamToBytes(input), "UTF-8");
			logger.info("getUserList Message:" + message);
			JSONObject json = JSONObject.fromObject(message);
			
			JSONObject jsonOpenIdList = JSONObjectUtil.getJSONObjectValue(json, "data");
			if (jsonOpenIdList != null)
			{
				JSONArray arrayOpenIdList = JSONObjectUtil.getJSONArrayValue(jsonOpenIdList, "openid");
				for (int i = 0; i < arrayOpenIdList.size(); i++)
				{
					openIdList.add(arrayOpenIdList.getString(i));
				}
			}
			String jsonNextOpenId = JSONObjectUtil.getJSONStringValue(json, "next_openid");
			if (!StringUtil.stringIsEmptyStr(jsonNextOpenId))
			{
				openIdList.addAll(getUserOpenIdList(accessToken, jsonNextOpenId));
			}
		}
		catch (Exception e)
		{
			logger.error(e);
			e.printStackTrace();
		}
		finally
		{
			try
			{
				input.close();
			}
			catch (Exception e)
			{
			}
		}
		return openIdList;
	}
	
	public static List<JSONObject> getUserListInfo(String accessToken, List<String> openIdList)
	{
		logger.info("获取所有关注用户 的列表:" + openIdList.size());
		if (openIdList.size() == 0)
		{
			return new ArrayList<JSONObject>();
		}
		int getCountEvery = 100;
		
		List<JSONObject> userList = new ArrayList<JSONObject>();
		if (openIdList.size() > getCountEvery)
		{
			List<String> queryOpenIdList = new ArrayList<String>();
			for (String openId : openIdList)
			{
				queryOpenIdList.add(openId);
				if (queryOpenIdList.size() == getCountEvery)
				{
					userList.addAll(getUserListInfo(accessToken, queryOpenIdList));
					queryOpenIdList.clear();
				}
			}
			
			if (queryOpenIdList.size() > 0)
			{
				userList.addAll(getUserListInfo(accessToken, queryOpenIdList));
			}
		}
		else
		{
			JSONObject jsonUserList = new JSONObject();
			JSONArray userListArray = new JSONArray();
			for (String openId : openIdList)
			{
				JSONObject jsonUser = new JSONObject();
				jsonUser.put("openid", openId);
//				jsonUser.put("lang", "zh-CN");
				userListArray.add(jsonUser);
			}
			jsonUserList.put("user_list", userListArray);
			
			OutputStream output = null;
			InputStream input = null;
			try
			{
				URL url = new URL("https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=" + accessToken);
				HttpURLConnection http = (HttpURLConnection) url.openConnection();
				http.setRequestMethod("POST");
				http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				http.setDoOutput(true);
				http.setDoInput(true);
				System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
				System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
				http.connect();
				output = http.getOutputStream();
				output.write(jsonUserList.toString().getBytes("UTF-8"));// 传入参数
				output.flush();
				
				input = http.getInputStream();
				
				String result = new String(InputStreamUtil.inputStreamToBytes(input), "UTF-8");
				logger.info("getListUserInfo Message:" + result);
				
				JSONObject jsonReturn = JSONObject.fromObject(result);
				JSONArray arrayUserList = JSONObjectUtil.getJSONArrayValue(jsonReturn, "user_info_list");
				
				for (int i = 0; i < arrayUserList.size(); i++)
				{
					JSONObject jsonUser = arrayUserList.getJSONObject(i);
					userList.add(jsonUser);
				}
			}
			catch (Exception e)
			{
				logger.error(e);
			}
			finally
			{
				if (output != null)
				{
					try
					{
						output.close();
					}
					catch (Exception e)
					{
						logger.error(e);
					}
				}
				if (input != null)
				{
					try
					{
						input.close();
					}
					catch (Exception e)
					{
						logger.error(e);
					}
				}
			}
		}
		return userList;
	}
	
	public static JSONObject getMenu(String accessToken) throws Exception
	{
		String result = postToUrl("https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + accessToken, null);
		JSONObject jsonReturn = JSONObject.fromObject(result);
		return jsonReturn;
	}
	
	public static JSONObject deleteMenu(String accessToken) throws Exception
	{
		String result = postToUrl("https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + accessToken, null);
		JSONObject jsonReturn = JSONObject.fromObject(result);
		return jsonReturn;
		
	}
	
	public static JSONObject createMenu(String accessToken, String menu) throws Exception
	{
		String result = postToUrl("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken, menu);
		JSONObject jsonReturn = JSONObject.fromObject(result);
		return jsonReturn;
	}
	
	public static JSONObject createCustomerMenu(String accessToken, String menu) throws Exception
	{
		String result = postToUrl("https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=" + accessToken, menu);
		JSONObject jsonReturn = JSONObject.fromObject(result);
		return jsonReturn;
	}

	public static JSONObject createGroup(String accessToken, String name) throws Exception
	{
		JSONObject jsonGroup = new JSONObject();
		JSONObject jsonName = new JSONObject();
		jsonName.put("name", name);
		jsonGroup.put("group", jsonName);

		String result = postToUrl("https://api.weixin.qq.com/cgi-bin/groups/create?access_token=" + accessToken, jsonGroup.toString());
		return JSONObject.fromObject(result);
	}
	
	public static JSONObject deleteGroup(String accessToken, String groupId) throws Exception
	{
		JSONObject jsonGroup = new JSONObject();
		JSONObject jsonName = new JSONObject();
		jsonName.put("id", groupId);
		jsonGroup.put("group", jsonName);

		String result = postToUrl("https://api.weixin.qq.com/cgi-bin/groups/delete?access_token=" + accessToken, jsonGroup.toString());
		return JSONObject.fromObject(result);
	}
	
	public static JSONObject getGroupList(String accessToken) throws Exception
	{
		String result = postToUrl("https://api.weixin.qq.com/cgi-bin/groups/get?access_token=" + accessToken, null);
		return JSONObject.fromObject(result);
	}
	
	public static JSONObject moveUserToGroup(String accessToken, String openId, String groupId) throws Exception
	{
		JSONObject jsonGroup = new JSONObject();
		jsonGroup.put("openid", openId);
		jsonGroup.put("to_groupid", groupId);

		String result = postToUrl("https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=" + accessToken, jsonGroup.toString());
		return JSONObject.fromObject(result);
	}
	
	public static boolean moveUserListToGroup(String accessToken, List<String> openIdList, String groupId) throws Exception
	{
		if (openIdList.size() > 50)
		{
			List<String> moveList = new ArrayList<String>();
			for (int i = 0; i < openIdList.size(); i++)
			{
				moveList.add(openIdList.get(i));
				
				if (moveList.size() == 50)
				{
					if (moveUserListToGroup(accessToken, moveList, groupId))
					{
						moveList = new ArrayList<String>();
					}
				}
			}
			return true;
		}
		else
		{
			JSONObject jsonGroup = new JSONObject();
			JSONArray jsonList = new JSONArray();
			
			for (String openId : openIdList)
			{
				jsonList.add(openId);
			}
			jsonGroup.put("openid_list", jsonList);
			jsonGroup.put("to_groupid", groupId);
			
			String result = postToUrl("https://api.weixin.qq.com/cgi-bin/groups/members/batchupdate?access_token=" + accessToken, jsonGroup.toString());
			JSONObject jsonReturn = JSONObject.fromObject(result);
			
			if ("0".equals(jsonReturn.getString("errcode")))
			{
				return true;
			}
			else
			{
				throw new Exception("移动失败，失败代码：" + jsonReturn.getInt("errcode"));
			}
		}
	}
	
	private static String postToUrl(String strUrl, String outputContent) throws Exception
	{
		OutputStream output = null;
		InputStream input = null;
		try
		{
			URL url = new URL(strUrl);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			
			if (!StringUtil.stringIsEmptyStr(outputContent))
			{
				output = http.getOutputStream();
				output.write(outputContent.toString().getBytes("UTF-8"));// 传入参数
				output.flush();
			}
			
			input = http.getInputStream();
			String result = new String(InputStreamUtil.inputStreamToBytes(input), "UTF-8");
			return result;
		}
		catch (Exception e)
		{
			logger.error(e);
			throw e;
		}
		finally
		{
			if (output != null)
			{
				try
				{
					output.close();
				}
				catch (Exception e)
				{
					logger.error(e);
				}
			}
			if (input != null)
			{
				try
				{
					input.close();
				}
				catch (Exception e)
				{
					logger.error(e);
				}
			}
		}
	}
	
	/**
	 * 创建永久有效带参数二维码
	 * @param scene_id
	 * @return
	 */
	public static JSONObject createLimitParameterQRCode(String accessToken, String scene_id) throws Exception
	{
		JSONObject jsonSceneInfo = new JSONObject();
		jsonSceneInfo.put("scene_str", scene_id);
		
		JSONObject jsonActionInfo = new JSONObject();
		jsonActionInfo.put("scene", jsonSceneInfo);
		
		JSONObject jsonSendContent = new JSONObject();
		jsonSendContent.put("action_name", "QR_LIMIT_STR_SCENE");
		jsonSendContent.put("action_info", jsonActionInfo);
		
		String result = postToUrl("https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + accessToken, jsonSendContent.toString());
		JSONObject jsonReturn = JSONObject.fromObject(result);
		return jsonReturn;
	}
	
	/**
	 * 
	 * @param accessToken
	 * @param logoUrl LOGO
	 * @param brandName 公司名称
	 * @param title 标题
	 * @param color 颜色
	 */
	public static JSONObject createDataCard(String accessToken, String logoUrl, String source, String brandName, String title, String color) throws Exception
	{
		//码型：CODE_TYPE_NONE
		//使用场景入口 center_title、center_sub_title、center_url
		
		//logo
		String url = "https://api.weixin.qq.com/card/create?access_token=" + accessToken;
		
		
		JSONObject jsonGift = new JSONObject();
		
		JSONObject jsonGiftBaseInfo = new JSONObject();
		jsonGiftBaseInfo.put("logo_url", logoUrl);
		jsonGiftBaseInfo.put("code_type", "CODE_TYPE_NONE");
		jsonGiftBaseInfo.put("brand_name", brandName);
		jsonGiftBaseInfo.put("title", title);
//		jsonGiftBaseInfo.put("sub_title", "");
		jsonGiftBaseInfo.put("color", color);
		jsonGiftBaseInfo.put("service_phone", "");
		jsonGiftBaseInfo.put("center_title", "立即充值");
		jsonGiftBaseInfo.put("center_url", "http://test.credit114.cn/netflow/useCard");
		
		if (source != null && source.trim().length() > 0)
		{
			jsonGiftBaseInfo.put("source", source);
		}
		jsonGiftBaseInfo.put("notice", "直接可以充值到手机");
		jsonGiftBaseInfo.put("description", "不可能叠加使用！");
		jsonGiftBaseInfo.put("use_custom_code", true);
		jsonGiftBaseInfo.put("can_share", false);
		
		JSONObject jsonGiftBaseInfoSku = new JSONObject();
		jsonGiftBaseInfoSku.put("quantity", 0);
		jsonGiftBaseInfo.put("sku", jsonGiftBaseInfoSku);

		JSONObject jsonGiftBaseInfoDateInfo = new JSONObject();
		jsonGiftBaseInfoDateInfo.put("type", "DATE_TYPE_FIX_TERM");
		jsonGiftBaseInfoDateInfo.put("fixed_term", 90);
		jsonGiftBaseInfoDateInfo.put("fixed_begin_term", 0);
		jsonGiftBaseInfo.put("date_info", jsonGiftBaseInfoDateInfo);
		

//		JSONObject jsonGiftAdvancedInfo = new JSONObject();
//		JSONObject jsonGiftAdvancedInfoAbstract = new JSONObject();
//		jsonGiftAdvancedInfoAbstract.put("abstract", "兑换券详情");
//		jsonGiftAdvancedInfo.put("abstract", jsonGiftAdvancedInfoAbstract);

		jsonGift.put("base_info", jsonGiftBaseInfo);
//		jsonGift.put("advanced_info", jsonGiftAdvancedInfo);
		jsonGift.put("gift", "可直充全网流量");
		

		JSONObject jsonCard = new JSONObject();
		jsonCard.put("card_type", "GIFT");
		jsonCard.put("gift", jsonGift);
		

		JSONObject jsonData = new JSONObject();
		jsonData.put("card", jsonCard);
		
		String result = postToUrl(url, jsonData.toString());
		JSONObject jsonReturn = JSONObject.fromObject(result);
		return jsonReturn;
	}
	
	public static JSONObject modifyStock(String accessToken, String cardId, int increase, int reduce) throws Exception
	{
		String url = "https://api.weixin.qq.com/card/modifystock?access_token=" + accessToken;
		
		JSONObject jsonData = new JSONObject();
		jsonData.put("card_id", cardId);
		jsonData.put("increase_stock_value", increase);
		jsonData.put("reduce_stock_value", reduce);
		String result = postToUrl(url, jsonData.toString());
		JSONObject jsonReturn = JSONObject.fromObject(result);
		return jsonReturn;
	}
	/**
	 * 销卡
	 * @param accessToken
	 * @param cardId
	 * @param cardCode
	 * @return
	 * @throws Exception
	 */
	public static JSONObject consumeCard(String accessToken, String cardId, String cardCode) throws Exception
	{
		String url = "https://api.weixin.qq.com/card/code/consume?access_token=" + accessToken;
		
		JSONObject jsonData = new JSONObject();
		jsonData.put("code", cardCode);
		if (!StringUtil.stringIsEmptyStr(cardId))
		{
			jsonData.put("card_id", cardId);
		}
		String result = postToUrl(url, jsonData.toString());
		JSONObject jsonReturn = JSONObject.fromObject(result);
		return jsonReturn;
	}
	
	public static JSONObject decryptCardCode(String accessToken, String encryptCode) throws Exception
	{
		String url = "https://api.weixin.qq.com/card/code/decrypt?access_token=" + accessToken;
		
		JSONObject jsonData = new JSONObject();
		jsonData.put("encrypt_code", URLDecoder.decode(encryptCode, "UTF-8"));
		String result = postToUrl(url, jsonData.toString());
		JSONObject jsonReturn = JSONObject.fromObject(result);
		return jsonReturn;
	}
	
	public static JSONObject importCardCode(String accessToken, String cardId, List<String> cardCode) throws Exception
	{
		String url = "http://api.weixin.qq.com/card/code/deposit?access_token=" + accessToken;
		
		JSONObject jsonData = new JSONObject();
		JSONArray jsonCodeList = new JSONArray();
		for (String code : cardCode)
		{
			jsonCodeList.add(code);
		}
		jsonData.put("card_id", cardId);
		jsonData.put("code", jsonCodeList);
		String result = postToUrl(url, jsonData.toString());
		JSONObject jsonReturn = JSONObject.fromObject(result);
		return jsonReturn;
	}
	
	public static JSONObject checkCardCode(String accessToken, String cardId, List<String> cardCode) throws Exception
	{
		String url = "http://api.weixin.qq.com/card/code/checkcode?access_token=" + accessToken;
		
		JSONObject jsonData = new JSONObject();
		JSONArray jsonCodeList = new JSONArray();
		for (String code : cardCode)
		{
			jsonCodeList.add(code);
		}
		jsonData.put("card_id", cardId);
		jsonData.put("code", jsonCodeList);
		System.out.println(jsonData.toString());
		String result = postToUrl(url, jsonData.toString());
		JSONObject jsonReturn = JSONObject.fromObject(result);
		return jsonReturn;
	}

}
