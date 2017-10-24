/*
 * @(#)ConnectionHandler.java 12:30:54 PM
 * 
 * Copyright 2015 cjr, Inc. All rights reserved. ForYou
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.you.db;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import com.you.util.PropertyUtils;
//import java.sql.*;
/**
 * <p>Title: ConnectionHandler.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2015/4</p>
 * <p>Company: Co.,Ltd</p>
 * @author cjr
 * @version 1.0
 */
public class ConnectionHandler
{
	
	private static final String C3P0_CONFIG = "c3p0.properties";

	private static final Log LOG = LogFactory.getLog(ConnectionHandler.class);

	private static ConnectionHandler instance;

	private ComboPooledDataSource cpds;

	private HashMap<String, String> map;
	

	/**
	 * 私有构造器
	 */
	private ConnectionHandler()
	{
		this.initialize();
	}

	private void initialize()
	{
		int minPoolSize = 5;
		int maxPoolSize = 50;
		int acquireIncrement = 5;
		int initialPoolSize = 10;
		int idleTestPeriod = 3000;
		int maxIdleTime = 1800;
		InputStream in = ClassLoader.getSystemResourceAsStream(C3P0_CONFIG);
	//	ConnectionHandler.class.getResourceAsStream(C3P0_CONFIG);
		Properties p = new Properties();
        try
		{
			p.load(in);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		map = PropertyUtils.loadMapFromFileName(C3P0_CONFIG);
		cpds = new ComboPooledDataSource();
		try
		{
			cpds.setDriverClass(map.get("JDBC_DRIVER"));
		}
		catch (PropertyVetoException e)
		{
			LOG.error(e);
		}
		cpds.setJdbcUrl(map.get("JDBC_URL"));
		cpds.setUser(map.get("JDBC_USER"));
		cpds.setPassword(map.get("JDBC_PASS"));
	//	System.out.print("加载数据库成功");
		// 最小连接数
		try
		{
			minPoolSize = Integer.parseInt(map.get("Min_PoolSize"));
		}
		catch (NumberFormatException e)
		{
			LOG.error(e);
		}
		cpds.setMinPoolSize(minPoolSize);
		// 最大连接数
		try
		{
			maxPoolSize = Integer.parseInt(map.get("Max_PoolSize"));
		}
		catch (NumberFormatException e)
		{
			LOG.error(e);
		}
		cpds.setMaxPoolSize(maxPoolSize);
		// 增量条数
		try
		{
			acquireIncrement = Integer.parseInt(map.get("Acquire_Increment"));
		}
		catch (NumberFormatException e)
		{
			LOG.error(e);
		}
		cpds.setAcquireIncrement(acquireIncrement);
		// 初始化连接数
		try
		{
			initialPoolSize = Integer.parseInt(map.get("Initial_PoolSize"));
		}
		catch (NumberFormatException e)
		{
			LOG.error(e);
		}
		cpds.setInitialPoolSize(initialPoolSize);
		// 每隔多少秒测试连接是否可以正常使用
		try
		{
			idleTestPeriod = Integer.parseInt(map.get("Idle_Test_Period"));
		}
		catch (NumberFormatException e)
		{
			LOG.error(e);
		}
		cpds.setIdleConnectionTestPeriod(idleTestPeriod);
		// 连接超时
		try
		{
			maxIdleTime = Integer.parseInt(map.get("MAX_IdleTime"));
		}
		catch (NumberFormatException e)
		{
			LOG.error(e);
		}
		cpds.setMaxIdleTime(maxIdleTime);
		cpds.setTestConnectionOnCheckout(Boolean
				.getBoolean(map.get("Validate")));
	}

	/**
	 * 单例模式获取ConnectionHandler实例句柄
	 * @return ConnectionHandler ConnectionHandler
	 */
	public static final ConnectionHandler getInstance()
	{
		if (instance == null)
		{
			try
			{
				instance = new ConnectionHandler();
			}
			catch (Exception e)
			{
				LOG.error(e);
			}
		}
		return instance;
	}

	public static final ConnectionHandler getInstance(String jdbcURL,
			String jdbcUSER, String jdbcPASS)
	{
		if (instance == null)
		{

		}
		return instance;
	}

	public synchronized final Connection openConnection()
	{
		try
		{
			return cpds.getConnection();
		}
		catch (SQLException e)
		{
			LOG.error(e);
		}
		return null;
	}

	public void closeConnection(Connection connection)
	{
		if (null != connection)
		{
			try
			{
				connection.close();
			}
			catch (SQLException e)
			{
				LOG.error(e);
			}
		}
	}

	public ComboPooledDataSource getDataSource()
	{
		return this.cpds;
	}

	protected void finalize() throws Throwable
	{
		DataSources.destroy(cpds);
		super.finalize();
	}
	 
	/*Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection("jdbc\:mysql\://127.0.0.1\:3306/paco?useUnicode\=true&characterEncoding\=utf-8&autoReconnect\=true","root","");
	Statement statement = conn.createStatement();*/
}

