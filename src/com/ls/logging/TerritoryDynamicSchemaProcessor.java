package com.ls.logging;

import java.io.InputStream;

import org.pentaho.platform.api.engine.IPentahoSession;
import org.pentaho.platform.engine.core.system.PentahoSessionHolder;

import mondrian.i18n.LocalizingDynamicSchemaProcessor;
import mondrian.olap.Util.PropertyList;

public class TerritoryDynamicSchemaProcessor extends LocalizingDynamicSchemaProcessor implements mondrian.spi.DynamicSchemaProcessor {
	private static LSLogger logger = LSLogger.getLSLogger(MyTenantedDatasourceService.class);

	public TerritoryDynamicSchemaProcessor() {
		super();
		System.out.println("DSP: Creating the DSP from LDSP - please work!");
	}

	
	@Override
	public String filter(String schemaUrl, PropertyList connectInfo, InputStream stream) throws Exception {

		String schema = super.filter(schemaUrl, connectInfo, stream);
		System.out.println("Schema URL ==> " + schemaUrl);
		logger.debug("Schema URL ==> " + schemaUrl);
		System.out.println("Schema connectInfo ==> " + connectInfo);
		logger.debug("Schema connectInfo ==> " + connectInfo);
		System.out.println("Schema stream ==> " + stream);
		logger.debug("Schema stream ==> " + stream);
		System.out.println("Schema Name ==> " + schema);
		logger.debug("Schema Name ==> " + schema);
		IPentahoSession session = PentahoSessionHolder.getSession();
		String practiceID = (String)session.getAttribute("PRACTICE_ID").toString();
		String strUserId = (String)session.getAttribute("USER_ID").toString();
		String strUserName = (String)session.getAttribute("USER_NAME");
		String strPracticeName = (String)session.getAttribute("PRACTICE_NAME");
		String strDatabaseDetail = (String)session.getAttribute("DATABASE_DETAIL");
		String strSchemaDetail = (String)session.getAttribute("SCHEMA_DETAIL");
	    //String tenantID = "141";
		//String strSchemaDetail = "PUBLIC";
		//Schema connectInfo ==> provider=mondrian; Catalog=mondrian:/panalytics;
		//overwrite=true; DataSource=rds_phanik_conn; Role=; EnableXmla=false;
		//DynamicSchemaProcessor=com.ls.logging.TerritoryDynamicSchemaProcessor; Locale=en_US
		// parse Datasource=xxxx; part, replace with Datasource=strdatabasedetail
		connectInfo.put("DataSource", strDatabaseDetail);
		System.out.println("Schema connectInfo updated ==> " + connectInfo);
		System.out.println("DSP SCHEMA DETAIL ==> " + strSchemaDetail);
		schema = schema.replaceAll("%userschema%", strSchemaDetail);
		logger.debug("Final Schema Name ==> " + schema);
		System.out.println("Final Schema Name ==> " + schema);
		//System.out.println("Final strUserName ==> " + schema);
		logger.debug("Original session PracticeId===> "+practiceID);
		logger.debug("Original session UserId===> "+strUserId);
		logger.debug("Original session UserName===> "+strUserName);
		logger.debug("Original session PracticeName===> "+strPracticeName);
		logger.debug("Original session DatabaseDetail===> "+strDatabaseDetail);
		logger.debug("Original session SchemaDetail===> "+strSchemaDetail);
		System.out.println("Original session SchemaDetail===> "+strSchemaDetail);
		return schema;
	}

}

