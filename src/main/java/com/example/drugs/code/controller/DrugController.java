package com.example.drugs.code.controller;

import com.example.drugs.code.model.Drug;
import com.example.drugs.code.service.DrugService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DrugController {

    private final DrugService drugService;

    public DrugController(DrugService drugService) {
        this.drugService = drugService;
    }

    @PostMapping(value = "/drugs")
    public Drug insert(@RequestBody Drug drug) {
        return drugService.insert(drug);
    }

    @GetMapping(value = "/drugs")
    public List<Drug> selectAll() {
        return drugService.selectAll();
    }

    @GetMapping(value = "/drugs/{id}")
    public Drug select(@PathVariable(name = "id") int id) {
        return drugService.select(id);
    }

    @PutMapping(value = "/drugs/{id}")
    public Drug update(@PathVariable(name = "id") int id, @RequestBody Drug drug) {
        return drugService.update(drug, id);
    }

    @DeleteMapping(value = "/drugs/{id}")
    public void delete(@PathVariable(name = "id") int id) {
      drugService.delete(id);
    }

    @DeleteMapping(value = "/drugs")
    public void deleteAll() {
        drugService.deleteAll();
    }
}