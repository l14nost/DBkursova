package com.example.DBkursova.service.impl;

import com.example.DBkursova.entity.Materials;
import com.example.DBkursova.repository.MaterialsRepo;
import com.example.DBkursova.service.MaterialsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MaterialsServiceImpl implements MaterialsService {
    private final MaterialsRepo materialsRepo;
    @Override
    public List<Materials> findAll(){
        return materialsRepo.findAll();
    }
    @Override
    public Materials findById(Long id){
        return materialsRepo.findById(id).orElse(null);
    }
    @Override
    public void save(Materials materials){
        materialsRepo.save(materials);
    }

    @Override
    public void update(Materials materials, Long id){
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

    @Override
    public List<Materials> query3(String name){
        return materialsRepo.findAllLike(name);
    }
    @Override
    public List<Materials> query6(int param1, int param2){
        return materialsRepo.findAllBetween(param1,param2);
    }
    @Override
    public int query8(int count){
        return materialsRepo.countMaterial(count);
    }

    @Override
    public List<Object[]> query17(int idObject){
        return materialsRepo.unionMatEqu(idObject);
    }

    @Override
    public void deleteMaterial(Long id){
        materialsRepo.deleteById(id);
    }
}
