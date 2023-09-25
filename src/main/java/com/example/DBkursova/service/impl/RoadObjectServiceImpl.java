package com.example.DBkursova.service.impl;

import com.example.DBkursova.entity.Equipment;
import com.example.DBkursova.entity.ObjectMaterials;
import com.example.DBkursova.entity.RoadObject;
import com.example.DBkursova.repository.ObjectMaterialsRepo;
import com.example.DBkursova.repository.RoadObjectsRepo;
import com.example.DBkursova.service.RoadObjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoadObjectServiceImpl implements RoadObjectService {
    private final RoadObjectsRepo roadObjectsRepo;
    private final ObjectMaterialsRepo objectMaterialsRepo;
    @Override
    public List<RoadObject> findAll(){
        return roadObjectsRepo.findAllQuery();
    }

    @Override
    public void save(RoadObject roadObject){
        roadObjectsRepo.save(roadObject);
    }

    @Override
    public RoadObject findById(Long id) {
        return roadObjectsRepo.findById(id).orElse(null);
    }


    @Override
    public List<RoadObject> findAllCrew(int idCrew){
        return roadObjectsRepo.findAllCrew(idCrew);
    }

    @Override
    public void update(RoadObject object, Long id){
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
    @Override
    public List<RoadObject> query5(LocalDate param1, LocalDate param2){
        return roadObjectsRepo.findAllBetween(param1,param2);
    }
    @Override
    public int query7(LocalDate param){
        return roadObjectsRepo.countObjectByData(param);
    }
    @Override
    public List<Object[]> query10(){
        return roadObjectsRepo.countCrew();
    }

    @Override
    public List<RoadObject> query13(String name){return roadObjectsRepo.findByMat(name);}
    @Override
    public List<RoadObject> query14(int count){return roadObjectsRepo.findObjectByMat(count);}


    @Override
    public List<RoadObject> query15(){
        return roadObjectsRepo.findByMatNull();
    }

    @Override
    public List<RoadObject> query16(){
        return roadObjectsRepo.findAllEquNull();
    }

    @Override
    public void deleteRoadObject(Long id){
        List<ObjectMaterials> objectMaterials = objectMaterialsRepo.findAll();
        for (int i = 0;i<objectMaterials.size();i++){
            if (objectMaterials.get(i).getObject().getIdObject()==id){
                objectMaterialsRepo.deleteById(objectMaterials.get(i).getIdObjectMaterials());
            }
        }
        roadObjectsRepo.deleteById(id);
    }

    @Override
    public void deleteRoadObjectEquipment(int id,Long idObject){
        RoadObject object = roadObjectsRepo.findById(idObject).orElse(null);
        if (object!=null){
            List<Equipment> equipment = object.getEquipment();
            equipment.remove(id);
            roadObjectsRepo.saveAndFlush(object);
        }

    }

}
