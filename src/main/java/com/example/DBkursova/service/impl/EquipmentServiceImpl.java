package com.example.DBkursova.service.impl;

import com.example.DBkursova.entity.Equipment;
import com.example.DBkursova.repository.EquipmentRepo;
import com.example.DBkursova.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {
    private final EquipmentRepo equipmentRepo;

    public List<Equipment> findAll(){
        return equipmentRepo.findAll();
    }
    public Equipment findById(Long id){
        return equipmentRepo.findById(id).orElse(null);
    }

    public void update(Equipment equipment, Long id) {
        Optional<Equipment> equipmentOptional = equipmentRepo.findById(id);
        if (equipmentOptional!=null){
            Equipment equipmentUpdate = equipmentOptional.get();
            if (equipment.getName()!=null){
                equipmentUpdate.setName(equipment.getName());
            }
            if (equipment.getType()!=null){
                equipmentUpdate.setType(equipment.getType());
            }
            if (equipment.getCondition()!=null){
                equipmentUpdate.setCondition(equipment.getCondition());
            }
            if (equipment.getCost()!=0){
                equipmentUpdate.setCost(equipment.getCost());
            }
            equipmentRepo.saveAndFlush(equipmentUpdate);
        }
    }

    public void save(Equipment equipment) {
        equipmentRepo.save(equipment);
    }

    public List<Equipment> query4(String name){
        return equipmentRepo.findAllLike(name);
    }

    public List<Object[]> query12(){
        return equipmentRepo.countInObject();
    }

    public void deleteEquipment(Long id){
        equipmentRepo.deleteById(id);
    }
}
