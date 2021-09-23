package com.syrisa.utility;

import com.syrisa.utility.service.NumberGenerateService;

import java.util.Random;

public class NumberGenerate {
    private NumberGenerate(){}

    public static final NumberGenerateService generate = (length) ->{
        StringBuilder builder = new StringBuilder();
        builder.append(1+new Random().nextInt(8));
        for (int i=0;i<length;i++){
            builder.append(new Random().nextInt(10));
        }
        return Long.parseLong(builder.toString());
    };
}
