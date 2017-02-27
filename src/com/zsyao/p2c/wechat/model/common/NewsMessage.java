package com.zsyao.p2c.wechat.model.common;

import java.util.ArrayList;
import java.util.List;

public class NewsMessage extends AbsBaseMessage
{
	private List<Article> articles = new ArrayList<Article>();

	public NewsMessage()
	{
		super(MESSAGE_TYPE_NEWS);
	}
	
	public List<Article> getArticles()
	{
		return articles;
	}

	public void setArticles(List<Article> articles)
	{
		this.articles = articles;
	}
	
	public void addArticle(String title, String description, String picUrl, String url)
	{
		articles.add(new Article(title, description, picUrl, url));
	}
	
	public class Article
	{
		protected String title;
		protected String description;
		protected String picUrl;
		protected String url;
		
		public Article()
		{
			
		}
		public Article(String title, String description, String picUrl, String url)
		{
			this.title = title;
			this.description = description;
			this.picUrl = picUrl;
			this.url = url;
		}
	}

	@Override
	public String toSendXML()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>")
			.append("<ToUserName><![CDATA[" + getToUserName() + "]]></ToUserName>")
			.append("<FromUserName><![CDATA[" + getFromUserName() + "]]></FromUserName>")
			.append("<CreateTime>" + getCreateTime() + "</CreateTime>")
			.append("<MsgType><![CDATA[" + getMsgType() + "]]></MsgType>")
			.append("<ArticleCount>" + articles.size() + "</ArticleCount>")
			.append("<Articles>");
		for (Article article : articles)
		{
			
			sb.append("</item>")
				.append("<Title><![CDATA[" + article.title + "]]></Title>")
				.append("<Description><![CDATA[" + article.description + "]]></Description>")
				.append("<PicUrl><![CDATA[" + article.picUrl + "]]></PicUrl>")
				.append("<Url><![CDATA[" + article.url + "]]></Url>")
				.append("</item>");
		}
		sb.append("</Articles>")
			.append( "</xml>");
		return sb.toString();
	}

	@Override
	public String toXML()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>")
			.append("<ToUserName><![CDATA[" + getToUserName() + "]]></ToUserName>")
			.append("<FromUserName><![CDATA[" + getFromUserName() + "]]></FromUserName>")
			.append("<CreateTime>" + getCreateTime() + "</CreateTime>")
			.append("<MsgType><![CDATA[" + getMsgType() + "]]></MsgType>")
			.append("<ArticleCount>" + articles.size() + "</ArticleCount>")
			.append("<Articles>");
		for (Article article : articles)
		{
			
			sb.append("</item>")
				.append("<Title><![CDATA[" + article.title + "]]></Title>")
				.append("<Description><![CDATA[" + article.description + "]]></Description>")
				.append("<PicUrl><![CDATA[" + article.picUrl + "]]></PicUrl>")
				.append("<Url><![CDATA[" + article.url + "]]></Url>")
				.append("</item>");
		}
		sb.append("</Articles>")
			.append( "</xml>");
		return sb.toString();
	}
}
