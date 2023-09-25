package com.example.DBkursova.service;

import com.example.DBkursova.entity.Crew;
import com.example.DBkursova.entity.CrewMember;
import com.example.DBkursova.repository.CrewMembersRepo;
import com.example.DBkursova.repository.CrewRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CrewMemberService {
    private final CrewMembersRepo crewMembersRepo;
    public List<CrewMember> findAll(){
        return crewMembersRepo.findAll();
    }
    public CrewMember findById(int id){
        return crewMembersRepo.findById(id).orElse(null);
    }

    public void save(CrewMember crewMember){
        crewMembersRepo.save(crewMember);
    }
    public void update(CrewMember member, int id){
        Optional<CrewMember> memberOptional = crewMembersRepo.findById(id);
        if (memberOptional.isPresent()){
            CrewMember memberUpdate = memberOptional.get();
            if (member.getName()!=null){
                memberUpdate.setName(member.getName());
            }
            if (member.getSalary()!=0){
                memberUpdate.setSalary(member.getSalary());
            }
            if (member.getPosition()!=null){
                memberUpdate.setPosition(member.getPosition());
            }
            crewMembersRepo.saveAndFlush(memberUpdate);
        }
    }
    public List<CrewMember> query2(int idCrew){
        return crewMembersRepo.findAllCrew(idCrew);
    }

    public List<Object[]> query9(){
        return crewMembersRepo.countMemberForAll();
    }

    public List<Object[]> query18(int crew){
        return crewMembersRepo.crewMemObject(crew);
    }

    public void query19(){
        crewMembersRepo.updateMemberDescriptionActive();
    }

    public void query20(){
        crewMembersRepo.updateMemberDescriptionNotActive();
    }
    public void deleteCrew(int id){
        crewMembersRepo.deleteById(id);
    }
}
