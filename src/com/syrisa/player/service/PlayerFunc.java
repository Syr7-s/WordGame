package com.syrisa.player.service;

public interface PlayerFunc<T,R> {
    R collect(T t);
}
