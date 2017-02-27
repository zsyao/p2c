package com.zsyao.util;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

public class MD5
{
	private final static char[] hexDigits =
	{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
			'E', 'F' };

	private static String bytesToHex(byte[] bytes)
	{
		StringBuffer sb = new StringBuffer();
		int t;
		for (int i = 0; i < 16; i++)
		{
			t = bytes[i];
			if (t < 0)
				t += 256;
			sb.append(hexDigits[(t >>> 4)]);
			sb.append(hexDigits[(t % 16)]);
		}
		return sb.toString();
	}
	
	public static void main_(String[] args) throws Exception
	{
//		String enterpriseId = code(UUID.randomUUID().toString(), 16).toLowerCase();
//		String activityCode = "OpenAccount";
//		String joinTime = System.currentTimeMillis() + "";
//		String noncestr = code(UUID.randomUUID().toString() + System.currentTimeMillis(), 16).toLowerCase();
//		String customerId = "123";
//		String secret = code(UUID.randomUUID().toString() + System.currentTimeMillis(), 32).toLowerCase();
//		
//		String sign = MD5.md5(enterpriseId + activityCode + joinTime + noncestr + customerId + secret).toLowerCase();
//		
//		String url = "http://123.57.58.67/enterprise/interface/userJoinActivity?enterpriseId=" + enterpriseId
//				+ "&activityCode=" + activityCode
//				+ "&joinTime=" + joinTime
//				+ "&noncestr=" + noncestr
//				+ "&customerId=" + customerId
//				+ "&sign=" + sign;
//		
//		System.out.println(url);
		
//		String url = "http://101.201.43.4:8083/FCIP/community/wechatCardToCoin";
//		
//		PostMethod postMethod = new PostMethod(url);
//		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
//		
//		DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'") ;
//		java.util.Calendar cal = java.util.Calendar.getInstance(); 
//		int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET); 
//		int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);  
//		//4、从本地时间里扣除这些差量，即可以取得UTC时间：  
//		cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset)); 
//		
//		
//		String customerKey = "9872ed9fc22fc182d371c3e9ed316094";
//		String customerSecret = "87d538ef1c1db71603e60f278446c86470162380";
//		
//		String nonce = UUID.randomUUID().toString();
//		String created = format.format(cal.getTime()).toString();
//		String passwordDigest = new sun.misc.BASE64Encoder().encode(StringEncrypt.Encrypt(nonce + created + customerSecret, ""));
//		
//		postMethod.addParameter("Authorization", "WSSE");
//		postMethod.addParameter("Realm", "UFP");
//		postMethod.addParameter("Profile", "UsernameToken");
//		postMethod.addParameter("Type", "CustomerKey");
//
//		postMethod.addParameter("X-WSSE", "UsernameToken");
//		postMethod.addParameter("Username", customerKey);
//		postMethod.addParameter("PasswordDigest", passwordDigest);
//		postMethod.addParameter("Nonce", nonce);
//		postMethod.addParameter("Created", created);
//		
//		postMethod.addParameter("openId", "oUZWKs4q7QQ_PBRMe-saltC-rh14");
//		postMethod.addParameter("productID", "49");
//		postMethod.addParameter("deductFlag", "1");
//		postMethod.addParameter("mobile", "18642045677");
//		
//		HttpClientUtil hcu = new HttpClientUtil();
//		
//		JSONObject jasonObject = hcu.doPost(url, postMethod);
//		System.out.println(jasonObject.toString());
		
//		BufferedReader br = new BufferedReader(new FileReader("e:/需要发送的.txt"));
//		
//		FileOutputStream fo = new FileOutputStream("e:/需要发送的_result.txt");
//		
//		StringBuffer sb = new StringBuffer();
//		try
//		{
//			String line = null;
//			while ((line = br.readLine()) != null)
//			{
//				try
//				{
//					UserInfo user = WechatUtil.getUserInfo(line.trim());
//					if (user != null 
//							&& !StringUtil.stringIsEmptyStr(user.getSubscribe())
//							&& "1".equals(user.getSubscribe()))
//					{
//						System.out.println(line + "\t关注");
//						sb.append(line).append("\t").append("关注").append("\r\n");
//					}
//					else
//					{
//						System.out.println(line + "\t未关注");
//						sb.append(line).append("\t").append("未关注").append("\r\n");
//					}
//				}
//				catch(Exception e)
//				{
//					e.printStackTrace();
//				}
//			}
//			fo.write(sb.toString().getBytes());
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		
//		fo.flush();
//		fo.close();
//		br.close();
	}

