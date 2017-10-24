/*
 * @(#)AbstractDao.java 12:33:14 PM
 * 
 * Copyright 2013 Lzj.Liu, Inc. All rights reserved. ForYou
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.you.db;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>Title: AbstractDao.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2015/4</p>
 * <p>Company: Co.,Ltd</p>
 * @author cjr
 * @version 1.0
 */
public class AbstractDao<T>
{
	private static final Log LOG = LogFactory.getLog(AbstractDao.class);

	protected Class<T> entityClass;

	protected String className;

	@SuppressWarnings("unchecked")
	public AbstractDao()
	{
		entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		className = entityClass.getSimpleName();
	}

	/**
	 * 获取 QueryRunner
	 * @return QueryRunner QueryRunner
	 */
	private QueryRunner getQueryRunner()
	{
		ConnectionHandler ch = ConnectionHandler.getInstance();
		return new QueryRunner(ch.getDataSource());
	}

	/**
	 * 示例：INSERT INTO tableName(colum1,colum2...) VALUES(?,?...)
	 * 新增/修改/删除数据表并返回当前操作所影响的记录行数
	 * @param sql 要执行的SQL
	 * @param params 要传递的参数数组<表示要保存的相关值，对应?,?...>
	 * @return int 当前操作所影响的记录行数
	 */
	public int save(String sql, Object[] params)
	{
		int affectedRows = 0;
		QueryRunner runner = this.getQueryRunner();
		try
		{
			if (null == params)
				affectedRows = runner.update(sql);
			else
				affectedRows = runner.update(sql, params);
		}
		catch (SQLException e)
		{
			LOG.error(e);
		}
		return affectedRows;
	}

	/**
	 * 新增
	 * @param sql 要执行的SQL
	 * @param params 要传递的参数数组
	 * @return int 当前操作所影响的记录行数
	 */
	public int insert(String sql, Object[] params)
	{
		return this.save(sql, params);
	}

	/**
	 * 删除
	  * @param sql 要执行的SQL
	 * @param params 要传递的参数数组
	 * @return int 当前操作所影响的记录行数
	 */
	public int delete(String sql, Object[] params)
	{
		return this.save(sql, params);
	}

	/**
	 * 修改
	 * @param sql 要执行的SQL
	 * @param params 要传递的参数数组
	 * @return int 当前操作所影响的记录行数
	 */
	public int update(String sql, Object[] params)
	{
		return this.save(sql, params);
	}

	/**
	 * 示例：INSERT INTO tableName(colum1,colum2...) VALUES(?,?...)
	 * 新增数据表并返回自动增加类型主键的值
	 * @param sql 要执行的SQL
	 * @param params 要传递的参数数组<表示要插入的相关值，对应?,?...>
	 * @return long 主键编号
	 */
	@SuppressWarnings("unchecked")
	public long insertAndRetrunMySQLKey(String sql, Object[] params)
	{
		long primaryKey = 0;
		QueryRunner runner = this.getQueryRunner();
		try
		{
			if (null == params)
				runner.update(sql);
			else
				runner.update(sql, params);
			Number number = (Number) runner.query("SELECT LAST_INSERT_ID()",
					new ScalarHandler(1));
			primaryKey = number.longValue();
		}
		catch (SQLException e)
		{
			LOG.error(e);
		}
		return primaryKey;
	}

