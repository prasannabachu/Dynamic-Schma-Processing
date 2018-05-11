package com.ls.logging;

public class HelloWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyTenantedDatasourceService serv = new MyTenantedDatasourceService();
		serv.clearDataSource("prasanna");
	}

}
