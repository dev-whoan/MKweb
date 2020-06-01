package com.mkweb.web;

import java.util.ArrayList;

import com.mkweb.config.PageConfigs;
import com.mkweb.data.PageXmlData;
import com.mkweb.logger.MkLogger;

public class PageInfo {
	private String pageControlName = null;
	private ArrayList<String> pageServiceName = null;
	private ArrayList<String> pageParameter = null;
	private ArrayList<String[]> pageSqlInfo = null;
	private ArrayList<String> pageValue = null;
	
	private String TAG = "[PageInfo]";
	private MkLogger mklogger = MkLogger.Me();
	private boolean set = false;
	
	public PageInfo(String requestPageName) {
		this.pageControlName = requestPageName;
		set = false;
		setPageInfo();
	}
	
	private void setPageInfo() {
		ArrayList<PageXmlData> pxd = PageConfigs.Me().getControl(this.pageControlName);
		pageServiceName = new ArrayList<String>();
		pageParameter = new ArrayList<String>();
		pageSqlInfo = new ArrayList<String[]>();
		pageValue = new ArrayList<String>();
		if(pxd == null)	{
			mklogger.error(TAG + " pageControlName is invalid : " + pageControlName);
			return;
		}
		
		for(int i = 0; i < pxd.size(); i++) {
			PageXmlData xmlData = pxd.get(i);
			pageServiceName.add(i, xmlData.getServiceName());
			pageParameter.add(i, xmlData.getParameter());
			pageSqlInfo.add(i, xmlData.getSql());
			pageValue.add(i, xmlData.getData());
		}
		set = true;
	}
	public boolean isSet()	{	return this.set;	}

	public String getPageControlName(){	return this.pageControlName;	}
	public ArrayList<String> getPageServiceName() {	return this.pageServiceName;	}
	public ArrayList<String> getPageParameter() {	return this.pageParameter;	}
	public ArrayList<String[]> getPageSqlInfo()	{	return this.pageSqlInfo;	}
	public ArrayList<String> getPageValue()	{	return this.pageValue;	}
}