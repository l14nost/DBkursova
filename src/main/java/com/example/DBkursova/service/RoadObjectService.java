package com.example.DBkursova.service;

import com.example.DBkursova.entity.Equipment;
import com.example.DBkursova.entity.ObjectMaterials;
import com.example.DBkursova.entity.RoadObject;
import com.example.DBkursova.repository.MaterialsRepo;
import com.example.DBkursova.repository.ObjectMaterialsRepo;
import com.example.DBkursova.repository.RoadObjectsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoadObjectService {
    private final RoadObjectsRepo roadObjectsRepo;
    private final ObjectMaterialsRepo objectMaterialsRepo;
    public List<RoadObject> findAll(){
        return roadObjectsRepo.findAllQuery();
    }
    public List<RoadObject> findAllMaterials(){
        return roadObjectsRepo.findAllQuery();
    }

    public void save(RoadObject roadObject){
        roadObjectsRepo.save(roadObject);
    }

    public RoadObject findById(int id) {
        return roadObjectsRepo.findById(id).orElse(null);
    }


    public List<RoadObject> findAllCrew(int idCrew){
        return roadObjectsRepo.findAllCrew(idCrew);
    }

    public void update(RoadObject object, int id){
        Optional<RoadObject> objectOptional = roadObjectsRepo.findById(id);
        if (objectOptional.isPresent()){
            RoadObject objectUpdate = objectOptional.get();
            if (object.getStreet()!=null){
                objectUpdate.setStreet(object.getStreet());
            }

            if (object.getDamageType()!=null){
                objectUpdate.setDamageType(object.getDamageType());
            }

            if (object.getRepairDate()!=null){
                objectUpdate.setRepairDate(object.getRepairDate());
            }if (object.getCrew()!=null){
                objectUpdate.setCrew(object.getCrew());
            }
            if (object.getEquipment()!=null){
                objectUpdate.setEquipment(object.getEquipment());

            }
           roadObjectsRepo.saveAndFlush(objectUpdate);
        }
    }
    public List<RoadObject> query5(LocalDate param1, LocalDate param2){
        return roadObjectsRepo.findAllBetween(param1,param2);
    }
    public int query7(LocalDate param){
        return roadObjectsRepo.countObjectByData(param);
    }
    public List<Object[]> query10(){
        return roadObjectsRepo.countCrew();
    }

    public List<RoadObject> query13(String name){return roadObjectsRepo.findByMat(name);}
    public List<RoadObject> query14(int count){return roadObjectsRepo.findObjectByMat(count);}


    public List<RoadObject> query15(){
        return roadObjectsRepo.findByMatNull();
    }

    public List<RoadObject> query16(){
        return roadObjectsRepo.findAllEquNull();
    }

    public void deleteRoadObject(int id){
        List<ObjectMaterials> objectMaterials = objectMaterialsRepo.findAll();
        for (int i = 0;i<objectMaterials.size();i++){
            if (objectMaterials.get(i).getObject().getIdObject()==id){
                objectMaterialsRepo.deleteById(objectMaterials.get(i).getIdObjectMaterials());
            }
        }
        roadObjectsRepo.deleteById(id);
    }

    public void deleteRoadObjectEquipment(int id,int idObject){
        RoadObject object = roadObjectsRepo.findById(idObject).orElse(null);
        if (object!=null){
            List<Equipment> equipment = object.getEquipment();
            equipment.remove(id);
            roadObjectsRepo.saveAndFlush(object);
        }

    }

}
