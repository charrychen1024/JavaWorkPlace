/*
 * @(#)HtmlParserUtils.java
 * May 17, 20138:41:37 PM
 * Copyright 2015 cjr, Inc. All rights reserved. 
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.you.util;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.lexer.Lexer;
import org.htmlparser.lexer.Page;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.MetaTag;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.util.DefaultParserFeedback;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.you.constant.SystemConstant;

/**
 * <p>Title: HtmlParserUtils.java</p>
 * <p>Description: 简单的html解析工具</p>
 * <p>Copyright: Copyright (c) 2015/4</p>
 * <p>Company: Co.,Ltd</p>
 * @author cjr
 * @version 1.0
 */
public class HtmlParserUtils
{
	private static Log loger = LogFactory.getLog(HtmlParserUtils.class);

	private static final String IMAGE = "img";

	/**
	 * 解析字符串<未测试>
	 * @param inputHTML String
	 * @return Parser Parser
	 */
	public static Parser createParser(String inputHTML, String encode)
	{
		Lexer mLexer = new Lexer(new Page(inputHTML, encode));
		return new Parser(mLexer, new DefaultParserFeedback(
				DefaultParserFeedback.QUIET));
	}

	/**
	 * 取得html内容中所有的图片名称，不包括扩展名
	 * @param parser Parser
	 * @return String[] 图片名称
	 */
	public static String[] getImages(Parser parser)
	{
		NodeFilter filter = new TagNameFilter(IMAGE);
		NodeList nodeList = null;
		try
		{
			nodeList = parser.extractAllNodesThatMatch(filter);
		}
		catch (ParserException e)
		{
			loger.error(e);
		}
		String[] strings = null;
		if (null != nodeList)
		{
			int iSize = nodeList.size();
			strings = new String[iSize];
			for (int i = 0; i < iSize; i++)
			{
				ImageTag imageTag = (ImageTag) nodeList.elementAt(i);
				String imageUrl = imageTag.getImageURL();
				strings[i] = FilenameUtils.getBaseName(imageUrl);
			}
		}
		return strings;
	}

	/**
	 * 取得html内容中所有的图片名称，不包括扩展名
	 * @param htmlContent 要解析的html内容
	 * @param encode 编码
	 * @return String[] 图片名称
	 */
	public static String[] getImages(String htmlContent, String encode)
	{
		if (StringUtils.isBlank(htmlContent))
			return null;
		else
		{
			Parser parser = Parser.createParser(htmlContent, encode);
			return getImages(parser);
		}
	}

	/**
	 * 取得NodeList
	 * @param parser Parser
	 * @param andFilter AndFilter
	 * @return NodeList nodeList
	 */
	public static NodeList getNodeList(Parser parser, AndFilter andFilter)
	{
		NodeList nodeList = null;
		try
		{
			if (null != parser)
				nodeList = parser.extractAllNodesThatMatch(andFilter);
		}
		catch (ParserException e)
		{
			loger.error(e);
		}
		return nodeList;
	}

	/**
	 * 取得NodeList
	 * @param htmlContent Html内容
	 * @param tagName 标签名称
	 * @param attName 属性名称
	 * @param attValue 属性值
	 * @param encode 编码格式
	 * @return NodeList NodeList
	 */
	public static NodeList getNodeList(String htmlContent, String tagName,
			String attName, String attValue, String encode)
	{
		Parser parser = Parser.createParser(htmlContent, encode);
		AndFilter andFilter = new AndFilter(new TagNameFilter(tagName),
				new HasAttributeFilter(attName, attValue));
		return getNodeList(parser, andFilter);
	}

	/**
	 * 取得NodeList
	 * @param htmlContent Html源码
	 * @param tagName 标签名称
	 * @param encode 编码格式
	 * @return NodeList NodeList
	 */
	public static NodeList getNodeList(String htmlContent, String tagName,
			String encode)
	{
		Parser parser = Parser.createParser(htmlContent, encode);
		return getNodeList(parser, tagName);
	}

	/**
	 * 取得NodeList
	 * @param parser Parser
	 * @param tagName 标签名称
	 * @return NodeList NodeList
	 */
	public static NodeList getNodeList(Parser parser, String tagName)
	{
		NodeList nodeList = null;
		TagNameFilter tagFilter = new TagNameFilter(tagName);
		try
		{
			if (null != parser)
				nodeList = parser.extractAllNodesThatMatch(tagFilter);
		}
		catch (ParserException e)
		{
			loger.error(e);
		}
		return nodeList;
	}

	/**
	 * 根据标签名称，标签属性名称，标签属性值取得该标签内的html内容
	 * @param parser Parser
	 * @param tagName 标签名称
	 * @param attName 标签的属性
	 * @param attValue 标签的属性值
	 * @return String 该标签内的html内容
	 */
	public static String getHtmlByTagName(Parser parser, String tagName,
			String attName, String attValue)
	{
		String result = null;
		AndFilter andFilter = new AndFilter(new TagNameFilter(tagName),
				new HasAttributeFilter(attName, attValue));
		NodeList nodeList = getNodeList(parser, andFilter);
		if (null != nodeList)
		{
			result = nodeList.toHtml();
		}
		return result;
	}

