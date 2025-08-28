package jp.te4a.app2.facility2.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.te4a.app2.facility2.form.FacilityForm;
import jp.te4a.app2.facility2.form.UserForm;
import jp.te4a.app2.facility2.service.FacilityService;
import jp.te4a.app2.facility2.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/home")
    public String selectFunction() {
        return "admin/admin-home";
    }

    @Autowired
    UserService userService;
    @Autowired
    FacilityService facilityService;
    
    @ModelAttribute
    UserForm setUpForm() {
        return new UserForm();
    }

    //adminにGET要求
    @GetMapping("/list")
    String list(Model model) {
        model.addAttribute("admin", facilityService.findAll());
        return "admin/admin-facility-list";
    }
    //admin/createにPOST要求
    @PostMapping("/create")
    String create(@Validated FacilityForm form, BindingResult result, Model model) {
        if(result.hasErrors()) {
            //エラー発生時に一覧画面に戻す
            return list(model);
        }
        facilityService.save(form);
        return "redirect:/admin";
    }
    //admin/editにパラメタformを含むPOST要求
    @PostMapping(path="/edit", params="form")
    public String editForm(@RequestParam Integer id, FacilityForm form) {
        FacilityForm facilityForm = facilityService.findOne(id);
        BeanUtils.copyProperties(facilityForm, form);        
        return "admin/edit";
    }
    
    //admin/editにPOST要求
    @PostMapping(path="/edit")
    String edit(@RequestParam Integer id,@Validated FacilityForm form,BindingResult result) {
        if(result.hasErrors()) {
            //エラー発生時に編集画面に戻す
            return editForm(id, form);
        }
        facilityService.update(form);
        return "redirect:/admin";
    }
    //admin/deleteにPOST要求
    @PostMapping(path="/delete")
    String delete(@RequestParam Integer id) {
        facilityService.delete(id);
        return "redirect:/admin";
    }
    //admin/editにパラメタgoToTopを含むPOST要求
    @PostMapping(path="/edit", params="goToTop")
    String goToTop() {
        return "redirect:/admin";
    }
}

