/*
 * @(#)BbsClassDao.java 11:01:39 PM
 * 
 * Copyright 2015 cjr, Inc. All rights reserved. ForYou
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.you.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.you.bean.PageData;
import com.you.db.AbstractDao;


public class PageDataDao extends AbstractDao<PageData>
{

	private static final String SAVE_BBSCLASS = "INSERT INTO " +
	" PageData(sourceUrl,fileUrl,title,textContent) VALUES(?,?,?,?)";

	public long insert(PageData data)
	{
		Object[] params = new Object[] { data.getSourceUrl(),
				data.getFileUrl(), data.getTitle(), data.getTextContent()};
		return super.insertAndRetrunMySQLKey(SAVE_BBSCLASS, params);
	}
	
}