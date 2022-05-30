FROM tomcat:latest
COPY target/spring-boot-security-jwt-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/sprin.war
EXPOSE 8091
#CMD ['./usr/local/tomcat/bin/startup.sh']
