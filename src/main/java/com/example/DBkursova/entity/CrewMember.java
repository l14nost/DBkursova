package com.example.DBkursova.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "crewmembers")
public class CrewMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long idCrewMember;
    @Column(name = "member_name")
    private String name;
    @Column(name = "member_position")
    private String position;
    @Column(name = "member_salary")
    private Integer salary;
    @Column(name = "member_description")
    private String description;
    @ManyToOne
    private Crew crew;
}
