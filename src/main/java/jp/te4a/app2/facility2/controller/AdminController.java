package jp.te4a.app2.facility2.controller;


import java.util.ArrayList;
import java.util.List;

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

import jp.te4a.app2.facility2.bean.FacilityBean;
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
    //管理者ログイン時の検索
    @PostMapping("/adminSearch")
    public String adminSearch(
        @RequestParam(required = false) Integer id,
        @RequestParam(required = false) String product,
        @RequestParam(required = false) String manufacturer,
        @RequestParam(required = false) Integer serviceLife,
        @RequestParam(required = false) String location,
        Model model) {

        List<FacilityBean> result = facilityService.search(id, product, manufacturer, serviceLife, location);

        List<FacilityForm> forms = new ArrayList<>();
        for (FacilityBean bean : result) {
            FacilityForm form = new FacilityForm();
            BeanUtils.copyProperties(bean, form);
            forms.add(form);
        }

        model.addAttribute("facilities", forms);
        return "admin/admin-facility-list";
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

    //選択した項目を編集画面に表示（1つ選択可能）
    @PostMapping(path = "editSelected")
    public String editSelected(@RequestParam List<Integer> selectedIds, Model model) {
        Integer id = selectedIds.get(0);
        FacilityForm facilityForm = facilityService.findOne(id);
        model.addAttribute("facilityForm", facilityForm);
        return "admin/edit";
    }

    // 編集処理
    @PostMapping(path = "edit")
    public String edit(@Validated FacilityForm facilityForm, Model model, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("facilityForm", facilityForm); // ← これを忘れずに！
            return "admin/edit";
        }
        facilityService.update(facilityForm);
        return "redirect:list";
    }

    // 削除処理
    @PostMapping(path = "delete")
    public String delete(@RequestParam("selectedIds") List<Integer> selectedIds) {
        Integer id = selectedIds.get(0);
        facilityService.delete(id);
        return "redirect:list";
    }

}

