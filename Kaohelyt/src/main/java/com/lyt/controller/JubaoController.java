package com.lyt.controller;

import com.lyt.interceptor.RefreshCSRFToken;
import com.lyt.pojo.Jubao;
import com.lyt.pojo.User;
import com.lyt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Scope("prototype")
public class JubaoController {
    @Autowired
    UserService userService;
    @RefreshCSRFToken
    @RequestMapping(value = "/jubao", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String jubao(int JID,String UserName, Model model){
        model.addAttribute("JID",JID);
        model.addAttribute("UserName",UserName);
        return "jubao";
    }
    @RefreshCSRFToken
    @RequestMapping(value = "/jubaoN", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String jubaoN( int JID,String JubaoName,  String JubaoN, Model model){

        userService.addJubao(JID,JubaoName,JubaoN);
        return "c3";
    }
    @RefreshCSRFToken
    @RequestMapping(value = "/fj", produces = "application/json;charset=UTF-8")
    public String fj(Model model, ServletRequest servletRequest){
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        Cookie[] cookies = request.getCookies();
        Cookie ck = null;
        for (Cookie c : cookies) {
            if (c.getName().equals("autoLogin")) {
                ck = c;
            }
        }
        String UserIdP=null;
        if (ck != null) {
            UserIdP = ck.getValue().split("-")[1];
        }
        User us=userService.findUserById(UserIdP);
    if (us.getG().equals("yes")){
        List<Jubao>jubaos=userService.fj();
        model.addAttribute("Jubao",jubaos);
    }
        return "jiubaoP";
    }
    @RefreshCSRFToken
    @RequestMapping(value = "/df", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String df(String JID,Model model, ServletRequest servletRequest){
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        Cookie[] cookies = request.getCookies();
        Cookie ck = null;
        for (Cookie c : cookies) {
            if (c.getName().equals("autoLogin")) {
                ck = c;
            }
        }
        String UserIdP=null;
        if (ck != null) {
            UserIdP = ck.getValue().split("-")[1];
        }
        User us=userService.findUserById(UserIdP);
        if (us.getG().equals("yes")){
            userService.df(JID);
            List<Jubao>jubaos=userService.fj();
            model.addAttribute("Jubao",jubaos);
        }
        return "jiubaoP";
    }
    @RefreshCSRFToken
    @RequestMapping(value = "/f", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String f(String UserId,Model model, ServletRequest servletRequest){
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        Cookie[] cookies = request.getCookies();
        Cookie ck = null;
        for (Cookie c : cookies) {
            if (c.getName().equals("autoLogin")) {
                ck = c;
            }
        }
        String UserIdP=null;
        if (ck != null) {
            UserIdP = ck.getValue().split("-")[1];
        }
        User us=userService.findUserById(UserIdP);
        if (us.getG().equals("yes")){
            userService.f(UserId);

        }
        return "redirect:/AllUser.action";
    }
    @RefreshCSRFToken
    @RequestMapping(value = "/ff", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String ff(String UserId,Model model, ServletRequest servletRequest){
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        Cookie[] cookies = request.getCookies();
        Cookie ck = null;
        for (Cookie c : cookies) {
            if (c.getName().equals("autoLogin")) {
                ck = c;
            }
        }
        String UserIdP=null;
        if (ck != null) {
            UserIdP = ck.getValue().split("-")[1];
        }
        User us=userService.findUserById(UserIdP);
        if (us.getG().equals("yes")){
            userService.ff(UserId);

        }
        return "redirect:/AllUser.action";
    }
}
