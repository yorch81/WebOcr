package net.yorch.webocr;

/**
 * WebOcrApp<br>
 * 
 * Application Class<br><br>
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
public class WebOcrApp {

	public static void main(String[] args) {
		String sPort = System.getenv("OCR_PORT");
		
		if (sPort == null)
			sPort = "80";
		
		System.out.println("WebOcr is running on port " + sPort);
		
		WebApp web = new WebApp();
		web.start(Integer.valueOf(sPort));
		
        //TessWrapper wrapper = new TessWrapper("/usr/bin/", "/home/yorch/Downloads/img/", "/home/yorch/Downloads/txt/");
        
        //System.out.println(wrapper.tesseract("ocr"));
		
		// Create Environment Variable
		// export JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF8
	}

}
