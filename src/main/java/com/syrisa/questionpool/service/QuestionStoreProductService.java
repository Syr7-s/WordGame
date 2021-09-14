package com.syrisa.questionpool.service;

import com.syrisa.question.Question;

import java.util.List;
import java.util.Map;

public interface QuestionStoreProductService {
    List<Question> questionList(String type);
}
