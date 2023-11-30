FROM tomee:9-alpine-plume
COPY target/jakartaee-hello-world.war /usr/local/tomee/webapps/

