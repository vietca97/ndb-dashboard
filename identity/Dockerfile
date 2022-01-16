FROM maven:3.8.1-jdk-8
COPY identity.cer $JAVA_HOME/jre/lib/security
RUN \
    cd $JAVA_HOME/jre/lib/security \
    && keytool -keystore cacerts -storepass changeit -noprompt -trustcacerts -importcert -alias kttv.gov.vn -file identity.cer
ADD target/nbd-identity.jar nbd-identity.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","nbd-identity.jar"]