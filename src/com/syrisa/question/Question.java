package com.syrisa.question;

import lombok.Data;

@Data
public class Question {
    private long questionID;
    private String questionType;
    private String question;
    private String answer;
}
