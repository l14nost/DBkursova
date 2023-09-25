package com.example.DBkursova.repository;

import com.example.DBkursova.entity.ObjectMaterials;
import com.example.DBkursova.entity.RoadObject;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Repository
public interface RoadObjectsRepo extends JpaRepository<RoadObject,Integer> {
    @Query("SELECT ro FROM RoadObject ro")
    List<RoadObject> findAllQuery();

    @Query(value = "select * " +
            "from objects " +
            "where crew_crew_id = :idCrew " +
            "order by street ", nativeQuery = true)
    List<RoadObject> findAllCrew(@Param("idCrew")int idCrew);
    @Query(value = "select * " +
            "from objects " +
            "where repair_date between :param1 and :param2",nativeQuery = true)
    List<RoadObject> findAllBetween(@Param("param1")LocalDate param1,@Param("param2")LocalDate param2);

    @Query(
            value = "select count(*) " +
                    "from objects " +
                    "where repair_date>=:param",
            nativeQuery = true
    )
    int countObjectByData(@Param("param") LocalDate param);
    @Query(value = "select crew_crew_id,count(*) " +
            "from objects " +
            "group by crew_crew_id",nativeQuery = true)
    List<Object[]> countCrew();
    @Query(value = "SELECT * " +
        "FROM Objects " +
        "WHERE object_id IN ( " +
        "    SELECT object_object_id " +
        "    FROM object_materials " +
        "    WHERE materials_material_id = ( " +
        "        SELECT material_id " +
        "        FROM Materials " +
        "        WHERE material_name = :name " +
        "    ) " +
        ")", nativeQuery = true)
    List<RoadObject> findByMat(String name);

    @Query(value = "SELECT * " +
            "FROM Objects " +
            "WHERE object_id IN (SELECT object_object_id FROM Object_Materials WHERE quantity > :count) ",nativeQuery = true)
    List<RoadObject> findObjectByMat(int count);


    @Query(value = "SELECT *" +
            "FROM Objects o " +
            "LEFT JOIN Object_Materials om ON o.object_id = om.object_object_id " +
            "WHERE om.object_object_id IS NULL",nativeQuery = true)
    List<RoadObject> findByMatNull();
    @Query(value = "SELECT * " +
            "FROM Objects " +
            "WHERE object_id NOT IN (SELECT object_object_id FROM Objects_Equipment)",nativeQuery = true)
    List<RoadObject> findAllEquNull();
}
