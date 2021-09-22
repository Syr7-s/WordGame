FROM openjdk:8
ADD target/WordGame.jar WordGame.jar
ENTRYPOINT ["java","-jar","WordGame.jar"]