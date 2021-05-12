FROM ubuntu:18.04
MAINTAINER YoRcH

ENV JAVA_TOOL_OPTIONS -Dfile.encoding=UTF8

RUN apt-get update && apt-get install -y software-properties-common && add-apt-repository -y ppa:alex-p/tesseract-ocr
RUN apt-get install -y tesseract-ocr-all 
RUN apt-get install -y openjdk-8-jdk 

COPY app /opt/

EXPOSE 80

CMD ["java", "-jar", "/opt/webocr.jar"]