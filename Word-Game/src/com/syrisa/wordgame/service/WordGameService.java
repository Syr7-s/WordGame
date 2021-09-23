package com.syrisa.wordgame.service;

import com.syrisa.exceptions.WordGameExceptionService;

public interface WordGameService<T> {
    void accept(T t) throws WordGameExceptionService;
}