	public static void main3(String[] args) throws Exception
	{
		String sign = "c7de7de2299f81f7cc022a44d7b68855";
		String noteStr = UUID.randomUUID().toString();
		String timeStame = System.currentTimeMillis() + "";
		System.out.print("http://card.orientalwisdom.com/game/game/index?nonceStr=" + noteStr);
		System.out.print("&timestamp=" + timeStame);
		System.out.print("&sign=" + MD5.md5(timeStame + noteStr + sign));
		System.out.println();
//		JSONObject sendData = new JSONObject();
//		
//		JSONObject jsonFirst = new JSONObject();
//		JSONObject jsonKeyword1 = new JSONObject();
//		JSONObject jsonKeyword2 = new JSONObject();
//		JSONObject jsonKeyword3 = new JSONObject();
//		JSONObject jsonRemark = new JSONObject();
//		
//		jsonFirst.put("value", "您好，您的好友邀您开户领流量。");
//		jsonKeyword1.put("value", "上海证券开户送流量");
//		jsonKeyword2.put("value", "1.5G");
//		jsonKeyword3.put("value", "#{当前日期}");
//		jsonRemark.put("value", "接受好友邀请并完成上海证券开户，可获1.5G流量。超值福利，还等什么，点击详情即刻参与，还有更多豪礼等你拿。");
//		
//		sendData.put("first", jsonFirst);
//		sendData.put("keyword1", jsonKeyword1);
//		sendData.put("keyword2", jsonKeyword2);
//		sendData.put("keyword3", jsonKeyword3);
//		sendData.put("remark", jsonRemark);
//		
//		System.out.println(sendData.toString());
		
//		System.out.println(code("ov4rntwLgBD4kK5hVbmLGy2wz0q8", 16));
		
//		System.out.println(code(UUID.randomUUID().toString() + System.currentTimeMillis(), 32).toLowerCase());
//		System.out.println("https://open.weixin.qq.com/connect/oauth2/authorize?appid="
//				+ "wx2e839a1462281b1e" + "&redirect_uri="
//				+ URLEncoder.encode("http://test.credit114.cn/netflow/user/testCard", "UTF-8") + "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");
	}
	
//	public static void main(String[] args) throws Exception
//	{
//		//"applicationContext.xml"
//		BeanFactory factory = new XmlBeanFactory(new FileSystemResource(URLDecoder.decode(MD5.class.getResource("/applicationContext.xml").getPath(), "UTF-8")));
//		ISMSService smsService = (ISMSService)factory.getBean("smsService");
//		smsService.sendSMS("13545010147", "王晶同学，您需在今明两天还款150.96元，可支付宝转账，账号：1109 0852 8010 102，还款银行：招商银行，账户名：北京中天嘉华信息技术有限公司。如有问题，请拨打电话4009199995");
//	}
	
