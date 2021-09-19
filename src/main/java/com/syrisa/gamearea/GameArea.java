package com.syrisa.gamearea;


import com.syrisa.alphabet.service.AlphabetService;
import com.syrisa.player.PlayerOrder;
import com.syrisa.point.service.PointWheel;
import com.syrisa.question.Question;

import java.util.*;
import java.util.function.*;
import java.util.stream.Stream;

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

    public Consumer<String[]> drawGameArea = answer -> {
        System.out.println("******************************************************************");
        for (String s : answer) {
            System.out.print("[" + s + "]");
        }
        System.out.println();
        System.out.println("******************************************************************");
    };

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
        drawGameArea.accept(answerArray);
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

    Predicate<Integer> isCharactersHaveInAnswer = count -> count != 0;

    public void pointBoard() {
        if (playerPoints.isEmpty()) {
            System.out.println("Points table not create yet.");
        } else {
            Set<String> keys = playerPoints.keySet();
            for (String key : keys) {
                System.out.println(key + " : Point : " + playerPoints.get(key));
            }
        }
    }

    public void playGame() {
        System.out.println("\n**************************************--> Welcome To Word Game <--**************************************");
        while (true) {
            System.out.println("Game Exit : Q\nContine : C\nPoint Table : P");
            System.out.print("Choose : ");
            String choose = scanner.next();
            switch (choose.toUpperCase()) {
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
        String questionVarious = questionType.get(new Random().nextInt(questionType.size()));
        System.out.println("Question Type: " + questionVarious);
        if (questionPool.containsKey(questionVarious)) {
            List<Question> questions = questionPool.get(questionVarious);
            Question question = questions.get(new Random().nextInt(questions.size()));
            System.out.println("Question is that: " + question.getQuestion());
            System.out.println(question.getAnswer().length() + " character.");
            answerArray = new String[question.getAnswer().length()];
            vowelAndConsonantAlphabetCount.accept(question.getAnswer());
            playerStartSpinWheel(question);
        }
    }

    Predicate<Integer> isZero = count -> count == 0;

    private final Consumer<String> vowelAndConsonantAlphabetCount = answer -> {
        countVowel = 0;
        countConsonant = 0;
        countSpace = 0;
        for (int i = 0; i < answer.length(); i++) {
            if (AlphabetService.VOWEL.contains(answer.charAt(i))) {
                countVowel++;
            } else if (AlphabetService.CONSONANT.contains(answer.charAt(i))) {
                countConsonant++;
            } else if (" ".equals(String.valueOf(answer.charAt(i)))) {
                countSpace++;
            } else {
                System.out.println(answer.charAt(i) + " invalid character");
            }
        }
        isCountVowelZero = isZero.test(countVowel);
        isCountConsonantZero = isZero.test(countConsonant);
        System.out.println("Vowel Count : " + countVowel + " Consonant Count : " + countConsonant + " Space Count : " + countSpace);
    };

    private void playerStartSpinWheel(Question question) {
        PlayerOrder order;
        while (!isAnswerFound) {
            order = setPlayerOrder(players.get(playerOrderKept), true);
            System.out.println(order.getPlayerName() + " named player is starting to the game.");
            System.out.println("Spin the wheel........");
            point = PointWheel.pointWheel.get(new Random().nextInt(PointWheel.POINT_WHELL_SIZE));
            if (point.equals("BANKRUPTCY") || point.equals("PASS")) {
                playerOrderKept = playerWillBankruptcyOrPass.apply(playerOrderKept, point).get();
            } else {
                pointNum = Integer.valueOf(point);
                System.out.println(pointNum);
                playerOrderKept = playerWillChooseCharacter.apply(question, order.getPlayerName()).apply(playerOrderKept);
            }
        }
    }

    Function<Integer, Integer> playerOrderFunc = order -> order == 2 ? 0 : (++order);

    BiFunction<Integer, String, Supplier<Integer>> playerWillBankruptcyOrPass = (playerOrder, score) -> () -> {
        System.out.println(players.get(playerOrder) + " is " + score);
        return playerOrderFunc.apply(playerOrder);
    };

    private final Function<String, String> enterTheCharacter = player -> {
        System.out.println();
        System.out.print(player + " is choosing alphabet.(Consonant or Vowel) : ");
        return scanner.next();
    };

    BiFunction<Integer, Question, Consumer<String>> playerChooseACharacter = (playerOrder, question) -> (playerName) -> {
        String selectedCharacter;
        Stream.of(choosedConsonantOrVowelAlphabet).forEach(System.out::println);
        System.out.println();
        if (Boolean.FALSE.equals(isCountConsonantZero)) {
            Stream.of(AlphabetService.CONSONANT).forEach(System.out::print);
            selectedCharacter = enterTheCharacter.apply(playerName);
            boolean isConsonantCharacter = AlphabetService.CONSONANT.contains(selectedCharacter.charAt(0));
            if (isConsonantCharacter) {
                fillTheArrayWithConsonantAlphabet(playerOrder, question, selectedCharacter);
            } else {
                System.out.println("Please enter the Consonant character.");
            }
        } else {
            Stream.of(AlphabetService.VOWEL).forEach(System.out::print);
            selectedCharacter = enterTheCharacter.apply(playerName);
            boolean isVowelCharacter = AlphabetService.VOWEL.contains(selectedCharacter.charAt(0));
            if (isVowelCharacter) {
                fillTheArrayWithVowelAlphabet(playerOrder, question, selectedCharacter);
            } else {
                System.out.println("Please enter the Vowel character");
            }
        }
        isCountConsonantZero = isZero.test(countConsonant);
        isCountVowelZero = isZero.test(countVowel);
        isConsonantAndVowelCharacterCountZero();
    };


    BiFunction<Question, String, Function<Integer, Integer>> playerWillChooseCharacter = (question, name) -> playerOrder -> {
        playerChooseACharacter.apply(playerOrder, question).accept(name);
        return playerOrderFunc.apply(playerOrder);
    };


    private void fillTheArrayWithConsonantAlphabet(Integer playerOrder, Question question, String selectedCharacter) {
        if (!choosedConsonantOrVowelAlphabet.contains(selectedCharacter)) {
            choosedConsonantOrVowelAlphabet.add(selectedCharacter);
            boolean result = findAlphabet(selectedCharacter, question.getAnswer(), answerArray, playerOrder);
            countConsonant = countAlphabetCheck.apply(count, result).apply(countConsonant);
            System.out.println("Count Consonant : " + countConsonant);
            System.out.println(result ? "Character found." : "Character is not found");
            isCountConsonantZero = isZero.test(countConsonant);
        } else {
            System.out.println("Character is said.");
        }
    }

    BiFunction<Integer, Boolean, Function<Integer, Integer>> countAlphabetCheck = (count, result) -> countAlphabet -> Boolean.TRUE.equals(result) ? countAlphabet - count : countAlphabet;

    private void fillTheArrayWithVowelAlphabet(Integer playerOrder, Question question, String selectedCharacter) {
        if (!choosedConsonantOrVowelAlphabet.contains(selectedCharacter)) {
            choosedConsonantOrVowelAlphabet.add(selectedCharacter);
            boolean result = findAlphabet(selectedCharacter, question.getAnswer(), answerArray, playerOrder);
            countVowel = countAlphabetCheck.apply(count, result).apply(countVowel);
            System.out.println("Count Vowel : " + countVowel);
            System.out.println(result ? "Character found." : "Character is not found");
        } else {
            System.out.println("Character is said.");
        }
    }

    private void isConsonantAndVowelCharacterCountZero() {
        if (isCountConsonantZero && isCountVowelZero) {
            for (int i = 0; i < answerArray.length; i++) {
                if (answerArray[i] == null) {
                    answerArray[i] = " ";
                }
            }
            System.out.println("***************************************************************");
            drawGameArea.accept(answerArray);
            System.out.println("***************************************************************");
            System.out.println("Game Over");
            isAnswerFound = true;
        }
    }

}
