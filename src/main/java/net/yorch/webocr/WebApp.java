package net.yorch.webocr;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;

import org.json.JSONObject;

public class WebApp {

	public WebApp() {
	}
	
	public void start(int webPort) {
		port(webPort);
		
		get("/", (req, res) -> {
			JSONObject jRes = new JSONObject();
			
			jRes.put("MSG", "Welcome to WebOCR Application !!!");
			jRes.put("TEST_URL", "http://localhost/test");
			jRes.put("TEST_CURL", "curl -F \"image=@C:/path/from/image.png\" http://localhost/extract");
			
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
			    
				retOcr = TessOcr.ocr(ocrFile);
				
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
