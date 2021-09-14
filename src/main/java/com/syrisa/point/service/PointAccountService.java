package com.syrisa.point.service;

import com.syrisa.question.Question;

public interface PointAccountService {
    int pointAccount(Question question, long time) throws Exception;
}
