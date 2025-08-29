package jp.te4a.app2.facility2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
    //admin/listにGET要求
    @GetMapping("/list")
    String list(Model model) {
        model.addAttribute("facilities", facilityService.findAll());
        return "admin/admin-facility-list";
    }

    // 登録画面表示
    @PostMapping(path="register")
    public String registerForm() {
        return "admin/register";
    }

    // 登録処理
    @PostMapping(path="create")
    public String create(@Validated FacilityForm facilityForm, BindingResult result , Model model) {
        if(result.hasErrors()) {
            //エラー発生時に登録画面に戻す
            return "admin/register";
        }
        facilityService.save(facilityForm);
        return "redirect:list";
    }

}

