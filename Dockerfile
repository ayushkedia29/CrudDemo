FROM openjdk:8

ARG HOST
ARG PORT
ENV host=$HOST
ENV port=$PORT

ADD target/crud-demo-jar-with-dependencies.jar ./crud-demo-jar-with-dependencies.jar

EXPOSE $PORT

CMD ["sh","-c","java -jar crud-demo-jar-with-dependencies.jar"]