	public static void main(String[] args) throws Exception
	{
		
//		String[] officeList = {"北京海泰职场"};//{"北京海泰职场", "沈阳浑南职场", "武汉职场", "上海职场", "深圳职场", "广州职场", "昆山职场"};
//		for (int i = 0; i < officeList.length; i++)
//		{
//			String url = "http://test.credit114.cn/axin/job/applyJob.jspx?officeId=" + (i + 1);
//			byte[] imgContent = QRCodeUtil.createQRCord(url, 250, 250);
//			
//			FileOutputStream fo = new FileOutputStream("e:/" + officeList[i] + "-中天嘉华.png");
//			fo.write(imgContent);
//			fo.flush();
//			fo.close();
//		}
		
//		System.out.println(DateUtil.dateToString(new Date(System.currentTimeMillis() + 24 * 3600 * 1000), DateUtil.FORMAT_DATE));
		
//		System.out.println(code("credit114", 32));
		
//		System.out.println(WechatUtil.getGroupList().toString());
//		
//		System.out.println(SHA1.enCoding("aaaaaa"));
//		System.out.println(SHA1.enCoding("aaaaaa").length());
		
//		String id = MD5.code("" + System.currentTimeMillis() + "" +  + new Random().nextDouble(), 16).toLowerCase();
//		String pwd = MD5.code(id + System.currentTimeMillis() + new Random().nextDouble(), 32).toLowerCase();
//		System.out.println(id);
		
//		System.out.println(WechatUtil.getAccessToken());
		
//		System.out.println(MD5.code("" + System.nanoTime() + "" + new Random().nextDouble(), 16).toLowerCase());
//		System.out.println(MD5.code("" + System.nanoTime() + "" + new Random().nextDouble(), 32).toLowerCase());
//		
//		long l1 = System.currentTimeMillis();
//		
//		String eid = "bba58cb9f1900fd7";
//		String se = "ad98445b7c07073cd6e33d03a6a23d63";
//		
//		String secret = MD5.md5(eid + "" + l1 + "" + se);
//		
//		System.out.println(l1);
//		System.out.println();
//		
//		System.out.println("http://test.credit114.cn/netflow/interface/getAccessToken?enterpriseid=" + eid + "&timestamp="
//				+ l1 + "&secret=" + secret);
		
		
		
		StringBuffer sb = new StringBuffer();
		try
		{
			BufferedReader br = new BufferedReader(new FileReader("E:\\工作交接资料\\信用公社\\中天嘉华测试菜单_2.txt"));
			String line = null;
			
			while ((line = br.readLine()) != null)
			{
				sb.append(line);
			}
			br.close();
		}
		catch(Exception e)
		{
		}
		System.out.println(sb.toString());
//		System.out.println(WechatUtil.deleteMenu());
//		System.out.println(WechatUtil.createMenu(sb.toString()));
		//System.out.println(WechatUtil.createCustomerMenu("{\"button\": [{\"type\": \"view\", \"name\": \"软帝分期\", \"url\": \"http://test.credit114.cn/axin/loan/loanRequest.jspx\", \"sub_button\": [ ]}, {\"type\": \"view\", \"name\": \"信用资料\", \"url\": \"http://test.credit114.cn/axin/loan/creditInfo.jspx\", \"sub_button\": [ ]},{\"name\": \"我的\", \"sub_button\": [{\"type\": \"view\", \"name\": \"关于我们\", \"url\": \"http://www.credit114.cn/axin/loan/softeem/aboutUs.jspx\", \"sub_button\": [ ]},{\"type\": \"view\", \"name\": \"常见问题\", \"url\": \"http://www.credit114.cn/axin/loan/softeem/questionsAnswers.jspx\", \"sub_button\": [ ]},{\"type\": \"view\", \"name\": \"我要吐槽\", \"url\": \"http://www.credit114.cn/axin/loan/softeem/feedback.jspx\", \"sub_button\": [ ]}]}        ],\"matchrule\":{\"group_id\":\"100\"}}"));
		//System.out.println(WechatUtil.createCustomerMenu("{\"button\": [{\"type\": \"view\", \"name\": \"东软分期\", \"url\": \"http://test.credit114.cn/axin/loan/loanRequest.jspx\", \"sub_button\": [ ]}, {\"type\": \"view\", \"name\": \"信用资料\", \"url\": \"http://test.credit114.cn/axin/loan/creditInfo.jspx\", \"sub_button\": [ ]},{\"name\": \"我的\", \"sub_button\": [{\"type\": \"view\", \"name\": \"关于我们\", \"url\": \"http://www.credit114.cn/axin/loan/softeem/aboutUs.jspx\", \"sub_button\": [ ]},{\"type\": \"view\", \"name\": \"常见问题\", \"url\": \"http://www.credit114.cn/axin/loan/softeem/questionsAnswers.jspx\", \"sub_button\": [ ]},{\"type\": \"view\", \"name\": \"我要吐槽\", \"url\": \"http://www.credit114.cn/axin/loan/softeem/feedback.jspx\", \"sub_button\": [ ]}]}        ],\"matchrule\":{\"group_id\":\"101\"}}"));
		
		
//		System.out.println(WechatUtil.createLimitParameterQRCode(100));
//		System.out.println(WechatUtil.createLimitParameterQRCode(101));
		
//		byte[] imgContent = QRCodeUtil.createQRCode("http://weixin.qq.com/q/O0w1Q1Ll6FmRSjWgXWAj");
//		FileOutputStream fo = new FileOutputStream("e:/中天嘉华-软帝-关注二维码.png");
//		fo.write(imgContent);
//		fo.flush();
//		fo.close();
		//System.out.println(WechatUtil.createGroup("东软睿道-沈阳"));
		
	}
	
