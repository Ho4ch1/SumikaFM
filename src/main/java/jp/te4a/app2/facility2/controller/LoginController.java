package jp.te4a.app2.facility2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
        
        @GetMapping(path="login")
        String loginForm() {
            return "login-form";
        }

}
