package net.yorch.webocr;

import static spark.Spark.post;
import static spark.SparkBase.setPort;
import static spark.Spark.get;

import java.io.IOException;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.User;
import facebook4j.auth.AccessToken;
import facebook4j.auth.OAuthAuthorization;
import facebook4j.auth.OAuthSupport;
import facebook4j.conf.Configuration;
import facebook4j.conf.ConfigurationBuilder;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import static spark.Spark.setIpAddress;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

/**
 * WebApp<br>
 *
 * Web Application<br><br>
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
 * @version    1.0.0, 2015-15-07
 * @author     <a href="mailto:the.yorch@gmail.com">Jorge Alberto Ponce Turrubiates</a>
 */
public class WebApp {

	public WebApp() {
		
		ConfigurationBuilder confBuilder = new ConfigurationBuilder();

        confBuilder.setDebugEnabled(true);
        confBuilder.setOAuthAppId("709488202488871");
        confBuilder.setOAuthAppSecret("cf2a470921280967d91eecc0714058cc");
        confBuilder.setUseSSL(true);
        confBuilder.setJSONStoreEnabled(true);


        final Configuration configuration = confBuilder.build();
        
		final Facebook facebook = new FacebookFactory(configuration).getInstance();
    	
    	final String IP_ADDRESS = System.getenv("OPENSHIFT_DIY_IP") != null ? System.getenv("OPENSHIFT_DIY_IP") : "localhost";
    	final int PORT = System.getenv("OPENSHIFT_DIY_PORT") != null ? Integer.parseInt(System.getenv("OPENSHIFT_DIY_PORT")) : 8080;

    	setIpAddress(IP_ADDRESS);

		/**
	     * Port Applicacion
	     */		
		setPort(PORT);
		
		/**
	     * Public Files Path
	     */	
		Spark.staticFileLocation("/public");
		
		/**
	     * Path /
	     */	
		get("/", new Route() {
	        @Override
	        public Object handle(Request request, Response response) {	        	
	        	/*Facebook facebook = new FacebookFactory().getInstance();
	        	facebook.setOAuthAppId("1621799478097585", "585fc241a31c74a350e9db01bc33ca3c");
	        	facebook.setOAuthCallbackURL("http://localhost:8080/fb");
	        	facebook.setOAuthPermissions("email");
	        	
	        	AccessToken accessToken = null;
	            try{
	                OAuthSupport oAuthSupport = new OAuthAuthorization(configuration );
	                accessToken = oAuthSupport.getOAuthAppAccessToken();
	                
	                facebook.setOAuthAccessToken(accessToken);
	            }catch (FacebookException e) {
	                return e.getMessage();
	            }
	            
	        	String strlogin = facebook.getOAuthAuthorizationURL("http://localhost:8080/fb");
	        	
	        	request.session().attribute("facebook", facebook);
	        	
	            StringBuffer callbackURL = new StringBuffer(request.url());
	            
	            int index = callbackURL.lastIndexOf("/");
	            callbackURL.replace(index, callbackURL.length(), "").append("/callback");
	            
	            //response.sendRedirect(facebook.getOAuthAuthorizationURL(callbackURL.toString()));
	            String callback = facebook.getOAuthCallbackURL();
	           
	            response.redirect(strlogin);
	            
	            return strlogin;*/
	            
	        	/*
	            Twitter twitter = TwitterFactory.getSingleton();
	            twitter.setOAuthConsumer("rj990vv20WAwBriRtirBhMSzp", "n2INiuJpV9uAioHKxTfANeJ4JDdq2ZIogH5FUwGocFVJsp0avn");
	            
	            request.session().attribute("twitter", twitter);
	            
	            try {
	            	RequestToken requestToken = twitter.getOAuthRequestToken("http://yorch.ngrok.com/tw");
	            	request.session().attribute("requestToken", requestToken);
	            	
	            	String authUrl = requestToken.getAuthenticationURL();
	            	response.redirect(authUrl);
				} catch (TwitterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
	            
	            return "WebOCR on Openshift !!!";
	        }
	    });
		
		get("/fb", new Route() {
	        @Override
	        public Object handle(Request request, Response response) {
	        	//Facebook facebook = (Facebook) request.session().attribute("facebook");
	            
	        	String oauthCode = request.queryParams("code");           
	            
	        	Facebook facebook = new FacebookFactory().getInstance();
	        	facebook.setOAuthAppId("1621799478097585", "585fc241a31c74a350e9db01bc33ca3c");
	        	facebook.setOAuthCallbackURL("http://localhost:8080/fb");
	        		        	
	            try {
	                AccessToken token = facebook.getOAuthAccessToken(oauthCode);
	                facebook.setOAuthAccessToken(token);
	                
	                User me = facebook.getMe();
	                
	                String info  = me.getEmail() + "-" + me.getName() + "-" + me.getId();
	                
	                return info;
	            } catch (FacebookException e) {
	                return e.getErrorMessage();
	            }
	            
	        }
	    });
		
		get("/tw", new Route() {
	        @Override
	        public Object handle(Request request, Response response) {    
	            Twitter twitter = (Twitter) request.session().attribute("twitter");
	            
	        	RequestToken requestToken = (RequestToken) request.session().attribute("requestToken");
	        	
	        	String verifier = request.queryParams("oauth_verifier"); 
	        	
	        	try {
	                twitter.getOAuthAccessToken(requestToken, verifier);
	                request.session().attribute("requestToken", null);
	                
	                twitter4j.User me  = (twitter4j.User) twitter.verifyCredentials();
	                
	                String info  = me.getScreenName() + "-" + me.getName() + "-" + me.getId();
	                
	                return info;
	                
	            } catch (TwitterException e) {
	                return e.getErrorMessage();
	            }
	        }
	    });
		
		/**
	     * Subir Archivo
	     * Example:  curl -F "webcam=@/home/yorch/tmp/mory.jpg" http://localhost:8080/upload
	     */	
		post("/upload", new Route() {
	        @Override
	        public Object handle(Request request, Response response) {
	        	MultipartConfigElement multipartConfigElement = new MultipartConfigElement("/home/yorch/tmp/");
	        	
	        	request.raw().setAttribute("org.eclipse.multipartConfig", multipartConfigElement);
	        	String retResponse = "";
	        	
	        	try {
					Part file = request.raw().getPart("webcam");
					//long fileSize = file.getSize();
					//String fileName = file.getName();
					
					file.write("yorch.jpg");
					
					retResponse = TessOcr.ocr("/home/yorch/tmp/yorch.jpg");
					
				} catch (IOException | ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	   
	            return retResponse;
	        }
	    });
	}

}

