package com.example.DBkursova.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "objects")
public class RoadObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "object_id")
    private Long idObject;
    @Column(name = "street")
    private String street;
    @Column(name = "repair_date")
    private LocalDate repairDate;
    @Column(name = "damage_type")
    private String damageType;
    @ManyToOne
    private Crew crew;
    @ManyToMany
    private List<Equipment> equipment = new ArrayList<>();
}
