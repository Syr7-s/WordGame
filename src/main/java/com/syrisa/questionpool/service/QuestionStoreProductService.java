package com.syrisa.questionpool.service;

import com.syrisa.question.Question;

import java.util.List;
import java.util.Map;

public interface QuestionStoreProductService {
    Map<String,List<Question>> addQuestionToPool(Question question);
}
