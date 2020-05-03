package com.lyt.controller;

import com.lyt.interceptor.RefreshCSRFToken;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Scope("prototype")
public class LoginController {
    @RefreshCSRFToken
    @RequestMapping(value = "/UserLogin", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String UserLogin(){
        return "login";

    }
    @RefreshCSRFToken
    @RequestMapping(value = "/UserLogin2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String UserLogin2(String login_msg,Model model){
        model.addAttribute("login_msg",login_msg);
        return "login";

    }
}
