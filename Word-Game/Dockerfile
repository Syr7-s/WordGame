FROM openjdk:8
ADD out/artifacts/Word_Game_jar/Word-Game.jar Word-Game.jar
ADD players.txt players.txt
ADD questions.txt questions.txt
ENTRYPOINT ["java","-jar","Word-Game.jar"]