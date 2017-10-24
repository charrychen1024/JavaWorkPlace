/*
 * @(#)PropertyUtils.java 12:31:53 PM
 * 
 * Copyright 2013 Lzj.Liu, Inc. All rights reserved. ForYou
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.you.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>Title: PropertyUtils.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2015/4</p>
 * <p>Company: Co.,Ltd</p>
 * @author cjr
 * @version 1.0
 */
public class PropertyUtils//加载属性文件
{
	private static final Log LOG = LogFactory.getLog(PropertyUtils.class);

	/**
	 * 私有构造器，防止类的实例化
	 */
	private PropertyUtils()
	{
		super();
	}

	/**
	 * 取得指定路径及文件名的properties文件中指定key的值<格式：G:\test.properties>
	 * @param filePath properties文件路径
	 * @param key key值
	 * @return String value值
	 */
	public static String getValueByFilePath(String filePath, String key)
	{
		String value = null;
		if (StringUtils.isNotBlank(filePath) && StringUtils.isNotBlank(key))
		{
			Properties props = new Properties();
			InputStream in = null;
			try
			{
				in = new BufferedInputStream(new FileInputStream(filePath));
				props.load(in);
				value = props.getProperty(key);
			}
			catch (FileNotFoundException e)
			{
				LOG.error(e);
			}
			catch (IOException e)
			{
				LOG.error(e);
			}
			finally
			{
				IOUtils.closeQuietly(in);
			}
		}
		return value;
	}

	/**
	 * 取得properties文件中指定key的值<属性文件在classpath>
	 * @param fileName classPath下的文件名
	 * @param key key值
	 * @return String value值
	 */
	public static String getValueByFileName(String fileName, String key)
	{
		String value = null;
		if (StringUtils.isNotBlank(fileName) && StringUtils.isNotBlank(key))
		{
			Properties props = new Properties();
			String filePath = PropertyUtils.class.getClassLoader().getResource(
					fileName).getPath();
			InputStream in = null;
			try
			{
				in = new BufferedInputStream(new FileInputStream(filePath));
				props.load(in);
				value = props.getProperty(key);
			}
			catch (FileNotFoundException e)
			{
				LOG.error(e);
			}
			catch (IOException e)
			{
				LOG.error(e);
			}
			finally
			{
				IOUtils.closeQuietly(in);
			}
		}
		return value;
	}

	/**
	 * 读取指定路径及文件名称的properties文件的所有内容
	 * @param filePath 文件路径
	 * @return HashMap 存储key与value的HashMap
	 */
	public static HashMap<String, String> loadMapFromFilePath(String filePath)
	{
		HashMap<String, String> map = null;
		if (StringUtils.isNotBlank(filePath))
		{
			InputStream in = null;
			Properties props = new Properties();
			try
			{
				in = new BufferedInputStream(new FileInputStream(filePath));
				props.load(in);
				Enumeration<?> en = props.propertyNames();
				map = new HashMap<String, String>();
				while (en.hasMoreElements())
				{
					String key = (String) en.nextElement();
					String value = props.getProperty(key);
					map.put(key, value);
				}
			}
			catch (FileNotFoundException e)
			{
				LOG.error(e);
			}
			catch (IOException e)
			{
				LOG.error(e);
			}
			finally
			{
				IOUtils.closeQuietly(in);
			}
		}
		return map;
	}

	/**
	 * 读取classpath下指定文件名称的properties文件的所有内容
	 * @param fileName 文件名称
	 * @return HashMap 存储key与value的HashMap
	 */
	public static HashMap<String, String> loadMapFromFileName(String fileName)
	{
		HashMap<String, String> map = null;
		if (StringUtils.isNotBlank(fileName))
		{
			Properties props = new Properties();
			String filePath = PropertyUtils.class.getClassLoader().getResource(
					fileName).getPath();
			InputStream in = null;
			try
			{
				in = new BufferedInputStream(new FileInputStream(filePath));
				props.load(in);
				Enumeration<?> en = props.propertyNames();
				map = new HashMap<String, String>();
				while (en.hasMoreElements())
				{
					String key = (String) en.nextElement();
					String value = props.getProperty(key);
					map.put(key, value);
				}
			}
			catch (FileNotFoundException e)
			{
				LOG.error(e);
			}
			catch (IOException e)
			{
				LOG.error(e);
			}
			finally
			{
				IOUtils.closeQuietly(in);
			}
		}
		return map;
	}

	/**
	 * 写入指定路径及文件名的properties文件key和值<格式：G:\test.properties>
	 * @param filePath 文件路径
	 * @param parameterName key名称
	 * @param parameterValue key值
	 */
	public static void setValueByFilePath(String filePath,
			String parameterName, String parameterValue)
	{
		Properties prop = new Properties();
		InputStream fis = null;
		OutputStream fos = null;
		try
		{
			fis = new BufferedInputStream(new FileInputStream(filePath));
			prop.load(fis);
			fos = new FileOutputStream(filePath);
			prop.setProperty(parameterName, parameterValue);
			prop.store(fos, "Update '" + parameterName + "' value");
		}
		catch (FileNotFoundException e)
		{
			LOG.error(e);
		}
		catch (IOException e)
		{
			LOG.error(e);
		}
		finally
		{
			IOUtils.closeQuietly(fis);
			IOUtils.closeQuietly(fos);
		}
	}

	/**
	 * 写入classpath下指定文件名称的properties文件的key和值
	 * @param fileName 文件名称
	 * @param parameterName key名称
	 * @param parameterValue key值
	 */
	public static void setValueByFileName(String fileName,
			String parameterName, String parameterValue)
	{
		Properties prop = new Properties();
		String filePath = PropertyUtils.class.getClassLoader().getResource(
				fileName).getPath();
		InputStream in = null;
		OutputStream fos = null;
		try
		{
			in = new BufferedInputStream(new FileInputStream(filePath));
			prop.load(in);
			prop.setProperty(parameterName, parameterValue);
			fos = new FileOutputStream(filePath);
			prop.store(fos, "Update '" + parameterName + "' value");
		}
		catch (FileNotFoundException e)
		{
			LOG.error(e);
		}
		catch (IOException e)
		{
			LOG.error(e);
		}
		finally
		{
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(fos);
		}
	}
}
