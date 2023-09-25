package com.example.DBkursova.repository;

import com.example.DBkursova.entity.ObjectMaterials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObjectMaterialsRepo extends JpaRepository<ObjectMaterials,Integer> {
        @Query(value = "select * " +
            "from object_materials om " +
            "join materials m on om.materials_material_id = m.material_id " +
            "join objects o on om.object_object_id = o.object_id " +
            "where om.object_object_id = :id", nativeQuery = true)
    List<ObjectMaterials> findAllMaterials(@Param("id") int id);

}
