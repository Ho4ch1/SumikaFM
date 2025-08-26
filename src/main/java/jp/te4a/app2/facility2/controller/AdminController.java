package jp.te4a.app2.facility2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


import jp.te4a.app2.facility2.form.UserForm;
import jp.te4a.app2.facility2.service.UserService;

@Controller
@RequestMapping("admin")
public class AdminController {

    @GetMapping
    public String redirectToHome() {
        return "redirect:/admin/admin-home";
    }

    @GetMapping("admin-home")
    public String selectFunction() {
        return "/admin/admin-home";
    }

    @Autowired
    UserService userService;

    @ModelAttribute
    UserForm setUpForm() {
        return new UserForm();
    }
}

