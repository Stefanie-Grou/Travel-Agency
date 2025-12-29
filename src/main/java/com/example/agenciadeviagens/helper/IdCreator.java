package com.example.agenciadeviagens.helper;

public class IdCreator {

    private static Long currentId = 0L;

    public static Long nextId() {
        return ++currentId;
    }
}
