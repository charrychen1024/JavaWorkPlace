/*
 * @(#)DateUtils.java 9:10:19 PM
 * 
 * Copyright 2013 Lzj.Liu, Inc. All rights reserved. ForYou
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.you.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.you.bean.PageData;
import com.you.dao.PageDataDao;

/**
 * <p>Title: DateUtils.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2015/4</p>
 * <p>Company: Co.,Ltd</p>
 * @author cjr
 * @version 1.0
 */
public class DateUtils
{

	private DateUtils()
	{
		super();
	}

	/**
	 * 将当前时间精确到秒并转化为long格式
	 * @return iTime 整数格式的当前时间，精确到秒
	 */
	public static long getCurrentLong()
	{
		long iTime = System.currentTimeMillis() / 1000;
		return iTime;
	}

	/**
	 * 将long格式的时间格式转化为指定的日期格式
	 * @param lTime long格式的时间
	 * @param format 要格式化的时间格式
	 * @return String 格式化后的日期字符串
	 */
	public static String covertLongToTime(long lTime, String format)
	{
		long l = lTime * 1000;
		Date date = new Date((long) l);
		return formatDate(date, format);
	}

	/**
	 * 将日期对象格式化为指定格式的字符串
	 * @param date 日期对象
	 * @param format 字符串格式
	 * @return String 格式化后的日期字符串
	 */
	public static String formatDate(Date date, String format)
	{
		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}
	
/*	
public static void main(String[] args)
	   {
	      PageData data = new PageData();
	      data.setSourceUrl("65465465");
	      data.setFileUrl("6545615351");
	      data.setCreateTime(564654654l);
	      data.setTitle("asdf");
	      data.setTextContent("asdf");
	      
	      PageDataDao dataDao = new PageDataDao();
	      dataDao.insert(data);
	   }
	*/ 


}
