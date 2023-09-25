package com.example.DBkursova.entity;

import com.example.DBkursova.enums.TypeMaterials;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Materials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id")
    private int idMaterial;
    @Column(name = "material_name")
    private String name;
    @Column(name = "material_type")
    @Enumerated(EnumType.STRING)
    private TypeMaterials type;
    @Column(name = "material_quantity")
    private int quantity;
}
