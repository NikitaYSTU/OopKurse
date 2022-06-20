package com.example.drugs.code.service;

import com.example.drugs.code.model.Drug;

import java.util.List;

public interface DrugService {

    Drug insert(Drug book);

    List<Drug> selectAll();

    Drug select(int id);

    Drug update(Drug book, int id);

    void delete(int id);

    void deleteAll();
}