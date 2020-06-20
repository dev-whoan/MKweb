package com.mkweb.config;

import java.io.File;

import java.util.HashMap;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mkweb.can.MkSqlConfigCan;
import com.mkweb.data.SqlXmlData;
import com.mkweb.logger.MkLogger;

public class MkRestApiSqlConfigs extends MkSqlConfigCan {
	private HashMap<String, SqlXmlData> api_sql_configs = new HashMap<String, SqlXmlData>();

	private File defaultFile = null;

	private static MkRestApiSqlConfigs mrasc = null;
	private long lastModified; 
	private MkLogger mklogger = MkLogger.Me();
	private String controlName = "MkApiSQL";

	private String TAG = "[MkRestApiSqlConfigs]";

	public static MkRestApiSqlConfigs Me() {
		if(mrasc == null)
			mrasc = new MkRestApiSqlConfigs();
		return mrasc;
	}
	private String[] sl_list = {
			"id",
			"db",
			"allow_single"
	};
	private String[] sl_info = new String[sl_list.length];

	public void setSqlConfigs(File sqlConfigs) {
		api_sql_configs.clear();
		defaultFile = sqlConfigs;

		mklogger.info("=*=*=*=*=* MkWeb Rest Api Sql Configs Start*=*=*=*=*=*=");
		mklogger.info(TAG + "File: " + defaultFile.getAbsolutePath());
		if(defaultFile == null || !defaultFile.exists())
		{
			mklogger.error("Config file is not exists or null");
			return;
		}

		NodeList nodeList = setNodeList(defaultFile);

		if(nodeList != null) {
			lastModified = defaultFile.lastModified();

			for(int i = 0; i < nodeList.getLength(); i++)
			{
				Node node = nodeList.item(i);
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					NamedNodeMap attributes = node.getAttributes();

					for(int sli = 0; sli < sl_list.length; sli++) {
						Node tN = attributes.getNamedItem(sl_list[sli]);
						sl_info[sli] = (tN != null ? tN.getNodeValue() : null);
					}

					String query = node.getTextContent();

					SqlXmlData xmlData = new SqlXmlData();

					xmlData.setControlName(this.controlName);
					//ID = 0, DB = 1  
					xmlData.setServiceName(sl_info[0]);
					xmlData.setDB(sl_info[1]);
					xmlData.setAllowSingle(sl_info[2]);
					xmlData.setData(query);

					api_sql_configs.put(sl_info[0], xmlData);

					mklogger.info("SQL ID :\t\t\t" + sl_info[0]);
					mklogger.info("SQL DB :\t\t\t" + sl_info[1]);
					mklogger.info("Allow  :\t\t\t" + sl_info[2]);
					mklogger.info("");

					query = query.trim();
					String queryMsg = "";

					String[] queryBuffer = query.split("\n");

					for (int j = 0; j < queryBuffer.length; j++) {
						String tempQuery = queryBuffer[j].trim();
						queryMsg += "\t\t\t\t\t\t\t\t" + tempQuery + "\n";
					}

					mklogger.info("query  :\n" + queryMsg + "\n");

				}
			}
		}else {
			mklogger.info(TAG + " No SQL Service has found. If you set SQL service, please check SQL config and web.xml.");
		}


		mklogger.info("=*=*=*=*=* MkWeb Rest Api Sql Configs Start*=*=*=*=*=*=");
	}

	public SqlXmlData getControlService(String serviceName) {
		if(lastModified != defaultFile.lastModified()) {
			setSqlConfigs(defaultFile);
			mklogger.info("==============Reload Rest Api SQL Config files==============");
			mklogger.info("==========Caused by   :    different modified time==========");
			mklogger.info("==============Reload Rest Api SQL Config files==============");
		}

		return api_sql_configs.get(serviceName);
	}
}