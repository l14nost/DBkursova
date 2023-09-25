package com.example.DBkursova.service;

import com.example.DBkursova.entity.Crew;
import com.example.DBkursova.entity.Equipment;
import com.example.DBkursova.entity.Materials;
import com.example.DBkursova.repository.EquipmentRepo;
import com.example.DBkursova.repository.MaterialsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MaterialsService {
    private final MaterialsRepo materialsRepo;
    public List<Materials> findAll(){
        return materialsRepo.findAll();
    }
    public Materials findById(int id){
        return materialsRepo.findById(id).orElse(null);
    }
    public void save(Materials materials){
        materialsRepo.save(materials);
    }

    public void update(Materials materials, int id){
        Optional<Materials> materialsOptional = materialsRepo.findById(id);
        if ( materialsOptional.isPresent()){
            Materials materialsUpdate = materialsOptional.get();
            if (materials.getType()!=null){
                materialsUpdate.setType(materials.getType());
            }
            if (materials.getName()!=null){
                materialsUpdate.setName(materials.getName());
            }
            if (materials.getQuantity()!=0){
                materialsUpdate.setQuantity(materials.getQuantity());
            }
            materialsRepo.saveAndFlush(materialsUpdate);
        }
    }

    public List<Materials> query3(String name){
        return materialsRepo.findAllLike(name);
    }
    public List<Materials> query6(int param1, int param2){
        return materialsRepo.findAllBetween(param1,param2);
    }
    public int query8(int count){
        return materialsRepo.countMaterial(count);
    }

    public List<Object[]> query17(int idObject){
        return materialsRepo.unionMatEqu(idObject);
    }

    public void deleteMaterial(int id){
        materialsRepo.deleteById(id);
    }
}
