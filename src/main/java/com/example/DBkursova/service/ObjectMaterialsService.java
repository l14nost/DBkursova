package com.example.DBkursova.service;

import com.example.DBkursova.entity.ObjectMaterials;
import com.example.DBkursova.repository.MaterialsRepo;
import com.example.DBkursova.repository.ObjectMaterialsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ObjectMaterialsService {
    private final ObjectMaterialsRepo objectMaterialsRepo;

    public List<ObjectMaterials> findAllMaterials(int id){
        return objectMaterialsRepo.findAllMaterials(id);
    }
    public ObjectMaterials findById(int id){
        return objectMaterialsRepo.findById(id).orElse(null);
    }

    public void save(ObjectMaterials objectMaterials){
        objectMaterialsRepo.save(objectMaterials);
    }
    public void update(ObjectMaterials objectMaterials, int id){
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
    public void deleteObjectMaterials(int id){
        objectMaterialsRepo.deleteById(id);
    }
}
