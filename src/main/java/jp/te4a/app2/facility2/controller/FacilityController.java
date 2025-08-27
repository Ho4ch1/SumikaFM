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
import jp.te4a.app2.facility2.service.FacilityService;




@Controller
@RequestMapping("/facilities")
public class FacilityController {
    @Autowired
    FacilityService facilityService;

    //課題８参照
    @ModelAttribute
    FacilityForm setUpForm() {
        return new FacilityForm();
    }
    //facilitiesにGET要求
    @GetMapping
    String list(Model model) {
        model.addAttribute("facilities", facilityService.findAll());
        return "facilities/list";
    }
    //facilities/createにPOST要求
    @PostMapping(path="create")
    String create(@Validated FacilityForm form,BindingResult result ,Model model) {
        if(result.hasErrors()) {
            //エラー発生時に一覧画面に戻す
            return list(model);
        }
        facilityService.save(form);
        return "redirect:/facilities";
    }
    //facilities/editにパラメタformを含むPOST要求
    @PostMapping(path = "edit",params = "form")
    public String editForm(@RequestParam Integer id,FacilityForm form) {
        FacilityForm facilityForm = facilityService.findOne(id);
        BeanUtils.copyProperties(facilityForm, form);        
        return "facilities/edit";
    }
    
    //facilities/editにPOST要求
    @PostMapping(path = "edit")
    String edit(@RequestParam Integer id,@Validated FacilityForm form,BindingResult result) {
        if(result.hasErrors()) {
            //エラー発生時に編集画面に戻す
            return editForm(id,form);
        }
        facilityService.update(form);
        return "redirect:/facilities";
    }
    //facilities/deleteにPOST要求
    @PostMapping(path = "delete")
    String delete(@RequestParam Integer id) {
        facilityService.delete(id);
        return "redirect:/facilities";
    }
    //facilities/editにパラメタgoToTopを含むPOST要求
    @PostMapping(path = "edit", params = "goToTop")
    String goToTop() {
        return "redirect:/facilities";
    }

    @GetMapping("/readonly-facility-list")
    public String readonlyList(Model model) {
        model.addAttribute("facilities", facilityService.findAll());
        return "facilities/readonly-facility-list";
    }





    /* 
    @RequestMapping("facilities/list")
    public String index(Model model) {
        model.addAttribute("msg","this is message");
        return "facilities/list";
    }
    
    @RequestMapping(value="facilities/list", method=RequestMethod.POST)
    public ModelAndView postForm(@RequestParam("id") String id,
    @RequestParam("product") String product,@RequestParam("modelNumber") String modelNumber,
    @RequestParam("manufacturer") String manufacturer,@RequestParam("specification") String specification,
    @RequestParam("serviceLife") String serviceLife,@RequestParam("purchaceDate") String purchaceDate,
    @RequestParam("depreciation") String depreciation,@RequestParam("notAvailable") String notAvailable,
    @RequestParam("rentAble") String rentAble,@RequestParam("location") String location,
    @RequestParam("expiry") String expiry) {
        ModelAndView mv = new ModelAndView("facilities/list");
        facilityService.save(new FacilityBean(Integer.valueOf(id), product, modelNumber, manufacturer, specification, Integer.valueOf(serviceLife), Integer.valueOf(purchaceDate), 
        Integer.valueOf(depreciation), Boolean.valueOf(notAvailable), Boolean.valueOf(rentAble), location, Integer.valueOf(expiry)));
        */
        /*課題６まで
        StringBuffer buff = new StringBuffer();
        buff.append("<HR>");
        for(FacilityBean bean:facilityService.findAll()) {
            buff.append("ID:" + bean.getId() + "<BR>" + "品名:" + bean.getProduct() +
            "<BR>"+ "型番:" + bean.getModelNumber() + "<BR>" + "メーカー:" + bean.getManufacturer() +
            "<BR>"+ "仕様:" + bean.getSpecification() + "<BR>" + "耐用年数:" + bean.getServiceLife() +
            "<BR>"+ "購入日:" + bean.getPurchaceDate() + "<BR>" + "減価償却:" + bean.getDepreciation() +
            "<BR>"+ "使用不可:" + bean.getNotAvailable() + "<BR>" + "貸出可能:" + bean.getRentAble() +
            "<BR>"+ "設置場所:" + bean.getLocation() +
            "<BR>"+ "使用期限:" + bean.getExpiry() + "<BR><HR>");
        }
            */
            /*
        mv.addObject("facilities", facilityService.findAll());
        return mv;
        
    }
        */
}
