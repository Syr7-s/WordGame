package com.syrisa.question;

import com.syrisa.question.service.QuestionService;
import com.syrisa.questionpool.QuestionPool;
import com.syrisa.utility.NumberGenerate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class QuestionImpl {
    public static Map<String, List<Question>> questionPool = new HashMap<>();
    public static final int NUMBER_LENGTH_FOR_ID = 5;
    private static QuestionService questionAdd = (String questionType, String question, String answer) -> {
        Question questionObject = new Question();
        try {
            questionObject.setQuestionID(NumberGenerate.generate.numberGenerate(NUMBER_LENGTH_FOR_ID));
            questionObject.setQuestionType(questionType);
            questionObject.setQuestion(question);
            questionObject.setAnswer(answer);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return questionObject;
    };

    public static Map<String, List<Question>> readQuestionFromFile() {
        String path = "questions.txt";
        //C:\Users\Casper\Desktop\WordGame\Word-Game\questions.txt
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                write(line.trim());
            }
            writeQuestionPoolToConsole();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questionPool;
    }

    private static void write(String line) {
        String[] questionInfo = line.split(",");
        try {
            Question question = questionAdd.questionAdd(questionInfo[0], questionInfo[1], questionInfo[2]);
            questionPool = QuestionPool.getInstance().pool.addQuestionToPool(question);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void writeQuestionPoolToConsole() {
        Set<String> keys = questionPool.keySet();
        for (String key : keys) {
            List<Question> questions = questionPool.get(key);
            for (Question question:questions) {
                System.out.println(question.toString());
            }
        }
    }
}
