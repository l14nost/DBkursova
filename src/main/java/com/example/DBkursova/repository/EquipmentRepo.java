package com.example.DBkursova.repository;

import com.example.DBkursova.entity.Crew;
import com.example.DBkursova.entity.Equipment;
import com.example.DBkursova.entity.Materials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepo extends JpaRepository<Equipment,Long> {
    @Query(value = "SELECT * " +
            "FROM equipment " +
            "WHERE equipment_name LIKE CONCAT('%', :name, '%')", nativeQuery = true)
    List<Equipment> findAllLike(@Param("name") String name);
    @Query(value = "SELECT e.equipment_id, COUNT(*) AS object_count " +
            "FROM Equipment e " +
            "JOIN Objects_Equipment oe ON e.equipment_id = oe.equipment_equipment_id " +
            "GROUP BY e.equipment_id, e.equipment_name " +
            "HAVING COUNT(*) > ANY ( " +
            "    SELECT COUNT(*) " +
            "    FROM Objects_Equipment " +
            "    GROUP BY equipment_equipment_id " +
            ")",nativeQuery = true)
    List<Object[]> countInObject();
}
