package com.syrisa;

import com.syrisa.gamearea.GameArea;
import com.syrisa.player.Player;
import com.syrisa.player.PlayerServiceImpl;
import com.syrisa.question.Question;
import com.syrisa.question.QuestionImpl;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class App {
    static Map<String, List<Question>> questionPool;

    static {
        questionPool = QuestionImpl.readQuestionFromFile();
        GameArea.players = PlayerServiceImpl.readPlayerFromFile().stream().map(Player::getPlayerName).collect(Collectors.toList());
        GameArea.questionType = new ArrayList<>(questionPool.keySet());
        GameArea.questionPool = questionPool;
    }

    public static void main(String[] args) {
        GameArea area = new GameArea();
        area.playGame();
    }
}
