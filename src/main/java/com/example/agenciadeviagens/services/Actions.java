package com.example.agenciadeviagens.services;

import java.util.List;

public interface Actions<T> {
    T create(String t);
    T fetch(Long id, List<T> list);
}
