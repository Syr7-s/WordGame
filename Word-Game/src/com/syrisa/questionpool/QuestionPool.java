package com.syrisa.questionpool;

import com.syrisa.question.Question;
import com.syrisa.questionpool.service.QuestionPoolService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionPool {
    private static QuestionPool instance = null;
    private static final Object LOCK = new Object();

    private Map<String, List<Question>> questionPool = new HashMap<>();

    public static QuestionPool getInstance() {
        synchronized (LOCK) {
            return instance == null ? instance = new QuestionPool() : instance;
        }
    }

    public QuestionPoolService pool = (question) -> {
        if (!questionPool.containsKey(question.getQuestionType())) {
            List<Question> questionList = new QuestionStoreProduct().storeProduct.questionList(question.getQuestionType());
            questionList.add(question);
            questionPool.put(question.getQuestionType(), questionList);
        } else {
            List<Question> questionList = questionPool.get(question.getQuestionType());
            questionList.add(question);
            questionPool.put(question.getQuestionType(), questionList);
        }
        return questionPool;
    };
}
