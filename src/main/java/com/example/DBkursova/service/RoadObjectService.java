package com.example.DBkursova.service;

import com.example.DBkursova.entity.Equipment;
import com.example.DBkursova.entity.ObjectMaterials;
import com.example.DBkursova.entity.RoadObject;
import com.example.DBkursova.repository.ObjectMaterialsRepo;
import com.example.DBkursova.repository.RoadObjectsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RoadObjectService {
    List<RoadObject> findAll();

    void save(RoadObject roadObject);

    RoadObject findById(Long id);


    List<RoadObject> findAllCrew(int idCrew);

    void update(RoadObject object, Long id);
    List<RoadObject> query5(LocalDate param1, LocalDate param2);
    int query7(LocalDate param);
    List<Object[]> query10();

    List<RoadObject> query13(String name);
    List<RoadObject> query14(int count);


    List<RoadObject> query15();

    List<RoadObject> query16();

    void deleteRoadObject(Long id);

    void deleteRoadObjectEquipment(int id,Long idObject);

}
