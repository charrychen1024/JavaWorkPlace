/*
 * @(#)SpiderService.java 8:44:39 PM
 * 
 * Copyright 2013 Lzj.Liu, Inc. All rights reserved. ForYou
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.you.service;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.jsoup.Jsoup;

import com.you.bean.PageData;
import com.you.constant.SystemConstant;
import com.you.dao.PageDataDao;
import com.you.util.DateUtils;
import com.you.util.HtmlParserUtils;
import com.you.util.HttpUtils;

/**
 * <p>Title: SpiderService.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2015/4</p>
 * <p>Company: Co.,Ltd</p>
 * @author cjr
 * @version 1.0
 */
public class SpiderService
{
	private static final Log loger = LogFactory.getLog(SpiderService.class);

	//网页地址
	private String webAddress;
	//存储目录
	private String directory;
	//线程数量
	private int threadNum;
	//爬行深度
	private int deepNum;
	//关键字
	private String keyWord;
	//编码
	private String charset;

	private ArrayList<String> arrUrls = new ArrayList<String>();//存储未处理URL
	private ArrayList<String> arrUrl = new ArrayList<String>();//存储已处理URL
	private Hashtable<String, Integer> deepUrls = new Hashtable<String, Integer>();//存储所有URL深度

	public SpiderService()
	{
		super();//调用父类
	}

	public SpiderService(String url)
	{
		this.webAddress = url;
	}

	//依次取出list中的元素，线程安全
	public synchronized String getAUrl()//从未处理队列中取出一个URL
	{
		if (null != arrUrls && !arrUrls.isEmpty())
		{
			String tmpAUrl = arrUrls.get(0);
			arrUrls.remove(0);
			return tmpAUrl;
		}
		return null;
	}
//listAllLinkFromUrl提取URL
	public List<String> listAllLinkFromUrl(String url)
	{
		List<String> list = new ArrayList<String>();
		if (StringUtils.isNotBlank(url))
		{
			String webDomain = this.getDomain();
			String htmlContent = HttpUtils.getHtmlContent(url, this.charset);
			if (htmlContent.indexOf(this.keyWord) != -1)//关键字
			{
				String fileName = Thread.currentThread().getId() + "-"
						+ System.currentTimeMillis()
						+ SystemConstant.EXTENDTION;
				String dir = this.directory + File.separatorChar + fileName;
				try
				{
					FileUtils.writeStringToFile(new File(dir), htmlContent,
							this.charset);//保存文件
					arrUrl.add(url);
				}
				catch (IOException e)
				{
					loger.error(e);
				}
			}
			NodeList nodeList = HtmlParserUtils.getNodeList(htmlContent, "a",
					this.charset);//获取页面所有URL
			String domain = SystemConstant.PROTOL + webDomain;
			if (null != nodeList && nodeList.size() > 0)
			{
				for (int i = 0; i < nodeList.size(); i++)
				{
					Node node = nodeList.elementAt(i);
					if (node instanceof LinkTag)
					{
						LinkTag tag = (LinkTag) node;
						String attr = tag.getAttribute("href");
						if (StringUtils.isNotBlank(attr))
						{
							if (!attr.startsWith("javascript")
									&& !attr.startsWith("mailto"))
							{
								if (attr.startsWith("/"))
									attr = domain + attr;
								//System.out.println(attr+"#####");
								list.add(attr.trim());
							}
						}
					}
				}
			}
		}
		return list;
	}

	private void setHtmlCharset(String url)//获取编码方式
	{
		Parser parser;
		try
		{
			parser = new Parser(url);
			String encode = parser.getEncoding();
			if (StringUtils.isNotBlank(encode))
				this.charset = encode;
		}
		catch (ParserException e)
		{
			loger.error(e);
		}
	}
	
	public void getWebByHomePage()//主页
	{
		//将URL存储到List中来处理
		arrUrls.add(webAddress);//未处理URL
		deepUrls.put(webAddress, 1);
		//判断存储网页的web文件是否存在，如果不存在就创建一个web目录
		File fDir = new File(directory);
		if (!fDir.exists())
		{
			fDir.mkdirs();
		}
		String tmp = getAUrl();
		if (StringUtils.isNotBlank(tmp))
		{
			this.setHtmlCharset(tmp);
			//输出从URL连接流中得到的网页地址
			this.getWebByUrl(tmp, charset);
			int i = 0;
			//每次执行都为该当前对象创建一个线程
			for (i = 0; i < threadNum; i++)
			{
				new Thread(new Processer(this)).start();
			}
		}
	}

