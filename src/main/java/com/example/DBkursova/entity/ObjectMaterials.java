package com.example.DBkursova.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ObjectMaterials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_object_materials")
    private Long idObjectMaterials;

    @ManyToOne
    private RoadObject object;

    @ManyToOne
    private Materials materials;

    private Integer quantity;
}
