package com.example.drugs.code.exception;

public class DrugNotFoundException extends RuntimeException {

    public DrugNotFoundException(int id) {
        super("Таких лекарств с id = " + id + " не существует!");
    }
}