	/**
	 * 将List中的每个URL目录下 的信息打印出
	 * @param strUrl
	 * @param charset
	 */
	public void getWebByUrl(String strUrl, String charset)
	{
		try
		{
			String htmlContent = HttpUtils.getHtmlContent(strUrl, charset);
			if (StringUtils.isNotBlank(htmlContent)
					&& (StringUtils.isBlank(this.keyWord) || htmlContent.indexOf(this.keyWord) != -1))//关键字过滤
			{
				if (!arrUrl.contains(strUrl))//URL消重
				{
					String fileName = Thread.currentThread().getId() + "-"
							+ System.currentTimeMillis()
							+ SystemConstant.EXTENDTION;
					String filePath = directory + File.separatorChar + fileName;
					FileUtils.writeStringToFile(new File(filePath),
							htmlContent, charset);
					arrUrl.add(strUrl);
					System.out.println("线程数量：" + threadNum + "，线程ID："
							+ Thread.currentThread().getId() + "，地址：" + strUrl
							+ "保存到->" + filePath);
					
					//将数据保存到数据库   start
					PageData data = new PageData();
					data.setSourceUrl(strUrl);
					data.setFileUrl(filePath);
				//	data.setCreateTime(DateUtils.getCurrentLong());
					data.setTitle(Jsoup.parse(htmlContent).getElementsByTag("title").text());
					data.setTextContent(htmlContent);
					PageDataDao dataDao = new PageDataDao();
				    dataDao.insert(data);
				}

				if (deepUrls.get(strUrl) < this.deepNum)
					this.getUrlByString(htmlContent, strUrl);
			}
		
		}
		catch (Exception e)
		{
			loger.error(e);
		}
	}
	

	/**
	 * 判断URL是否合法，如果合法返回URL，如果不则返回null
	 * @return
	 */
	public String getDomain()//获取主机地址
	{
		String domain = null;
		if (StringUtils.isNotBlank(webAddress))
		{
			domain = StringUtils.substringBetween(webAddress, "//", "/");
		}
		return domain;
	}

	public void getUrlByString(String inputArgs, String strUrl)
	{
		NodeList nodeList = HtmlParserUtils.getNodeList(inputArgs, "a",
				this.charset);
		if (null != nodeList && nodeList.size() > 0)
		{
			String domain = SystemConstant.PROTOL + this.getDomain();
			for (int i = 0; i < nodeList.size(); i++)
			{
				Node node = nodeList.elementAt(i);
				if (node instanceof LinkTag)
				{
					LinkTag tag = (LinkTag) node;
					String attr = tag.getAttribute("href");
					if (!attr.startsWith("javascript")
							&& !attr.startsWith("mailto"))
					{
						if (attr.startsWith("/"))
							attr = domain + attr;
						System.out.println(attr + "######");
						arrUrls.add(attr);
						deepUrls.put(attr, (deepUrls.get(strUrl) + 1));//将新抓取的URL存入哈希表，同时爬行深度加1
						attr = "";
					}
				}
			}
		}
	}

	/**
	 * 为当前的URL建立线程
	 * @author cjr
	 *
	 */
	class Processer implements Runnable//多线程
	{
		SpiderService gw;

		public Processer(SpiderService g)
		{
			this.gw = g;
		}

		public void run()
		{
			while (!arrUrls.isEmpty())
			{
				String tmp = getAUrl();
				if (StringUtils.isNotBlank(tmp))
				{
					setHtmlCharset(tmp);
					getWebByUrl(tmp, charset);
				}
			}
		}
	}

	public String getWebAddress()
	{
		return webAddress;
	}

	public void setWebAddress(String webAddress)
	{
		this.webAddress = webAddress;
	}

	public String getDirectory()
	{
		return directory;
	}

	public void setDirectory(String directory)
	{
		this.directory = directory;
	}

	public int getThreadNum()
	{
		return threadNum;
	}

	public void setThreadNum(int threadNum)
	{
		this.threadNum = threadNum;
	}

	public int getDeepNum()
	{
		return deepNum;
	}

	public void setDeepNum(int deepNum)
	{
		this.deepNum = deepNum;
	}

	public String getKeyWord()
	{
		return keyWord;
	}

	public void setKeyWord(String keyWord)
	{
		this.keyWord = keyWord;
	}

	public String getCharset()
	{
		return charset;
	}

	public void setCharset(String charset)
	{
		this.charset = charset;
	}
}
