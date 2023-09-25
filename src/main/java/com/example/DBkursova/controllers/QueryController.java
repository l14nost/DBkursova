package com.example.DBkursova.controllers;

import com.example.DBkursova.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('MANAGER')")
@Log4j2
public class QueryController {
    private final RoadObjectService roadObjectService;
    private final CrewService crewService;
    private final CrewMemberService crewMemberService;
    private final MaterialsService materialsService;
    private final EquipmentService equipmentService;
    private final UserDetailsServiceImpl userDetailsService;

    @GetMapping("/1")
    public String query1(@RequestParam(required = false, defaultValue = "0")int idCrew, Model model){
        model.addAttribute("objects", roadObjectService.findAllCrew(idCrew));
        log.info("Query1 by "+userDetailsService.getUserProfile());
        return "admin/1";
    }
    @GetMapping("/2")
    public String query2(@RequestParam(required = false, defaultValue = "0")int idCrew, Model model){
        model.addAttribute("objects", crewMemberService.query2(idCrew));
        log.info("Query2 by "+userDetailsService.getUserProfile());
        return "admin/2";
    }
    @GetMapping("/3")
    public String query3(@RequestParam(required = false, defaultValue = "0")String idCrew, Model model){
        model.addAttribute("objects", materialsService.query3(idCrew));
        log.info("Query3 by "+userDetailsService.getUserProfile());
        return "admin/3";
    }

    @GetMapping("/4")
    public String query4(@RequestParam(required = false, defaultValue = "0")String idCrew, Model model){
        model.addAttribute("objects", equipmentService.query4(idCrew));
        log.info("Query4 by "+userDetailsService.getUserProfile());

        return "admin/4";
    }

    @GetMapping("/5")
    public String query5(@RequestParam(required = false, defaultValue = "1996-01-01") LocalDate param1,@RequestParam(required = false, defaultValue = "2023-01-01") LocalDate param2, Model model){
        model.addAttribute("objects", roadObjectService.query5(param1,param2));
        log.info("Query5 by "+userDetailsService.getUserProfile());

        return "admin/5";
    }

    @GetMapping("/6")
    public String query6(@RequestParam(required = false, defaultValue = "0") int param1,@RequestParam(required = false, defaultValue = "200") int param2, Model model){
        model.addAttribute("objects", materialsService.query6(param1,param2));
        log.info("Query6 by "+userDetailsService.getUserProfile());

        return "admin/6";
    }

    @GetMapping("/7")
    public String query7(@RequestParam(required = false, defaultValue = "1996-01-01") LocalDate param, Model model){
        model.addAttribute("objects", roadObjectService.query7(param));
        model.addAttribute("models", roadObjectService.findAll());
        log.info("Query7 by "+userDetailsService.getUserProfile());

        return "admin/7";
    }

    @GetMapping("/8")
    public String query8(@RequestParam(required = false, defaultValue = "1000000") int param, Model model){
        model.addAttribute("objects", materialsService.query8(param));
        model.addAttribute("models", materialsService.findAll());
        log.info("Query8 by "+userDetailsService.getUserProfile());

        return "admin/8";
    }

    @GetMapping("/9")
    public String query9( Model model){
        model.addAttribute("models",crewMemberService.query9());
        log.info("Query9 by "+userDetailsService.getUserProfile());

        return "admin/9";
    }

    @GetMapping("/10")
    public String query10( Model model){
        model.addAttribute("models",roadObjectService.query10());
        log.info("Query10 by "+userDetailsService.getUserProfile());
        return "admin/10";
    }

    @GetMapping("/11")
    public String query11( Model model){
        model.addAttribute("models",crewService.query11());
        log.info("Query11 by "+userDetailsService.getUserProfile());

        return "admin/11";
    }

    @GetMapping("/12")
    public String query12( Model model){
        model.addAttribute("models",equipmentService.query12());
        log.info("Query12 by "+userDetailsService.getUserProfile());

        return "admin/12";
    }
    @GetMapping("/13")
    public String query13(@RequestParam(required = false, defaultValue = "0") String param1, Model model){
        model.addAttribute("objects", roadObjectService.query13(param1));
        model.addAttribute("materials", materialsService.findAll());
        log.info("Query13 by "+userDetailsService.getUserProfile());

        return "admin/13";
    }

    @GetMapping("/14")
    public String query14(@RequestParam(required = false, defaultValue = "0") int count, Model model){
        model.addAttribute("objects", roadObjectService.query14(count));
        log.info("Query14 by "+userDetailsService.getUserProfile());

        return "admin/14";
    }

    @GetMapping("/15")
    public String query15(Model model){
        model.addAttribute("objects", roadObjectService.query15());
        log.info("Query15 by "+userDetailsService.getUserProfile());

        return "admin/15";
    }

    @GetMapping("/16")
    public String query16(Model model){
        model.addAttribute("objects", roadObjectService.query16());
        log.info("Query16 by "+userDetailsService.getUserProfile());

        return "admin/16";
    }

    @GetMapping("/17")
    public String query17(@RequestParam(required = false, defaultValue = "1") int param,Model model){
        model.addAttribute("objects", materialsService.query17(param));
        log.info("Query17 by "+userDetailsService.getUserProfile());

        return "admin/17";
    }

    @GetMapping("/18")
    public String query18(@RequestParam(required = false, defaultValue = "1") int param,Model model){
        model.addAttribute("objects", crewMemberService.query18(param));
        log.info("Query18 by "+userDetailsService.getUserProfile());

        return "admin/18";
    }
    @GetMapping("/19")
    public String query19(Model model){
        model.addAttribute("objects", crewMemberService.findAll());
        log.info("Query19 by "+userDetailsService.getUserProfile());

        return "admin/19";
    }
    @PostMapping("/19_update")
    public String query19Update(){
        crewMemberService.query19();
        log.info("Query19 update by "+userDetailsService.getUserProfile());

        return "redirect:/19";
    }
    @GetMapping("/20")
    public String query20(Model model){
        model.addAttribute("objects", crewMemberService.findAll());
        log.info("Query20 by "+userDetailsService.getUserProfile());
        return "admin/20";
    }
    @PostMapping("/20_update")
    public String query20Update(){
        crewMemberService.query20();
        log.info("Query20 update by "+userDetailsService.getUserProfile());

        return "redirect:/20";
    }
}
