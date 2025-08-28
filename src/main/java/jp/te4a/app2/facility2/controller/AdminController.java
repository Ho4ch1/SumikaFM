package jp.te4a.app2.facility2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.te4a.app2.facility2.form.FacilityForm;
import jp.te4a.app2.facility2.service.FacilityService;



@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    FacilityService facilityService;

    @GetMapping
    public String AdminHome() {
        return "admin/admin-home";
    }

    @ModelAttribute
    FacilityForm setUpForm() {
        return new FacilityForm();
    }
    /*facilitiesにGET要求
    @GetMapping
    String list(Model model) {
        model.addAttribute("facilities", facilityService.findAll());
        return "admin/admin-facility-list";
    }*/

}

