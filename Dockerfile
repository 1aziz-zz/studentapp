FROM anapsix/alpine-java:latest
#COPY $TRAVIS_BUILD_DIR/build/libs /home
COPY build/libs /home
WORKDIR /home
CMD [ "java", "-jar", "studentapp-1.0.jar" ] 
EXPOSE 9000
