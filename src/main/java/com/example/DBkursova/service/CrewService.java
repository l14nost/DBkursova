package com.example.DBkursova.service;

import com.example.DBkursova.entity.Crew;
import com.example.DBkursova.repository.CrewRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CrewService {
    List<Crew> findAll();

    Crew findById(Long id);
    void save(Crew crew);
    void crewUpdate(Crew crew, Long id);

    List<Object[]> query11();

    void deleteMember(Long id);

    void addNew();
}
