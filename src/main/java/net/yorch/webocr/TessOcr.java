package net.yorch.webocr;

import java.io.File;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * TessOcr<br>
 * 
 * TessOcr Extract OCR Text<br><br>
 * 
 * Copyright 2016 Jorge Alberto Ponce Turrubiates
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
 * @version    1.0.0, 2016-27-09
 * @author     <a href="mailto:the.yorch@gmail.com">Jorge Alberto Ponce Turrubiates</a>
 */
public class TessOcr {
	/**
	 * Extract OCR Text of Image File
	 * 
	 * @param imageFile String Image Path
	 * @return String OCR Text
	 */
	public static String ocr(String imageFile) {
		String retValue = "";
		
		File image = new File(imageFile);
		
		if (image.exists()) {
			ITesseract instance = new Tesseract();  // JNA Interface Mapping
	        // ITesseract instance = new Tesseract1(); // JNA Direct Mapping
	        
			String dataPath = Utils.getTessPath();
			
			instance.setDatapath(dataPath);
			
	        try {
	        	retValue = instance.doOCR(image);
	        } catch (TesseractException e) {
	            System.err.println(e.getMessage());
	        }
		}
		
		return retValue;
	}
}
