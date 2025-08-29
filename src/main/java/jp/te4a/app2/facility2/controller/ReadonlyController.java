package jp.te4a.app2.facility2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import jp.te4a.app2.facility2.form.FacilityForm;
import jp.te4a.app2.facility2.service.FacilityService;

@Controller
@RequestMapping("/readonly")
public class ReadonlyController {
    @Autowired
    FacilityService facilityService;
    
    @ModelAttribute
    FacilityForm setUpForm() {
        return new FacilityForm();
    }

    //facilitiesにGET要求
    @GetMapping
    String list(Model model) {
        model.addAttribute("facilities", facilityService.findAll());
        return "readonly/readonly-facility-list";
    }
}