	/**
	 * 根据标签名称，标签属性名称，标签属性值取得该标签内的html内容
	 * @param htmlContent 要解析的html内容
	 * @param tagName 标签名称
	 * @param attName 标签的属性
	 * @param attValue 标签的属性值
	 * @param encode 编码
	 * @return String 该标签内的html内容
	 */
	public static String getHtmlByTagName(String htmlContent, String tagName,
			String attName, String attValue, String encode)
	{
		if (StringUtils.isBlank(htmlContent))
			return null;
		else
		{
			Parser parser = Parser.createParser(htmlContent, encode);
			return getHtmlByTagName(parser, tagName, attName, attValue);
		}
	}

	/**
	 * 通过Parser及tagName取得文本
	 * @param parser Parser
	 * @param tagName html源码中的标签名称
	 * @return String[] 文本内容数组
	 */
	public static String[] getTextByTagName(Parser parser, String tagName)
	{
		NodeList nodeList = getNodeList(parser, tagName);
		return getTextByNodeList(nodeList);
	}

	/**
	 * 通过NodeList取得文本
	 * @param nodeList NodeList
	 * @return String[] 文本内容数组
	 */
	public static String[] getTextByNodeList(NodeList nodeList)
	{
		String[] strings = null;
		if (null != nodeList)
		{
			int iSize = nodeList.size();
			strings = new String[iSize];
			for (int i = 0; i < nodeList.size(); i++)
			{
				Node node = nodeList.elementAt(i);
				strings[i] = StringUtils.trimToEmpty(node.toPlainTextString());
			}
		}
		return strings;
	}

	/**
	 * 取文本内容
	 * @param parser Parser
	 * @param tagName 标签名称
	 * @param attName 属性名称
	 * @param attValue 属性值
	 * @return String[] 文本内容数组
	 */
	public static String[] getTextByTagName(Parser parser, String tagName,
			String attName, String attValue)
	{
		AndFilter andFilter = new AndFilter(new TagNameFilter(tagName),
				new HasAttributeFilter(attName, attValue));
		NodeList nodeList = getNodeList(parser, andFilter);
		return getTextByNodeList(nodeList);
	}

	/**
	 * 通过html源码及标签名取得文件
	 * @param htmlContent html源码内容
	 * @param tagName 标签名称
	 * @param encode 编码格式
	 * @return String[] 文本内容数组
	 */
	public static String[] getTextByTagName(String htmlContent, String tagName,
			String encode)
	{
		if (StringUtils.isBlank(htmlContent))
			return null;
		else
		{
			Parser parser = Parser.createParser(htmlContent, encode);
			return getTextByTagName(parser, tagName);
		}
	}

	/**
	 * 通过html源码及标签名取得文件
	 * @param htmlContent html源码内容
	 * @param tagName 标签名称
	 * @param encode 编码格式
	 * @return String[] 文本内容数组
	 */
	public static String[] getTextByTagName(String htmlContent, String tagName,
			String attName, String attValue, String encode)
	{
		if (StringUtils.isBlank(htmlContent))
			return null;
		else
		{
			Parser parser = Parser.createParser(htmlContent, encode);
			AndFilter andFilter = new AndFilter(new TagNameFilter(tagName),
					new HasAttributeFilter(attName, attValue));
			NodeList nodeList = getNodeList(parser, andFilter);
			return getTextByNodeList(nodeList);
		}
	}

	/**
	 * 通过url及标签名称取得文本内容
	 * @param url url
	 * @param tagName 标签名称
	 * @return String[] 文本内容数组
	 */
	public static String[] getTextByTagName(String url, String tagName)
	{
		Parser parser = new Parser();
		try
		{
			parser.setURL(url);
			parser.setEncoding(parser.getEncoding());
		}
		catch (ParserException e)
		{
			loger.error(e);
		}
		return getTextByTagName(parser, tagName);
	}

	/**
	 * 从url中取得title中的相关文字
	 * @param url 网络地址
	 * @return String 取得title中的相关文字
	 * @throws ParserException ParserException
	 */
	public static String getTitle(String url) throws ParserException
	{

		Parser myParser = new Parser(url);
		// 设置编码
		myParser.setEncoding(SystemConstant.ENCODE_UT);
		String titleTag = "title";
		NodeFilter titleFilter = new TagNameFilter(titleTag);
		NodeList titleList = myParser.extractAllNodesThatMatch(titleFilter);
		int size = titleList.size();
		String title = null;
		if (size == 1)
		{
			TitleTag titleT = (TitleTag) titleList.elementAt(0);
			title = titleT.getTitle();

		}
		return title;

	}

	/**
	 * 从url中取得meta中的content
	 * @param url 网络地址
	 * @param attName meta的属性
	 * @param attValue meta的值
	 * @param encode 编码格式
	 * @return String 取得meta中的content
	 * @throws ParserException ParserException
	 */
	public static String getMeta(String url, String attName, String attValue,
			String encode) throws ParserException
	{

		Parser myParser = new Parser(url);
		// 设置编码
		myParser.setEncoding(encode);
		String metaTag = "meta";
		NodeFilter metaFilter = new TagNameFilter(metaTag);
		NodeList metaList = myParser.extractAllNodesThatMatch(metaFilter);
		String meta = null;
		for (int i = 0; i < metaList.size(); i++)
		{
			MetaTag metaT = (MetaTag) metaList.elementAt(i);
			String metaValue = metaT.getAttribute(attName);
			if (StringUtils.isNotBlank(metaValue)
					&& metaValue.equalsIgnoreCase(attValue))
			{
				meta = metaT.getAttribute("content");
			}
		}
		return meta;

	}
}
