package com.example.DBkursova.controllers;

import com.example.DBkursova.entity.*;
import com.example.DBkursova.service.*;
import com.example.DBkursova.service.impl.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
@Log4j2
public class ObjectController {
    private final RoadObjectService roadObjectService;
    private final ObjectMaterialsService objectMaterialsService;
    private final CrewService crewService;
    private final EquipmentService equipmentService;
    private final UserService userDetailsService;

    @GetMapping("/objects")
    public String crewMain(Model model){
        model.addAttribute("objects",roadObjectService.findAll());
        log.info("All objects by "+userDetailsService.getUserProfile());
        return "admin/objects_main";
    }
    @GetMapping("/object_edit/{id}")
    public String crewEditPage(@PathVariable Long id,Model model){
        model.addAttribute("objects",roadObjectService.findById(id));
        model.addAttribute("materials",objectMaterialsService.findAllMaterials(Integer.parseInt(String.valueOf(id))));
        model.addAttribute("crews",crewService.findAll());
        log.info("Edit page object "+id+" by "+userDetailsService.getUserProfile());
        return "admin/object_edit";
    }

    @PostMapping("/object_edit/{id}")
    public String crewUpdate(@PathVariable Long id, @RequestParam String street , @RequestParam LocalDate date,@RequestParam Long idCrew, @RequestParam String type, Model model){
        RoadObject roadObject = RoadObject.builder().damageType(type).street(street).repairDate(date).crew(Crew.builder().idCrew(idCrew).build()).build();
        roadObjectService.update(roadObject,id);
        log.info("Edit object "+id+" by "+userDetailsService.getUserProfile());
        return "redirect:/objects";
    }
    @GetMapping("/object_add")
    public String crewAddPage(Model model){
        RoadObject roadObject = RoadObject.builder().damageType("").street("").repairDate(LocalDate.now()).build();
        roadObjectService.save(roadObject);
        log.info("Add page object by "+userDetailsService.getUserProfile());

        return "redirect:/objects";
    }
    @GetMapping("/add_equipment_object/{id}")
    public String equipmentObjectAdd(@PathVariable Integer id,Model model){
        model.addAttribute("object", id);
        model.addAttribute("equipments",equipmentService.findAll());
        log.info("Add page equipment_object by "+userDetailsService.getUserProfile());
        return "admin/object_equipment_add";
    }
    @PostMapping("/add_equipment_object/{id}")
    public String equipmentObject(@PathVariable Long id,@RequestParam Long idEquipment,Model model){
        RoadObject roadObject = roadObjectService.findById(id);
        roadObject.getEquipment().add(Equipment.builder().idEquipment(idEquipment).build());
        roadObjectService.update(roadObject,id);
        log.info("Add equipment_object by "+userDetailsService.getUserProfile());
        return "redirect:/object_edit/"+id;
    }
    @PostMapping("/delete_object/{id}")
    public String deleteObject(@PathVariable Long id){

        roadObjectService.deleteRoadObject(id);
        log.info("Delete page object by "+userDetailsService.getUserProfile());
        return "redirect:/objects";
    }

    @GetMapping("/delete_object_equipment/{id}")
    public String deleteObjectEquipment(@PathVariable String id){
        String[] string = id.split("_");
        int hash = Integer.parseInt(string[0]);
        Long idObject = Long.parseLong(string[1]);
        roadObjectService.deleteRoadObjectEquipment(hash,idObject);
        log.info("Delete page equipment_object by "+userDetailsService.getUserProfile());
        return "redirect:/object_edit/"+idObject;
    }

}