	@SuppressWarnings("unused")
	private static void test1() throws Exception
	{

//		System.out.println(DateUtil.stringToDate("2015-08-21 13:54:32.123", DateUtil.FORMAT_TIMESTAMP));
//		System.out.println(LBSUtil.getLBSAddressInfo("116.3202", "40.08627"));
		
		
//		System.out.println(MD5.code("ov4rnt-1A8W3ciDeyIkVpKuvXnEY", 16));
		
		FileOutputStream fo = new FileOutputStream("g:/result.xml");
		
		StringBuffer sb1 = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		
		try
		{
			fo.write(("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n").getBytes());
			fo.write(("<graphml xmlns=\"http://graphml.graphdrawing.org/xmlns\">\r\n").getBytes());
			fo.write(("<graph edgedefault=\"undirected\">\r\n").getBytes());
			
			fo.write(("<key id=\"name\" for=\"node\" attr.name=\"name\" attr.type=\"string\"/>\r\n").getBytes());
			fo.write(("<key id=\"headImage\" for=\"node\" attr.name=\"headImage\" attr.type=\"string\"/>\r\n").getBytes());
			
			
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://rdsy4h8mus8ryn961kn3.mysql.rds.aliyuncs.com:3306/axin?user=ztjhdb&password=sinowel_credit114";
			Connection conn = DriverManager.getConnection(url);
			
			Statement stmt = conn.createStatement();
			Statement stmt2 = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("Select * From (" +
					"Select AccessOpenId, convert(nickName, char(100)) nickName, headImage from userfavoraccess " +
					"union SELECT SourceOpenId, convert(nickName, char(100)) nickName, headImage from userfavor ) t " +
					"Order by AccessOpenId");
			
			while (rs.next())
			{
				String openId = rs.getString("AccessOpenId");
				String nickName = rs.getString("nickName");
				String headImg = rs.getString("headImage");
				
//				fo.write(("<node id=\"" + openId + "\">\r\n").getBytes());
//				fo.write(("\t<data key=\"name\">" + (nickName == null ? "未知" : nickName) + "</data>\r\n").getBytes());
//				fo.write(("\t<data key=\"headImg\">" + (headImg == null ? "未知" : headImg) + "</data>\r\n").getBytes());
//				fo.write(("</node>").getBytes());
				sb1.append(("<node id=\"" + openId + "\">\r\n"));
				sb1.append(("\t<data key=\"name\">" + (nickName == null ? "未知" : nickName) + "</data>\r\n"));
				sb1.append(("\t<data key=\"headImg\">" + (headImg == null ? "未知" : headImg) + "</data>\r\n"));
				sb1.append(("</node>"));
				
				ResultSet rs2 = stmt2.executeQuery("select AccessOpenId from userfavoraccess Where SourceOpenId = '" + openId + "'");
				while (rs2.next())
				{
					sb2.append(("<edge source=\"" + openId + "\" target=\"" + rs2.getString("AccessOpenId") + "\"></edge>\r\n"));
				}
				rs2.close();
			}
			
			fo.write(sb1.toString().getBytes());
			fo.write(sb2.toString().getBytes());
			fo.flush();
			fo.close();
			
			stmt2.close();
			rs.close();
			stmt.close();
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static String toQueryString(Map<?, ?> data)
			throws UnsupportedEncodingException
	{
		StringBuffer queryString = new StringBuffer();
		for (Entry<?, ?> pair : data.entrySet())
		{
			queryString.append(pair.getKey() + "=");
			queryString.append(URLEncoder.encode((String) pair.getValue(),
					"UTF-8")
					+ "&");
		}
		if (queryString.length() > 0)
		{
			queryString.deleteCharAt(queryString.length() - 1);
		}
		return queryString.toString();
	}

	// 来自stackoverflow的MD5计算方法，调用了MessageDigest库函数，并把byte数组结果转换成16进制
	public static String MD5(String md5)
	{
		try
		{
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i)
			{
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		}
		catch (java.security.NoSuchAlgorithmException e)
		{
		}
		return null;
	}

	public static String md5(String input)
	{
		return code(input, 32);
	}

	public static String code(String input, int bit)
	{
		return code(input, "utf-8", bit);
	}
	
	public static String code(String input, String code, int bit)
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance(System.getProperty("MD5.algorithm", "MD5"));
			if (bit == 16)
			{
				return bytesToHex(md.digest(input.getBytes(code))).substring(8, 24);
			}
			return bytesToHex(md.digest(input.getBytes(code)));
		}
		catch(UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static String md5_3(String b) throws Exception
	{
		MessageDigest md = MessageDigest.getInstance(System.getProperty("MD5.algorithm", "MD5"));
		byte[] a = md.digest(b.getBytes());
		a = md.digest(a);
		a = md.digest(a);

		return bytesToHex(a);
	}
}
