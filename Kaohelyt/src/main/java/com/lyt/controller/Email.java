package com.lyt.controller;

import com.lyt.interceptor.RefreshCSRFToken;
import com.lyt.pojo.User;
import com.lyt.service.UserService;
import com.lyt.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@Controller
@Scope("prototype")
public class Email {
    @Autowired
    UserService userService;

    @RequestMapping(value ="/sendEmail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String sendEmail(HttpServletRequest request, HttpServletResponse response,String UserEmail) throws UnsupportedEncodingException {
        String a=userService.sendEamil(request,response,UserEmail);
        System.out.println(a);
        return a;
    }
    @RequestMapping(value = "/sendEmail2", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String sendEmail2(HttpServletRequest request, HttpServletResponse response,String UserEmail){
        userService.sendEamil2(request,response,UserEmail);
        return null;
    }
    @RefreshCSRFToken
    @RequestMapping(value = "/jihuo", produces = "application/json;charset=UTF-8")
    public String jihuo(Model model, ServletRequest servletRequest, ServletResponse servletResponse){
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;

        Cookie[] cookies=request.getCookies();
        Cookie ck=null;
        for (Cookie c:cookies){
            if (c.getName().equals("autoLogin")){
                ck=c;
            }
        }
        String UserId=null;
        if (ck!=null){
            UserId=ck.getValue().split("-")[1];
        }
        User u=userService.findUserById(UserId);
        model.addAttribute("UserId",u.getUserId());

        return "xieyi";
    }
    @RequestMapping(value = "/sendEmail3", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String sendEmail3(HttpServletRequest request, HttpServletResponse response,String UserEmail){
        userService.sendEamil3(request,response,UserEmail);
        return null;
    }
    @RequestMapping(value = "/sendEmail4", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String sendEmail4(HttpServletRequest request, HttpServletResponse response,String UserEmail){
        userService.sendEamil4(request,response,UserEmail);
        return null;
    }
    @RequestMapping(value = "/sendEmail5", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String sendEmail5(HttpServletRequest request, HttpServletResponse response,String UserEmail){
        userService.sendEamil5(request,response,UserEmail);
        return null;
    }
    @RefreshCSRFToken
    @RequestMapping(value = "/xL", produces = "application/json;charset=UTF-8")
    public String xL(Model model, ServletRequest servletRequest, ServletResponse servletResponse){
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;

        Cookie[] cookies=request.getCookies();
        Cookie ck=null;
        for (Cookie c:cookies){
            if (c.getName().equals("autoLogin")){
                ck=c;
            }
        }
        String UserId=null;
        if (ck!=null){
            UserId=ck.getValue().split("-")[1];
        }
        User u=userService.findUserById(UserId);
        model.addAttribute("UserL",u.getUserLoginName());

        return "xiugai_yonghuming";
    }
    @RefreshCSRFToken
    @RequestMapping(value = "/xP", produces = "application/json;charset=UTF-8")
    public String xP(Model model, ServletRequest servletRequest, ServletResponse servletResponse){
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;

        Cookie[] cookies=request.getCookies();
        Cookie ck=null;
        for (Cookie c:cookies){
            if (c.getName().equals("autoLogin")){
                ck=c;
            }
        }
        String UserId=null;
        if (ck!=null){
            UserId=ck.getValue().split("-")[1];
        }
        User u=userService.findUserById(UserId);

        return "xiugai_mima";
    }
    @RefreshCSRFToken
    @RequestMapping(value = "/xE", produces = "application/json;charset=UTF-8")
    public String xE(Model model, ServletRequest servletRequest, ServletResponse servletResponse){
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;

        Cookie[] cookies=request.getCookies();
        Cookie ck=null;
        for (Cookie c:cookies){
            if (c.getName().equals("autoLogin")){
                ck=c;
            }
        }
        String UserId=null;
        if (ck!=null){
            UserId=ck.getValue().split("-")[1];
        }
        User u=userService.findUserById(UserId);

        return "xiugai_email";
    }
}
