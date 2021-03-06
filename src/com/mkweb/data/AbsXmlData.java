package com.mkweb.data;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mkweb.impl.XmlData;

public class AbsXmlData implements XmlData {
	
	protected String serviceName = null;
	protected String serviceType = null;
	protected String controlName = null;
	protected String data = null;
	protected String Tag = null;
	protected static String absPath = "/WEB-INF";
	
	public String getTag() { return this.Tag; }
	
	public static NodeList setNodeList(File defaultFile) {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		Document doc = null;
		
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(defaultFile);
		}catch(Exception e) {
	//		mkLog.error(e);
			e.getStackTrace();
		}
		
		if(doc == null)
			return null;
		
		Node root = doc.getFirstChild();
		NodeList nodeList = root.getChildNodes();
		
		return nodeList;
	}

	public void setServiceName(String serviceName) { this.serviceName = serviceName; }
	public String getServiceName() {	return this.serviceName;	}
	
	public void setServiceType(String serviceType) { this.serviceType = serviceType; }
	public String getServiceType() {	return this.serviceType;	}
	
	public void setControlName(String controlName)	{	this.controlName = controlName;	}
	public String getControlName() {	return this.controlName;	}
	
	public void setData(String data) {	this.data = data;	}
	public String getData() {	return this.data;	}
	
	public static String getAbsPath()	{	return absPath;	}
	public String getMyInfo() {	return "Control: " + (this.controlName) + " | Service: " + (this.serviceName) + " | Tag: " + (getTag());	}   
}
