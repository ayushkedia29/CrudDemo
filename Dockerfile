FROM openjdk:8

ADD target/crud-demo-jar-with-dependencies.jar ./crud-demo-jar-with-dependencies.jar

EXPOSE 8338

CMD ["sh","-c","java -jar crud-demo-jar-with-dependencies.jar"]