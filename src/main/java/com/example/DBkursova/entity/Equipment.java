package com.example.DBkursova.entity;

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
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipment_id")
    private int idEquipment;
    @Column(name = "equipment_type")
    private String type;
    @Column(name = "equipment_name")
    private String name;
    @Column(name = "equipment_condition")
    private String condition;
    @Column(name = "equipment_cost")
    private int cost;
    @ManyToMany(mappedBy = "equipment", cascade = CascadeType.ALL)
    private List<RoadObject> object = new ArrayList<>();
}
