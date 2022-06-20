package com.example.drugs.code.service;

import com.example.drugs.code.exception.DrugNotFoundException;
import com.example.drugs.code.model.Drug;
import com.example.drugs.code.repository.DrugRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugServiceImpl implements DrugService {

    private final DrugRepository repository;

    public DrugServiceImpl(DrugRepository repository) {
        this.repository = repository;
    }

    @Override
    public Drug insert(Drug Drug) {
        Drug.setId(Drug.getId());
        return repository.save(Drug);
    }

    @Override
    public List<Drug> selectAll() {
        return repository.findAllByOrderByIdAsc();
    }

    @Override
    public Drug select(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new DrugNotFoundException(id));
    }

    @Override
    public Drug update(Drug newDrug, int id) {
        return repository.findById(id)
                .map(drug -> {
                    drug.setName(newDrug.getName());
                    drug.setAmount(newDrug.getAmount());
                    drug.setRecipeRequired(newDrug.getRecipeRequired());
                    drug.setPurpose(newDrug.getPurpose());
                    drug.setPrice(newDrug.getPrice());
                    drug.setType(newDrug.getType());
                    return repository.save(drug);
                })
                .orElseThrow(() -> new DrugNotFoundException(id));
    }

    @Override
    public void delete(int id) {
        if (repository.findById(id).isPresent()){
            repository.deleteById(id);
        } else {
            throw new DrugNotFoundException(id);
        }
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

}