package com.syrisa.gamearea;

import com.syrisa.player.PlayerOrder;
import com.syrisa.question.Question;

import java.util.*;
import java.util.function.Predicate;

public class GameArea {
    public static List<String> players = new ArrayList<>();
    public static Map<String, List<Question>> questionPool;
    public static List<String> questionType;
    private String[] answerArray;
    private boolean isAnswerFound = false;
    private int count = 0;
    private Scanner scanner = new Scanner(System.in);
    private int countVowel = 0;
    private int countConsonant = 0;
    private int countSpace = 0;
    private Integer playerOrderKept = 0;
    private List<String> choosedConsonantOrVowelAlphabet = new ArrayList<>();
    private Boolean isCountConsonantZero = false;
    private Boolean isCountVowelZero = false;
    Map<String, Integer> playerPoints = new HashMap<>();
    String point;
    Integer pointNum;

    private PlayerOrder setPlayerOrder(String player, boolean state) {
        PlayerOrder order = new PlayerOrder();
        order.setPlayerName(player);
        order.setPlayerStatus(state);
        return order;
    }

    public String[] drawGameArea(String[] answer) {
        System.out.println("******************************************************************");
        for (int i = 0; i < answer.length; i++) {
            System.out.print("[" + answer[i] + "]");
        }
        System.out.println();
        System.out.println("******************************************************************");
        return answer;
    }

    public Boolean findAlphabet(String alphabet, String answer, String[] answerArray, Integer playerOrder) {
        count = 0;
        for (int i = 0; i < answer.length(); i++) {
            if (alphabet.equals(String.valueOf(answer.charAt(i)))) {
                count++;
                answerArray[i] = alphabet;
            }
        }
        System.out.println(alphabet + " character " + count + " piece");
        pointNum *= count;
        System.out.println(players.get(playerOrder) + " earn " + pointNum + " points");
        pointCollect(playerOrder, pointNum);
        drawGameArea(answerArray);
        return isCharactersHaveInAnswer.test(count);
    }

    private void pointCollect(Integer playerOrder, Integer pointNum) {
        String playerName = players.get(playerOrder);
        if (!playerPoints.containsKey(playerName)) {
            playerPoints.put(playerName, pointNum);
        } else {
            int point = playerPoints.get(playerName) + pointNum;
            playerPoints.put(playerName, point);
        }
    }
    
    private final Predicate<Integer> isCharactersHaveInAnswer = count -> count != 0;
    
    public void pointBoard(){
        if (playerPoints.isEmpty()){
            System.out.println("Points table not create yet.");
        }else{
            Set<String> keys = playerPoints.keySet();
            for (String key:keys) {
                System.out.println(key+" : Point : "+playerPoints.get(key));
            }
        }
    }
    
    public void playGame(){
        System.out.println("\n**************************************--> Welcome To Word Game <--**************************************");
        while (true){
            System.out.println("Game Exit : Q\nContine : C\nPoint Table : P");
            System.out.print("Choose : ");
            String choose = scanner.next();
            switch (choose.toUpperCase()){
                case "Q":
                    System.out.println("Game is ending");
                    return;
                case "C":
                    startGame();
                    playerOrderKept = 0;
                    isAnswerFound = false;
                    choosedConsonantOrVowelAlphabet = new ArrayList<>();
                    break;
                case "P":
                    pointBoard();
                    break;
                default:
                    System.out.println("Invalid Option : Please Try Again");
                    break;
            }
        }
    }

    private void startGame() {
    }

}
