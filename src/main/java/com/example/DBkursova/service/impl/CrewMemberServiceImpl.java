package com.example.DBkursova.service.impl;

import com.example.DBkursova.entity.CrewMember;
import com.example.DBkursova.repository.CrewMembersRepo;
import com.example.DBkursova.service.CrewMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CrewMemberServiceImpl implements CrewMemberService {
    private final CrewMembersRepo crewMembersRepo;
    @Override
    public List<CrewMember> findAll(){
        return crewMembersRepo.findAll();
    }
    @Override
    public CrewMember findById(Long id){
        return crewMembersRepo.findById(id).orElse(null);
    }

    @Override
    public void save(CrewMember crewMember){
        crewMembersRepo.save(crewMember);
    }
    @Override
    public void update(CrewMember member, Long id){
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
    @Override
    public List<CrewMember> query2(int idCrew){
        return crewMembersRepo.findAllCrew(idCrew);
    }

    @Override
    public List<Object[]> query9(){
        return crewMembersRepo.countMemberForAll();
    }

    @Override
    public List<Object[]> query18(int crew){
        return crewMembersRepo.crewMemObject(crew);
    }

    @Override
    public void query19(){
        crewMembersRepo.updateMemberDescriptionActive();
    }

    @Override
    public void query20(){
        crewMembersRepo.updateMemberDescriptionNotActive();
    }
    @Override
    public void deleteCrew(Long id){
        crewMembersRepo.deleteById(id);
    }
}
