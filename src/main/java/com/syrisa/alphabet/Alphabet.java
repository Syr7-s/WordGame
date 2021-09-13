package com.syrisa.alphabet;

public class Alphabet {
    private static Alphabet instance = null;
    private static final Object LOCK = new Object();

    private Alphabet(){}

    public static Alphabet getInstance(){
        synchronized (LOCK){
            return instance == null ? instance = new Alphabet() : instance;
        }
    }
}
