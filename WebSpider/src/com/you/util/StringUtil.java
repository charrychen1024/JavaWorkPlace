/*
 * @(#)StringUtil.java 4:57:16 PM
 * 
 * Copyright 2013 Lzj.Liu, Inc. All rights reserved. ForYou
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.you.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.you.constant.SystemConstant;

/**
 * <p>Title: StringUtil.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2015/4</p>
 * <p>Company: Co.,Ltd</p>
 * @author cjr
 * @version 1.0
 */
public class StringUtil
{
	private StringUtil()
	{
		super();
	}
	
	/**
	 * 判断字符串是否URL格式
	 * @param string 要判断的字符串
	 * @return boolean true/false
	 */
	public static boolean isUrl(String string)
	{
		boolean bool = false;
		Pattern pattern = Pattern.compile(SystemConstant.URL_PATTERN,
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(string);
		if (matcher.find())
			bool = true;
		return bool;
	}
}
