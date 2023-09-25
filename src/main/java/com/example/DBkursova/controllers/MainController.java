package com.example.DBkursova.controllers;

import com.example.DBkursova.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
@Log4j2
public class MainController {
    private final RoadObjectService roadObjectService;
    private final CrewService crewService;
    private final CrewMemberService crewMemberService;
    private final MaterialsService materialsService;
    private final EquipmentService equipmentService;


    @GetMapping("/")
    public String mainController(Model model){
        model.addAttribute("objects", roadObjectService.findAll());
        model.addAttribute("crews", crewService.findAll());
        model.addAttribute("members", crewMemberService.findAll());
        model.addAttribute("equipments", equipmentService.findAll());
        model.addAttribute("materials", materialsService.findAll());
        log.info("Statistic page by "+getUserProfile());
        return "admin/admin_main";
    }

    public final static String setupCon(){
        String roleUser = getUserProfile().split(" ")[1];
        return roleUser;
    }
    public static String getUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            String role = userDetails.getAuthorities().toString();
            return username+" "+role;
        } else {
            return "error";
        }
    }

}
