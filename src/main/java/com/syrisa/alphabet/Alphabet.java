package com.syrisa.alphabet;

import com.syrisa.alphabet.service.AlphabetService;

public class Alphabet implements AlphabetService {
    private static Alphabet instance = null;
    private static final Object LOCK = new Object();

    private Alphabet(){}

    public static Alphabet getInstance(){
        synchronized (LOCK){
            return instance == null ? instance = new Alphabet() : instance;
        }
    }
}
