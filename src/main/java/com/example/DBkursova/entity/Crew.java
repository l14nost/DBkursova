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
public class Crew {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crew_id")
    private Long idCrew;
    @Column(name = "crew_number")
    private Integer number;
    @Column(name = "crew_time_spent")
    private Integer timeSpent;
    @OneToMany(mappedBy = "crew", cascade = CascadeType.ALL)
    private List<CrewMember> crewMemberList = new ArrayList<>();
    @OneToMany(mappedBy = "crew")
    private List<RoadObject> roadObjects = new ArrayList<>();
}
