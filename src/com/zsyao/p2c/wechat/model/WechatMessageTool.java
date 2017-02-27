package com.zsyao.p2c.wechat.model;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.zsyao.p2c.wechat.model.common.ImageMessage;
import com.zsyao.p2c.wechat.model.common.ShortVideoMessage;
import com.zsyao.p2c.wechat.model.common.TextMessage;
import com.zsyao.p2c.wechat.model.common.VideoMessage;
import com.zsyao.p2c.wechat.model.common.VoiceMessage;
import com.zsyao.p2c.wechat.model.even.AbsEventMessage;
import com.zsyao.p2c.wechat.model.even.LocationEventMessage;
import com.zsyao.p2c.wechat.model.even.ScanEventMessage;
import com.zsyao.p2c.wechat.model.even.SubscribeEventMessage;
import com.zsyao.p2c.wechat.model.even.UnsubscribeEventMessage;

@SuppressWarnings("unchecked")
public class WechatMessageTool
{
	private static Logger logger = Logger.getLogger(WechatMessageTool.class);
	
	/**
	 * 
	 * @param <T>
	 * @param xml
	 * @return
	 */
	public static <T>Message parserFromXML(String xml)
	{
		try
		{
			SAXReader reader = new SAXReader();
			Document document = reader.read(new ByteArrayInputStream(xml.getBytes("UTF-8")));
			Element root = document.getRootElement();
			List<Element> elementList = (List<Element>)root.elements();
			
			String messageType = "";
			String evenType = "";
			
			for (Element e : elementList)
			{
				if (e.getName().equalsIgnoreCase("MsgType"))
				{
					messageType = e.getText();
				}
				else if (e.getName().equalsIgnoreCase("Event"))
				{
					evenType = e.getText();
				}
			}

			Message baseMessage = null;
			if (Message.MESSAGE_TYPE_TEXT.equalsIgnoreCase(messageType))
			{
				baseMessage = new TextMessage();
			}
			else if (Message.MESSAGE_TYPE_IMAGE.equalsIgnoreCase(messageType))
			{
				baseMessage = new ImageMessage();
			}
			else if (Message.MESSAGE_TYPE_VOICE.equalsIgnoreCase(messageType))
			{
				baseMessage = new VoiceMessage();
			}
			else if (Message.MESSAGE_TYPE_VIDEO.equalsIgnoreCase(messageType))
			{
				baseMessage = new VideoMessage();
			}
			else if (Message.MESSAGE_TYPE_SHORT_VIDEO.equalsIgnoreCase(messageType))
			{
				baseMessage = new ShortVideoMessage();
			}
			else if (Message.MESSAGE_TYPE_LOCATION.equalsIgnoreCase(messageType))
			{
				baseMessage = new ImageMessage();
			}
			else if (Message.MESSAGE_TYPE_LINK.equalsIgnoreCase(messageType))
			{
				baseMessage = new ImageMessage();
			}
			else if (Message.MESSAGE_TYPE_EVENT.equalsIgnoreCase(messageType))
			{
				if (AbsEventMessage.EVENT_TYPE_OF_SUBSCRIBE.equals(evenType))
				{
					baseMessage = new SubscribeEventMessage();
				}
				else if (AbsEventMessage.EVENT_TYPE_OF_UNSUBSCRIBE.equals(evenType))
				{
					baseMessage = new UnsubscribeEventMessage();
				}
				else if (AbsEventMessage.EVENT_TYPE_OF_SCAN.equals(evenType))
				{
					baseMessage = new ScanEventMessage();
				}
				else if (AbsEventMessage.EVENT_TYPE_OF_LOCATION.equals(evenType))
				{
					baseMessage = new LocationEventMessage();
				}
				else
				{
					return null;
				}
			}
			else
			{
				return null;
			}
			
			Method[] methodList = baseMessage.getClass().getMethods();
			for (Element e : elementList)
			{
				for (Method method : methodList)
				{
					String methodName = method.getName();
					if (methodName.startsWith("set"))
					{
						methodName = methodName.substring(3);
						
						if (methodName.equalsIgnoreCase(e.getName()))
						{
							method.invoke(baseMessage, e.getText());
						}
					}
				}
			}
			return baseMessage;
		}
		catch(Exception e)
		{
			logger.error("xml:" + xml);
			logger.error(e);
			e.printStackTrace();
		}
		return null;
	}
}
