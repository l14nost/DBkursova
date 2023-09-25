package com.example.DBkursova.service;

import com.example.DBkursova.entity.CrewMember;
import com.example.DBkursova.repository.CrewMembersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CrewMemberService {
    List<CrewMember> findAll();
    CrewMember findById(Long id);
    void save(CrewMember crewMember);
    void update(CrewMember member, Long id);
    List<CrewMember> query2(int idCrew);

    List<Object[]> query9();

    List<Object[]> query18(int crew);

    void query19();

    void query20();
    void deleteCrew(Long id);
}
