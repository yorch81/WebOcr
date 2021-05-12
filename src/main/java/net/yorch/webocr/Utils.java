package net.yorch.webocr;

import java.io.File;

/**
 * Utils<br>
 * 
 * Utils for application<br><br>
 * 
 * Copyright 2021 Jorge Alberto Ponce Turrubiates
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @version    1.0.0, 2021-10-05
 * @author     <a href="mailto:the.yorch@gmail.com">Jorge Alberto Ponce Turrubiates</a>
 */
public class Utils {
	/**
	 * OS Flag
	 */
	private static boolean IS_WINDOWS = false;
	
	/**
	 * Check Operating System
	 */
	public static void checkOS() {
		if (System.getProperty("os.name").contains("Windows"))
			Utils.IS_WINDOWS = true;
	}
	
	/**
	 * Validate if OS is Windows
	 * 
	 * @return boolean
	 */
	public static boolean isWindows() {
		return Utils.IS_WINDOWS;
	}
	
	/**
	 * Gets tessdata path
	 * 
	 * @return tessdata path
	 */
	public static String getTessPath() {
		File file = new File("");
		String currentPath = file.getAbsolutePath();
		String tessPath = currentPath + "\\tessdata\\";
		
		return tessPath;
	}
	
	/**
	 * Gets temporary directory
	 * 
	 * @return temporary directory
	 */
	public static String getTempDir() {
		String tmpDir = "";
		
		if (Utils.IS_WINDOWS)
			tmpDir = System.getenv("TMP") + "\\";
		else
			tmpDir = "/tmp/";
		
		return tmpDir;
	}
	
	/**
	 * Gets extension of uploaded file
	 * 
	 * @param contentType MIME content type
	 * @return extension
	 */
	public static String getExt(String contentType) {
		String[] aCt = contentType.split("/");
		String ext = aCt[1];
		
		return ext;
	}
}
