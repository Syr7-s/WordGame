package com.syrisa.question.service;

import com.syrisa.question.Question;

public interface QuestionService {
    Question questionAdd(String questionType, String question, String answer);
}
