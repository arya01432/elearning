FROM node:16-alpine

RUN apk add -U git curl

# FROM maslick/minimalka:jdk11
# VOLUME /tmp
# EXPOSE 8083
# ADD target/elearning-0.0.1.jar elearning-0.0.1.jar
# ENTRYPOINT ["java","-jar","elearning-0.0.1.jar"] 
