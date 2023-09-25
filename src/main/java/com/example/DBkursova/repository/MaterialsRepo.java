package com.example.DBkursova.repository;

import com.example.DBkursova.entity.Crew;
import com.example.DBkursova.entity.Materials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialsRepo extends JpaRepository<Materials,Integer> {
    @Query(value = "SELECT * " +
            "FROM materials " +
            "WHERE material_name LIKE CONCAT('%', :name, '%')", nativeQuery = true)
    List<Materials> findAllLike(@Param("name") String name);
    @Query(value = "select * " +
            "from materials " +
            "where material_quantity between :param1 and :param2",nativeQuery = true)
    List<Materials> findAllBetween(@Param("param1") int param1,@Param("param2") int param2);
    @Query(value = "select count(*) " +
            "from materials " +
            "where material_quantity<= :count",nativeQuery = true)
    int countMaterial(@Param("count") int count);
    @Query(value = "SELECT material_id as id, material_type as type, material_name as name,'Материал' AS comment  " +
            "FROM Materials " +
            "JOIN Object_Materials ON Materials.material_id = Object_Materials.materials_material_id " +
            "JOIN Objects ON Object_Materials.object_object_id = Objects.object_id " +
            "WHERE Objects.object_id = :id " +
            "UNION " +
            "SELECT equipment_id, equipment_type, equipment_name,'Оборудование' AS comment  " +
            "FROM Equipment " +
            "JOIN Objects_Equipment ON Equipment.equipment_id = Objects_Equipment.equipment_equipment_id " +
            "JOIN Objects ON Objects_Equipment.object_object_id = Objects.object_id " +
            "WHERE Objects.object_id = :id",nativeQuery = true)
    List<Object[]> unionMatEqu(int id);



}
