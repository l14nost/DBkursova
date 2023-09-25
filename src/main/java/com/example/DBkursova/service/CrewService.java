package com.example.DBkursova.service;

import com.example.DBkursova.entity.Crew;
import com.example.DBkursova.repository.CrewRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CrewService {
    private final CrewRepo crewRepo;

    public List<Crew> findAll(){
        return crewRepo.findAll();
    }
    public Crew findById(int id){
        return crewRepo.findById(id).orElse(null);
    }
    public void save(Crew crew){
        crewRepo.save(crew);
    }
    public void crewUpdate(Crew crew, int id){
        Optional<Crew> crewOptional = crewRepo.findById(id);
        if (crewOptional.isPresent()){
            Crew crewUpdate = crewOptional.get();
            if (crew.getNumber()!=0){
                crewUpdate.setNumber(crew.getNumber());
            }
            if (crew.getTimeSpent()!=0){
                crewUpdate.setTimeSpent(crew.getTimeSpent());
            }
            crewRepo.saveAndFlush(crewUpdate);
        }
    }

    public List<Object[]> query11(){
        return crewRepo.crewCount();
    }

    public void deleteMember(int id){
        crewRepo.deleteById(id);
    }
}
