package com.example.DBkursova.service;

import com.example.DBkursova.entity.Materials;
import com.example.DBkursova.repository.MaterialsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface MaterialsService {
    List<Materials> findAll();
    Materials findById(Long id);
    void save(Materials materials);

    void update(Materials materials, Long id);

    List<Materials> query3(String name);
    List<Materials> query6(int param1, int param2);
    int query8(int count);

    List<Object[]> query17(int idObject);

    void deleteMaterial(Long id);
}
