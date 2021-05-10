package net.yorch.webocr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * TessWrapper<br>
 * 
 * Wrapper to execute Tesseract OCR in Linux and Windows<br><br>
 * 
 * Copyright 2015 Jorge Alberto Ponce Turrubiates
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
 * @version    1.0.0, 2015-24-02
 * @author     <a href="mailto:the.yorch@gmail.com">Jorge Alberto Ponce Turrubiates</a>
 */
public class TessWrapper {
	/**
	 * Tesseract OCR Directory
	 */
	private String tesshome = "";
	
	/**
	 * Images Directory
	 */
	private String imgdir = "";
	
	/**
	 * Text Output Directory
	 */
	private String txtdir = "";
	
	/**
	 * Comstructor of Class
	 * 
	 * @param tessHome String For Windows Tesseract OCR Home
	 * @param imgDir String Images Directory
	 * @param txtDir String Text Output Directory
	 */
	public TessWrapper(String tessHome, String imgDir, String txtDir) {
		tesshome = tessHome;
		imgdir = imgDir;
		txtdir = txtDir;
	}

	/**
	 * Executes tesseract Command
	 * 
	 * @param filename String Image File Name
	 * @return String
	 */
	public String tesseract(String filename){
		String retValue = "";
		StringBuffer command = new StringBuffer("");
		
		if (System.getProperty("os.name").contains("Windows")){
			command.append(tesshome);
			command.append("tesseract.exe");
		}
		else{
			command.append("tesseract");
		}
		
		command.append(" ");
		command.append(imgdir);
		command.append(filename);
		command.append(".png ");
		command.append(txtdir);
		command.append(filename);
		
		String[] aCommand; 
		
		if (System.getProperty("os.name").contains("Windows"))
			aCommand = new String[]{"cmd.exe","/c",command.toString()};
		else
			aCommand = new String[]{"/bin/bash","-c",command.toString()};
		
		int status = 1;
		
		try {
			Process tessProc = Runtime.getRuntime().exec(aCommand);
			status = tessProc.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (status == 0)
			retValue = getTextFromFile(filename + ".txt");
			
		return retValue;
	}
	
	/**
	 * Gets Text from File
	 * 
	 * @param filename String Image File Name
	 * @return String
	 */
	private String getTextFromFile(String filename){
		String retValue = "";
		File txtFile = new File(txtdir + filename);
		
		if (txtFile.exists()){
			try {
				String currentLine = "";
				
				@SuppressWarnings("resource")
				BufferedReader reader = new BufferedReader(new FileReader(txtFile));
				
				while ((currentLine = reader.readLine()) != null) {
					retValue = retValue + currentLine;
				}
				
				reader = null;
			} catch (Exception e) {
				retValue = "";
				e.printStackTrace();
			}
		}
		
		return retValue.trim();
	}
}
