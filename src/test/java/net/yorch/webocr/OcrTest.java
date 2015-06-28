package net.yorch.webocr;

import junit.framework.TestCase;

/**
 * OcrTest<br>
 * 
 * OcrTest Test for Tesseract OCR<br><br>
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
 * @version    1.0.0, 2015-27-06
 * @author     <a href="mailto:the.yorch@gmail.com">Jorge Alberto Ponce Turrubiates</a>
 */
public class OcrTest extends TestCase {
	
	/**
	 * Tesseract Wrapper Instance
	 */
	TessWrapper wrapper;
	
	/**
	 * Constructor Class
	 */
	public OcrTest() {
		wrapper = new TessWrapper("/usr/bin/", "/home/yorch/Downloads/img/", "/home/yorch/Downloads/txt/");
	}
	
	/**
	 * Test for Tesseract OCR
	 */
	public void testTesseract() {		
		String numbers = wrapper.tesseract("ocr");
			
		assertTrue( ! numbers.isEmpty() );
	}
}
