package com.ls.logging;

import javax.sql.DataSource;

import org.pentaho.platform.api.data.DBDatasourceServiceException;
import org.pentaho.platform.api.engine.IPentahoSession;
import org.pentaho.platform.engine.core.system.PentahoSessionHolder;
import org.pentaho.platform.engine.services.connection.datasource.dbcp.PooledOrJndiDatasourceService;
//import org.pentaho.reporting.libraries.base.util.StringUtils;

public class MyTenantedDatasourceService extends PooledOrJndiDatasourceService {
	private static LSLogger logger = LSLogger.getLSLogger(MyTenantedDatasourceService.class);
	//private String UpdatedDatabase;

	@Override
	public DataSource getDataSource(String dsName) throws DBDatasourceServiceException {
		System.out.println("Original DSName is  " + dsName);
		return super.getDataSource(modifyDsNameForTenancy(dsName));
	}

	@Override
	public void clearDataSource(String dsname) {
		/*System.out.println("Original DSName is  " + dsname);
		dsname = this.UpdatedDatabase;
		super.clearDataSource(modifyDsNameForTenancy(dsname));
		dsname = this.UpdatedDatabase;
		System.out.println("Original DSName is  " + dsname);*/
	}

	private String modifyDsNameForTenancy(String dsName) {
		System.out.println("IDB DATASOURCE STARTED");
		logger.debug("<==== IDB DATASOURCE STARTED ====>");
		logger.debug("Original DSName is===> " + dsName);
		System.out.println("Original DSName is " + dsName);

		IPentahoSession session = PentahoSessionHolder.getSession();
		if (session == null) {
			logger.warn("Session is null; no modifications made to the JNDI dsName.");
			System.out.println("Session is null; no modifications made to the JNDI dsName.");
			return dsName;
		}
		String practiceID = (String) session.getAttribute("PRACTICE_ID").toString();
		logger.debug("PRACTICE_ID--->" + practiceID);
		System.out.println("PRACTICE_ID--->" + practiceID);
		String strUserName = (String) session.getAttribute("USER_NAME");
		logger.debug("USER_NAME--->" + strUserName);
		System.out.println("USER_NAME--->" + strUserName);
		String strUserId = (String) session.getAttribute("USER_ID").toString();;
		logger.debug("strUserId--->" + strUserId);
		System.out.println("strUserId--->" + strUserId);
		String strPracticeName = (String) session.getAttribute("PRACTICE_NAME");
		System.out.println("PRACTICE_NAME---->" + strPracticeName);
		logger.debug("PRACTICE_NAME---->" + strPracticeName);
		String strDatabaseDetail = (String) session.getAttribute("DATABASE_DETAIL");
		//this.UpdatedDatabase = strDatabaseDetail;
		System.out.println("strDatabaseDetail--->" + strDatabaseDetail);
		logger.debug("strDatabaseDetail--->" + strDatabaseDetail);
		String strSchemaDetail = (String) session.getAttribute("SCHEMA_DETAIL");
		System.out.println("strSchemaDetail---->" + strSchemaDetail);
		logger.debug("strSchemaDetail---->" + strSchemaDetail);
		// String tenantId = "141";
		if ((practiceID == null || practiceID.length() == 0) ||
				(strUserId == null || strUserId.length() == 0)||
				(strDatabaseDetail == null || strDatabaseDetail.length() == 0)||
				(strSchemaDetail == null || strSchemaDetail.length() == 0)) {
			logger.warn("ID not found; no modifications made to the JNDI dsName.");
			System.out.println("ID not found; no modifications made to the JNDI dsName.");
			return dsName;
		}
		String dsname = strDatabaseDetail;
		logger.debug("New DSName is===> " + dsname);
		logger.debug("Session PRACTICE_ID is===> " + practiceID);
		logger.debug("Session USER_NAME is===> " + strUserName);
		logger.debug("Session USER_ID is===> " + strUserId);
		logger.debug("Session PRACTICE_NAME is===> " + strPracticeName);
		logger.debug("Session DATABASE_DETAIL is===> " + strDatabaseDetail);
		logger.debug("Session SCHEMA_DETAIL is===> " + strSchemaDetail);
		logger.debug("<==== IDB DATASOURCE ENDED ====>");

		System.out.println("New DSName is " + dsname);
		return dsname;
	}
}
