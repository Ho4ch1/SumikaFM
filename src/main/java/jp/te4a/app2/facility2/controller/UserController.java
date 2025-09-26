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
    
    @PostMapping("/userSearch")
    public String userSearch(
        @RequestParam(required = false) Integer id,
        @RequestParam(required = false) String username,
        @RequestParam(required = false) String role,
        Model model) {

        // ★プルダウン用のデータを毎回セットする
        model.addAttribute("ids", userService.getAllIds());
        model.addAttribute("usernames", userService.getAllUsernames());
        model.addAttribute("roles", userService.getAllRoles());

        List<UserBean> result = userService.search(id, username, role);

        List<UserForm> forms = new ArrayList<>();
        for (UserBean bean : result) {
            UserForm form = new UserForm();
            BeanUtils.copyProperties(bean, form);
            forms.add(form);
        }

        model.addAttribute("users", forms);
        return "auth/user-list";
    }

    // 一覧表示
    @GetMapping("/list")
    String list(Model model) {
        List<UserForm> forms = userService.findAll();

    for (UserForm form : forms) {
        UserBean bean = new UserBean();
        BeanUtils.copyProperties(form, bean);
    }

    model.addAttribute("users", forms);

        model.addAttribute("ids", userService.getAllIds());
        model.addAttribute("usernames", userService.getAllUsernames());
        model.addAttribute("roles", userService.getAllRoles());

        return "auth/user-list";
    }

    // 登録画面表示
    @PostMapping(path="register")
    public String registerForm() {
        return "auth/create-user";
    }

    // 登録処理
    @PostMapping(path="create")
    String create(@Validated UserForm form, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "auth/create-user";
        }
        try {
            userService.create(form);
            return "redirect:list";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "auth/create-user";
        } catch (Exception e) {
            model.addAttribute("error", "登録に失敗しました");
            return "auth/create-user";
        }
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
