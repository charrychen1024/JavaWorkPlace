package com.you.bean;

/**
 * 页面保存相关内容
 * @author cjr
 *
 */
public class PageData
{
	
	private Long refrenceId;
	
	private String sourceUrl;
	
	private String fileUrl;
	
	private String title;
	
	private Long createTime;
	
	private String textContent;
	
	

	

	public String getTextContent()
	{
		return textContent;
	}

	public void setTextContent(String textContent)
	{
		this.textContent = textContent;
	}

	public Long getRefrenceId()
	{
		return refrenceId;
	}

	public void setRefrenceId(Long refrenceId)
	{
		this.refrenceId = refrenceId;
	}

	public String getSourceUrl()
	{
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl)
	{
		this.sourceUrl = sourceUrl;
	}

	public String getFileUrl()
	{
		return fileUrl;
	}

	public void setFileUrl(String fileUrl)
	{
		this.fileUrl = fileUrl;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public Long getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Long createTime)
	{
		this.createTime = createTime;
	} 
	
}
