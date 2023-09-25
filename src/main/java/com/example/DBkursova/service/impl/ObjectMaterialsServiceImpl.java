package com.example.DBkursova.service.impl;

import com.example.DBkursova.entity.ObjectMaterials;
import com.example.DBkursova.repository.ObjectMaterialsRepo;
import com.example.DBkursova.service.ObjectMaterialsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ObjectMaterialsServiceImpl implements ObjectMaterialsService {
    private final ObjectMaterialsRepo objectMaterialsRepo;

    @Override
    public List<ObjectMaterials> findAllMaterials(int id){
        return objectMaterialsRepo.findAllMaterials(id);
    }
    @Override
    public ObjectMaterials findById(Long id){
        return objectMaterialsRepo.findById(id).orElse(null);
    }

    @Override
    public void save(ObjectMaterials objectMaterials){
        objectMaterialsRepo.save(objectMaterials);
    }
    @Override
    public void update(ObjectMaterials objectMaterials, Long id){
        Optional<ObjectMaterials> objectMaterialsOptional = objectMaterialsRepo.findById(id);
        if (objectMaterialsOptional.isPresent()){
            ObjectMaterials objectMaterialsUpdate = objectMaterialsOptional.get();
            if (objectMaterials.getMaterials()!=null){
                objectMaterialsUpdate.setMaterials(objectMaterials.getMaterials());
            }
            if (objectMaterials.getQuantity()!=0){
                objectMaterialsUpdate.setQuantity(objectMaterials.getQuantity());
            }
            objectMaterialsRepo.saveAndFlush(objectMaterialsUpdate);
        }
    }
    @Override
    public void deleteObjectMaterials(Long id){
        objectMaterialsRepo.deleteById(id);
    }
}
