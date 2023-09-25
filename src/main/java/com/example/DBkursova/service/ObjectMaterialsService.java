package com.example.DBkursova.service;

import com.example.DBkursova.entity.ObjectMaterials;
import com.example.DBkursova.repository.ObjectMaterialsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ObjectMaterialsService {
    List<ObjectMaterials> findAllMaterials(int id);
    ObjectMaterials findById(Long id);

    void save(ObjectMaterials objectMaterials);
    void update(ObjectMaterials objectMaterials, Long id);
    void deleteObjectMaterials(Long id);
}
