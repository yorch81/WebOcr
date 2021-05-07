package net.yorch.webocr;

import java.io.File;

public class Utils {
	public static String getTessPath() {
		File file = new File("");
		String currentPath = file.getAbsolutePath();
		String tessPath = "";
		
		if (System.getProperty("os.name").contains("Windows"))
			tessPath = currentPath + "\\tessdata\\";
		else
			tessPath = currentPath + "/tessdata/";
		
		return tessPath;
	}
	
	public static String getTempDir() {
		String tmpDir = "";
		
		if (System.getProperty("os.name").contains("Windows"))
			tmpDir = System.getenv("TMP") + "\\";
		else
			tmpDir = "/tmp/";
		
		return tmpDir;
	}
	
	public static String getExt(String contentType) {
		String[] aCt = contentType.split("/");
		String ext = aCt[1];
		
		return ext;
	}
}
