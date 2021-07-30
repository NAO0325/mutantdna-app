FROM openjdk:8
VOLUME /tmp
EXPOSE 8084
ADD ./target/mutant_dna-0.0.1-SNAPSHOT.jar mutantdna-app.jar
ENTRYPOINT ["java","-jar", "/mutantdna-app.jar"]