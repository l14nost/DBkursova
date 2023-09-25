package com.example.DBkursova.controllers;

import com.example.DBkursova.entity.Crew;
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

import java.util.List;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
@Log4j2
public class CrewController {
    private final UserDetailsServiceImpl userDetailsService;
    private final CrewService crewService;
    @GetMapping("/crews")
    public String crewMain(Model model){
        model.addAttribute("crews",crewService.findAll());
        log.info("All crews by "+userDetailsService.getUserProfile());
        return "admin/crews_main";
    }

    @GetMapping("/crews_edit/{id}")
    public String crewEdit(@PathVariable int id, Model model){
        model.addAttribute("crew",crewService.findById(id));
        log.info("Edit page crew "+id+" by "+userDetailsService.getUserProfile());
        return "admin/crews_edit";
    }

    @PostMapping("/crews_edit/{id}")
    public String crewUpdate(@PathVariable int id,@RequestParam int number ,@RequestParam int time, Model model){
        Crew crew = Crew.builder().number(number).timeSpent(time).build();
        crewService.crewUpdate(crew,id);
        log.info("Edit query crew "+id+" by "+userDetailsService.getUserProfile());
        return "redirect:/crews";
    }
    @GetMapping("/add_crew")
    public String crewAdd(){
        List<Crew> crews = crewService.findAll();
        Crew crewLast = crews.get(crews.size()-1);
        Crew crew = Crew.builder().number(crewLast.getNumber()+1).timeSpent(0).build();
        crewService.save(crew);
        log.info("Add crew by "+userDetailsService.getUserProfile());

        return "redirect:/crews";
    }

    @GetMapping("/delete_crew/{id}")
    public String deleteCrew(@PathVariable int id){
        crewService.deleteMember(id);
        log.info("Delete crew "+id+" by "+userDetailsService.getUserProfile());
        return "redirect:/crews";
    }

}
