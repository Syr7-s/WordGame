package com.syrisa.questionpool;

import com.syrisa.question.Question;
import com.syrisa.questionpool.service.QuestionStoreProductService;

import java.util.ArrayList;

public class QuestionStoreProduct {
    QuestionStoreProductService storeProduct = type ->{
        switch (type){
            case "Actor":
            case "Actress":
            case "Sportier":
            case "Movie":
            case "Single":
                return new ArrayList<>();
            default:
                throw new RuntimeException("Exception Occurred");
        }
    };
}
