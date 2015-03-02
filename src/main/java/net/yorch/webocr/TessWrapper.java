package net.yorch.webocr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
				
				BufferedReader reader = new BufferedReader(new FileReader(txtFile));
				
				while ((currentLine = reader.readLine()) != null) {
					retValue = retValue + currentLine;
				}
			} catch (Exception e) {
				retValue = "";
				e.printStackTrace();
			}
		}
		
		return retValue.trim();
	}
}