	/**
	 * 示例：INSERT INTO tableName(colum1,colum2...) VALUES(?,?...)
	 * 新增数据表并返回自动增加类型主键的值
	 * @param sql 要执行的SQL
	 * @param params 要传递的参数数组<表示要插入的相关值，对应?,?...>
	 * @return long 主键编号
	 */
	public long insertAndReturnKey(String sql, Object[] params)
	{
		long key = 0;
		if (null != params)
		{
			ConnectionHandler ch = ConnectionHandler.getInstance();
			ResultSet rs = null;
			PreparedStatement pstmt = null;
			Connection conn = null;
			try
			{
				conn = ch.openConnection();
				pstmt = conn.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS);
				ParameterMetaData pmd = pstmt.getParameterMetaData();
				if (params.length < pmd.getParameterCount())
				{
					LOG.error("参数个数错误！");
					key = -1;
				}
				else
				{
					for (int i = 0; i < params.length; i++)
					{
						pstmt.setObject(i + 1, params[i]);
					}
					pstmt.executeUpdate();
					rs = pstmt.getGeneratedKeys();
					if (rs.next())
						key = rs.getLong(1);
				}

			}
			catch (SQLException e)
			{
				LOG.error(e);
			}
			finally
			{
				DbUtils.closeQuietly(conn, pstmt, rs);
			}
		}
		return key;
	}

	/**
	 * 数据对象查询
	 * @param sql 要执行的SQL
	 * @param params 要传递的参数数组
	 * @return Object Object
	 */
	@SuppressWarnings("unchecked")
	public Object load(String sql, Object[] params)
	{
		Object object = null;
		List<T> list = null;
		QueryRunner runner = this.getQueryRunner();
		try
		{
			if (null == params)
				list = (List<T>) runner.query(sql, new BeanListHandler(
						entityClass));
			else
				list = (List<T>) runner.query(sql, new BeanListHandler(
						entityClass), params);
		}
		catch (SQLException e)
		{
			LOG.error(e);
		}
		if (null != list && !list.isEmpty())
			object = list.get(0);
		return object;
	}

	/**
	 * 数据对象查询，也可以直接将Object传入QueryRunner执行
	 * @param sql 要执行的SQL
	 * @param params 要传递的参数数组
	 * @return Object Object
	 */
	@SuppressWarnings("unchecked")
	public Object load(String sql, Object params)
	{
		return this.load(sql, new Object[]{params});
	}

	/**
	 * 多个参数的数据对象列表查询
	 * @param sql 要执行的SQL
	 * @param params 要传递的参数数组
	 * @return List<T> List
	 */
	@SuppressWarnings("unchecked")
	public List<T> list(String sql, Object[] params)
	{
		List<T> list = null;
		QueryRunner runner = this.getQueryRunner();
		try
		{
			if (null == params)
				list = (List<T>) runner.query(sql, new BeanListHandler(
						entityClass));
			else
				list = (List<T>) runner.query(sql, new BeanListHandler(
						entityClass), params);
		}
		catch (SQLException e)
		{
			LOG.error(e);
		}
		return list;
	}

	/**
	 * 不带参数的数据对象列表查询
	 * @param sql 要执行的SQL
	 * @return List<T> List
	 */
	@SuppressWarnings("unchecked")
	public List<T> list(String sql)
	{
		List<T> list = null;
		QueryRunner runner = this.getQueryRunner();
		try
		{
			list = (List<T>) runner
					.query(sql, new BeanListHandler(entityClass));
		}
		catch (SQLException e)
		{
			LOG.error(e);
		}
		return list;
	}

	/**
	 * 一个参数的数据对象列表查询
	 * @param sql 要执行的SQL
	 * @param param 参数值
	 * @return List<T> List<也可以return this.list(sql,new Object[]{param})>
	 */
	@SuppressWarnings("unchecked")
	public List<T> list(String sql, Object param)
	{
		return this.list(sql, new Object[] { param });
	}

	/**
	 * 多条件动态组合查询
	 * @param sql 要执行的SQL
	 * @param listParameter 动态查询参数值
	 * @return List<T> List<T>
	 */
	public List<T> query(String sql, List<Object> listParameter)
	{
		List<T> list = null;
		Object[] params = this.getParams(listParameter);
		if (null != params)
			list = this.list(sql, params);
		return list;
	}

	/**
	 * List转数组
	 * @param listParameter List<Object>
	 * @return Object[] Object[]
	 */
	private Object[] getParams(List<Object> listParameter)
	{
		Object[] params = null;
		if (null != listParameter && !listParameter.isEmpty())
		{
			int iSize = listParameter.size();
			params = new Object[iSize];
			for (int i = 0; i < iSize; i++)
			{
				params[i] = listParameter.get(i);
			}
		}
		return params;
	}

	/**
	 * 数据统计查询
	 * @param sql 要执行的SQL
	 * @param params 
	 * @return long
	 */
	@SuppressWarnings("unchecked")
	public long count(String sql, Object[] params)
	{
		Number number = 0;
		QueryRunner runner = this.getQueryRunner();
		try
		{
			if (null == params)
				number = (Number) runner.query(sql, new ScalarHandler(1));
			else
				number = (Number) runner.query(sql, new ScalarHandler(1),
						params);
		}
		catch (SQLException e)
		{
			LOG.error(e);
		}
		return (null != number) ? number.longValue() : -1;
	}

	/**
	 * 查询并返回Map
	 * @param sql 要执行的SQL
	 * @param params 参数值
	 * @return Map<String, Object> Map<String, Object>
	 */
	public Map<String, Object> find(String sql, Object params)
	{
		return this.find(sql, new Object[] { params });
	}

	/**
	 * 查询并返回Map
	 * @param sql 要执行的SQL
	 * @param params 参数值
	 * @return Map<String, Object> Map<String, Object>
	 */
	public Map<String, Object> find(String sql, Object[] params)
	{
		Map<String, Object> map = null;
		ConnectionHandler ch = ConnectionHandler.getInstance();
		QueryRunner runner = new QueryRunner(ch.getDataSource());
		try
		{
			map = (Map<String, Object>) runner.query(sql, new MapHandler(),
					params);
		}
		catch (SQLException e)
		{
			LOG.error(e);
		}
		return map;
	}
}