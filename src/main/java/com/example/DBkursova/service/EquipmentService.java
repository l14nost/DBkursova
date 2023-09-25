package com.example.DBkursova.service;

import com.example.DBkursova.entity.Equipment;
import com.example.DBkursova.repository.EquipmentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface EquipmentService {
    List<Equipment> findAll();
    Equipment findById(Long id);

    void update(Equipment equipment, Long id);

    void save(Equipment equipment);

    List<Equipment> query4(String name);

    List<Object[]> query12();

    void deleteEquipment(Long id);
}
