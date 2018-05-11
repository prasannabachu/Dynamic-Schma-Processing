package com.ls.logging;

public class CallingBatch {
	public static void main(String[] args) {
			Runtime run = Runtime.getRuntime();
		try {
			Process p = run.exec("cmd.exe /c start E:/pentaho123/server/pentaho-server/start-pentaho-debug.bat");
			System.out.println(p.exitValue());
			System.out.println("Done");
		} catch (Exception e) {
			e.printStackTrace();
		}
			System.out.println("FINISHED");
		}
}
	