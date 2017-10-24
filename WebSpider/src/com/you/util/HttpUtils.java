/*
 * @(#)HttpUtils.java 9:59:56 PM
 * 
 * Copyright 2013 Lzj.Liu, Inc. All rights reserved. ForYou
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.you.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URL;

import org.apache.commons.lang.StringUtils;

/**
 * <p>Title: HttpUtils.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2015/4</p>
 * <p>Company: Co.,Ltd</p>
 * @author cjr
 * @version 1.0
 */
public class HttpUtils
{

	private HttpUtils()
	{
		super();
	}

	public static String getHtmlContent(URL url, String encode)//获取网页
	{
		StringBuffer contentBuffer = new StringBuffer();

		int responseCode = -1;
		HttpURLConnection con = null;
		try
		{
			con = (HttpURLConnection) url.openConnection();//打开连接
			con.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");// IE代理进行下载
			con.setConnectTimeout(60000);
			con.setReadTimeout(60000);
			// 获得网页返回信息码
			responseCode = con.getResponseCode();
			if (responseCode == -1)
			{
				System.out.println(url.toString()
						+ " : connection is failure...");
				con.disconnect();
				return null;
			}
			if (responseCode >= 400) // 请求失败
			{
				System.out.println("请求失败:get response code: " + responseCode);
				con.disconnect();
				return null;
			}

			InputStream inStr = con.getInputStream();
			InputStreamReader istreamReader = new InputStreamReader(inStr,
					encode);
			BufferedReader buffStr = new BufferedReader(istreamReader);

			String str = null;
			while ((str = buffStr.readLine()) != null)
				contentBuffer.append(str);
			inStr.close();
		}
		catch (SocketException e)
		{
		}
		catch (IOException e)
		{
			contentBuffer = null;
		}
		finally
		{
			con.disconnect();
		}

		return contentBuffer.toString().toLowerCase();
	}

	public static String getHtmlContent(String url, String encode)//递归
	{
		if (StringUtils.isNotBlank(url))
		{
			if (!url.toLowerCase().startsWith("http://"))
			{
				url = "http://" + url;
			}
			try
			{
				URL rUrl = new URL(url);
				return getHtmlContent(rUrl, encode);
			}
			catch (Exception e)
			{
				return null;
			}
		}
		return null;
	}

}
