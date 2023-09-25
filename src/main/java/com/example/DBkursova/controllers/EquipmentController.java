package com.example.DBkursova.controllers;

import com.example.DBkursova.entity.Equipment;
import com.example.DBkursova.service.EquipmentService;
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
public class EquipmentController {
    private final UserDetailsServiceImpl userDetailsService;
    private final EquipmentService equipmentService;
    @GetMapping("/equipments")
    public String crewMain(Model model){
        model.addAttribute("equipments",equipmentService.findAll());
        log.info("All equipments by "+userDetailsService.getUserProfile());
        return "admin/equipments_main";
    }
    @GetMapping("/equipments_edit/{id}")
    public String crewEditPage(@PathVariable int id,Model model){
        model.addAttribute("equipment",equipmentService.findById(id));
        log.info("Edit page equipment "+id+" by "+userDetailsService.getUserProfile());
        return "admin/equipments_edit";
    }
    @PostMapping("/equipments_edit/{id}")
    public String crewUpdate(@PathVariable int id, @RequestParam String name , @RequestParam String type, @RequestParam String condition,@RequestParam int cost, Model model){
        Equipment equipment = Equipment.builder().cost(cost).condition(condition).type(type).name(name).build();
        equipmentService.update(equipment,id);
        log.info("Edit equipment "+id+" by "+userDetailsService.getUserProfile());
        return "redirect:/equipments";
    }
    @GetMapping("/equipments_add")
    public String crewAddPage(Model model){
        log.info("Add page equipment by "+userDetailsService.getUserProfile());
        return "admin/equipments_add";
    }
    @PostMapping("/equipments_add")
    public String crewAdd(@RequestParam String name , @RequestParam String type, @RequestParam String condition,@RequestParam int cost, Model model){
        Equipment equipment = Equipment.builder().cost(cost).condition(condition).type(type).name(name).build();
        equipmentService.save(equipment);
        log.info("Add equipment by "+userDetailsService.getUserProfile());
        return "redirect:/equipments";
    }

    @PostMapping("/delete_equipment/{id}")
    public String deleteEquipment(@PathVariable int id){
        equipmentService.deleteEquipment(id);
        return "redirect:/equipments";
    }
}
