# WebOCR #

## Description ##
Web implementation for Tesseract OCR

## Requirements ##
* [Java](https://www.java.com/es/download/)
* [Spark](http://www.sparkjava.com/)
* [Tess4j](http://tess4j.sourceforge.net/)

## Build ##
Execute
~~~
mvn package
~~~

## Developer Documentation ##
Execute
~~~
mvn javadoc:javadoc
~~~

## Testing ##
With cURL
~~~
curl -F "image=@C:/path/to/images/ocr.png" http://localhost/extract
~~~

With browser open
~~~
http://localhost/test
~~~

## Installation ##
Generate and execute jar.

Download training eng.traineddata on tessdata directory

## Notes ##
The default port is 80, but you can change it if create an environment variable named OCR_PORT.
	
## References ##
http://www.sparkjava.com/

https://code.google.com/p/tesseract-ocr/

P.D. Let's go play !!!







