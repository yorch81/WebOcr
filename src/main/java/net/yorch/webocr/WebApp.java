package net.yorch.webocr;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;

import org.json.JSONObject;

/**
 * WebApp<br>
 * 
 * Spark webservice application<br><br>
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
public class WebApp {

	/**
	 * Constuctor
	 */
	public WebApp() {
	}
	
	/**
	 * Start web application
	 * 
	 * @param webPort Listen port
	 */
	public void start(int webPort) {
		port(webPort);
		
		get("/", (req, res) -> {
			JSONObject jRes = new JSONObject();
			
			jRes.put("MSG", "Welcome to WebOCR REST Webservice !!!");
			
			res.type("application/json");
    		res.status(200);
    		
    		return jRes.toString();
		});
		
		get("/test", (req, res) -> {
			String form = "<form method='post' action='/extract' enctype='multipart/form-data'>"
	                + "    <input type='file' name='image'>"
	                + "    <button>Extract</button>"
	                + "</form>";
			
			return form;
		});
		
		post("/extract", (req, res) -> {
			String tmp = Utils.getTempDir();
			
			MultipartConfigElement multipartConfigElement = new MultipartConfigElement(tmp);
			
			req.attribute("org.eclipse.jetty.multipartConfig", multipartConfigElement);
        	
			String retOcr = "";
			
			JSONObject jRes = new JSONObject();
			
        	try {        		
				Part file = req.raw().getPart("image");
				
				String ct = file.getContentType();
				String ext = Utils.getExt(ct);
				
				String fileName = "ocrimg_" + String.valueOf(System.currentTimeMillis()) + "." + ext;
				
				file.write(fileName);	
				
				String ocrFile = tmp + fileName;
			    
				if (Utils.isWindows()) {
					retOcr = TessOcr.ocr(ocrFile);
				}
				else {
					// Create Environment Variable
					// export JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF8
					
					TessWrapper wrapper = new TessWrapper("/usr/bin/", tmp, tmp);
					retOcr = wrapper.tesseract(fileName);
				}
				
				jRes.put("ERROR", 0);
				jRes.put("OCR_TEXT", retOcr);
				
				file = null;
				ct = null;
				ext = null;
				ocrFile = null;
			} catch (Exception e) {
				jRes.put("ERROR", 0);
				jRes.put("ERROR_MSG", e.getMessage());
				jRes.put("OCR_TEXT", "");
			}
        	
        	res.type("application/json");
    		res.status(200);
    		
        	return jRes.toString();
		});
	}
}
