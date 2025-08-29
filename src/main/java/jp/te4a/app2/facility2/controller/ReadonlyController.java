package jp.te4a.app2.facility2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import jp.te4a.app2.facility2.bean.FacilityBean;
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

        //管理者ログイン時の検索
    @PostMapping("/readonlySearch")
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
        return "readonly/readonly-facility-list";
    }

    //facilitiesにGET要求
    @GetMapping
    String list(Model model) {
        model.addAttribute("facilities", facilityService.findAll());
        return "readonly/readonly-facility-list";
    }
}
