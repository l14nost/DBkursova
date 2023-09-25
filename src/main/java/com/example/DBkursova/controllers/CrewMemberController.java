package com.example.DBkursova.controllers;

import com.example.DBkursova.entity.Crew;
import com.example.DBkursova.entity.CrewMember;
import com.example.DBkursova.service.CrewMemberService;
import com.example.DBkursova.service.CrewService;
import com.example.DBkursova.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
@Log4j2
public class CrewMemberController {
    private final CrewMemberService crewMemberService;
    private final UserDetailsServiceImpl userDetailsService;

    @GetMapping("/add_member/{idCrew}")
    public String crewMain(@PathVariable int idCrew,Model model){
        model.addAttribute("idCrew", idCrew);
        log.info("Add page crew member to crew "+idCrew+" by "+userDetailsService.getUserProfile());
        return "admin/member_add";
    }
    @GetMapping("/edit_member/{idMember}")
    public String memberEdit(@PathVariable int idMember,Model model){
        model.addAttribute("member", crewMemberService.findById(idMember));
        log.info("Edit page crew member "+idMember+" by "+userDetailsService.getUserProfile());
        return "admin/member_edit";
    }

    @PostMapping("/add_member/{idCrew}")
    public String memberSave(@PathVariable int idCrew,@RequestParam String name ,@RequestParam String position,@RequestParam int salary, Model model){
        crewMemberService.save(CrewMember.builder().name(name).salary(salary).position(position).crew(Crew.builder().idCrew(idCrew).build()).build());
        log.info("Add crew member to crew "+idCrew+" by "+userDetailsService.getUserProfile());
        return "redirect:/crews_edit/"+idCrew;
    }
    @PostMapping("/edit_member/{idMember}")
    public String memberUpdate(@PathVariable int idMember,@RequestParam String name ,@RequestParam String position,@RequestParam int salary, Model model){
        crewMemberService.update(CrewMember.builder().name(name).salary(salary).position(position).crew(Crew.builder().build()).build(),idMember);
        log.info("Edit crew member "+idMember+" by "+userDetailsService.getUserProfile());
        return "redirect:/crews_edit/"+crewMemberService.findById(idMember).getCrew().getIdCrew();
    }

    @GetMapping("/delete_member/{id}")
    public String memberDelete(@PathVariable int id){
        CrewMember crewMember = crewMemberService.findById(id);
        crewMemberService.deleteCrew(id);
        log.info("Delete crew member "+id+" by "+userDetailsService.getUserProfile());
        return "redirect:/crews_edit/"+crewMember.getCrew().getIdCrew();
    }
}
