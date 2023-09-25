package com.example.DBkursova.service.impl;

import com.example.DBkursova.entity.Crew;
import com.example.DBkursova.repository.CrewRepo;
import com.example.DBkursova.service.CrewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CrewServiceImpl implements CrewService {
    private final CrewRepo crewRepo;
    @Override
    public List<Crew> findAll(){
        return crewRepo.findAll();
    }
    @Override
    public Crew findById(Long id){
        return crewRepo.findById(id).orElse(null);
    }
    @Override
    public void save(Crew crew){
        crewRepo.save(crew);
    }
    @Override
    public void crewUpdate(Crew crew, Long id){
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

    @Override
    public List<Object[]> query11(){
        return crewRepo.crewCount();
    }

    @Override
    public void deleteMember(Long id){
        crewRepo.deleteById(id);
    }

    @Override
    public void addNew() {
        List<Crew> crews = findAll();
        Crew crewLast = crews.get(crews.size()-1);
        Crew crew = Crew.builder().number(crewLast.getNumber()+1).timeSpent(0).build();
        crewRepo.save(crew);
    }
}
