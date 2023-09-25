package com.example.DBkursova.controllers;

import com.example.DBkursova.entity.Materials;
import com.example.DBkursova.entity.ObjectMaterials;
import com.example.DBkursova.entity.RoadObject;
import com.example.DBkursova.service.MaterialsService;
import com.example.DBkursova.service.ObjectMaterialsService;
import com.example.DBkursova.service.UserService;
import com.example.DBkursova.service.impl.MaterialsServiceImpl;
import com.example.DBkursova.service.impl.ObjectMaterialsServiceImpl;
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
public class ObjectMaterialsController {
    private final UserService userDetailsService;
    private final ObjectMaterialsService objectMaterialsService;
    private final MaterialsService materialsService;
    @GetMapping("/object_materials_edit/{id}")
    public String crewEditPage(@PathVariable Long id,Model model){
        model.addAttribute("objectMaterials",objectMaterialsService.findById(id));
        model.addAttribute("materials",materialsService.findAll());
        log.info("Edit page object_materials "+id+" by "+userDetailsService.getUserProfile());
        return "admin/object_materials_edit";
    }
    @GetMapping("/object_materials_add/{id}")
    public String crewAddPage(@PathVariable Integer id,Model model){
        model.addAttribute("object", id);
        model.addAttribute("materials",materialsService.findAll());
        log.info("Add page object_materials "+id+" by "+userDetailsService.getUserProfile());
        return "admin/object_materials_add";
    }
    @PostMapping("/object_materials_add/{id}")
    public String crewAdd(@PathVariable Long id, @RequestParam Long material, @RequestParam Integer quantity,Model model){
        objectMaterialsService.save(ObjectMaterials.builder().object(RoadObject.builder().idObject(id).build()).materials(Materials.builder().idMaterial(material).build()).quantity(quantity).build());
        log.info("Add object_materials "+id+" by "+userDetailsService.getUserProfile());
        return "redirect:/object_edit/"+id;
    }
    @PostMapping("/object_materials_edit/{id}")
    public String crewEdit(@PathVariable Long id, @RequestParam Long material, @RequestParam Integer quantity,Model model){
        objectMaterialsService.update(ObjectMaterials.builder().object(RoadObject.builder().idObject(id).build()).materials(Materials.builder().idMaterial(material).build()).quantity(quantity).build(),id);
        log.info("Edit object_materials "+id+" by "+userDetailsService.getUserProfile());
        return "redirect:/object_edit/"+objectMaterialsService.findById(id).getObject().getIdObject();
    }
    @GetMapping("/delete_object_material/{id}")
    public String deleteObjectMaterial(@PathVariable Long id){
        ObjectMaterials objectMaterials = objectMaterialsService.findById(id);
        objectMaterialsService.deleteObjectMaterials(id);
        log.info("Delete object_materials "+id+" by "+userDetailsService.getUserProfile());
        return "redirect:/object_edit/"+objectMaterials.getObject().getIdObject();
    }

}
