package net.yorch.webocr;

/**
 * App
 * 
 * Application Class
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
 * @category   App
 * @package    net.yorch.webocr
 * @copyright  Copyright 2015 JAPT
 * @license    http://www.apache.org/licenses/LICENSE-2.0
 * @version    1.0.0, 2015-24-02
 * @author     <a href="mailto:the.yorch@gmail.com">Jorge Alberto Ponce Turrubiates</a>
 */
public class App {
    public static void main( String[] args ) {
        System.out.println("WebOcr Application !!!");
        
        TessWrapper wrapper = new TessWrapper("C:/App/Tesseract-OCR/", "C:/TEMP/img/", "C:/TEMP/txt/");
        
        System.out.println(wrapper.tesseract("scanned"));
    }
}
