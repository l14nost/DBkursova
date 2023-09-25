package com.example.DBkursova.controllers;

import com.example.DBkursova.entity.Materials;
import com.example.DBkursova.enums.TypeMaterials;
import com.example.DBkursova.service.MaterialsService;
import com.example.DBkursova.service.UserService;
import com.example.DBkursova.service.impl.MaterialsServiceImpl;
import com.example.DBkursova.service.impl.UserServiceImpl;
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
public class MaterialsController {
    private final MaterialsService materialsService;
    private final UserService userDetailsService;

    @GetMapping("/materials")
    public String crewMain(Model model){
        model.addAttribute("materials",materialsService.findAll());
        log.info("All materials by "+userDetailsService.getUserProfile());
        return "admin/materials_main";
    }
    @GetMapping("/materials_edit/{id}")
    public String crewEditPage(@PathVariable Long id,Model model){
        model.addAttribute("material",materialsService.findById(id));
        log.info("Edit page materials "+id+" by "+userDetailsService.getUserProfile());
        return "admin/materials_edit";
    }

    @PostMapping("/materials_edit/{id}")
    public String crewUpdate(@PathVariable Long id, @RequestParam String name , @RequestParam TypeMaterials type, @RequestParam Integer quantity, Model model){
        Materials materials = Materials.builder().name(name).quantity(quantity).type(type).build();
        materialsService.update(materials,id);
        log.info("Edit materials "+id+" by "+userDetailsService.getUserProfile());
        return "redirect:/materials";
    }
    @GetMapping("/materials_add")
    public String crewAddPage(Model model){
        log.info("Add page materials by "+userDetailsService.getUserProfile());
        return "admin/materials_add";
    }

    @PostMapping("/materials_add")
    public String crewAdd(@RequestParam String name, @RequestParam TypeMaterials type, @RequestParam Integer quantity){
        Materials materials = Materials.builder().name(name).quantity(quantity).type(type).build();
        materialsService.save(materials);
        log.info("Add materials by "+userDetailsService.getUserProfile());
        return "redirect:/materials";
    }
    @GetMapping("/delete_material/{id}")
    public String deleteMaterials(@PathVariable Long id){
        materialsService.deleteMaterial(id);
        log.info("Delete materials by "+userDetailsService.getUserProfile());
        return "redirect:/materials";
    }

}
