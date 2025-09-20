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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.te4a.app2.facility2.bean.UserBean;
import jp.te4a.app2.facility2.form.UserForm;
import jp.te4a.app2.facility2.service.UserService;

@Controller
@RequestMapping("/auth")
public class UserController {
    @Autowired
    UserService userService;

    @ModelAttribute
    UserForm setUpForm() {
        return new UserForm();
    }
// 一覧表示
@GetMapping("/list")
String list(Model model) {
    List<UserForm> forms = userService.findAll();
}
/*
model.addAttribute("facilities", forms);

    model.addAttribute("ids", userService.getAllIds());
    model.addAttribute("manufacturers", userService.getAllManufacturers());
    model.addAttribute("products", userService.getAllProducts());
    model.addAttribute("locations", userService.getAllLocations());
    model.addAttribute("serviceLifes", userService.getAllServiceLifes());

    return "auth/user-list";
}
*/

    // 登録画面表示
    @PostMapping(path="register")
    public String registerForm() {
        return "auth/create-user";
    }

    // 登録処理
    @PostMapping(path="create")
    public String create(@Validated UserForm userForm, BindingResult result , Model model, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            //エラー発生時に登録画面に戻す
            return "admin/register";
        }
        userService.save(userForm);
        //redirectAttributes.addFlashAttribute("success", "設備を登録しました");
        return "redirect:list";
    }

    // 削除処理
    @PostMapping(path = "delete")
    public String delete(@RequestParam("selectedIds") List<Integer> selectedIds, RedirectAttributes redirectAttributes) {
        Integer id = selectedIds.get(0);
        userService.delete(id);
        //redirectAttributes.addFlashAttribute("success", "設備を削除しました");
        return "redirect:list";
    }

}