package com.lyt.controller;


import com.lyt.interceptor.RefreshCSRFToken;
import com.lyt.pojo.*;
import com.lyt.service.HaoyouService;

import com.lyt.service.LaheiService;
import com.lyt.service.PinLuenService;
import com.lyt.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("prototype")
public class HaoyouController {
    @Autowired
    LaheiService laheiService;
    @Autowired
    UserService userService;
    @Autowired
    HaoyouService haoyouService;
    @Autowired
    PinLuenService pinLuenService;
    @RefreshCSRFToken
    @RequestMapping(value = "/haoyou", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String haoyou(Model model, ServletRequest servletRequest, ServletResponse servletResponse, String LaheiB,int LaheiN){
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        String pp=(String) request.getSession().getAttribute("PassWord");
        int LaheiP = 0;


            Cookie[] cookies = request.getCookies();
            Cookie ck = null;
            for (Cookie c : cookies) {
                if (c.getName().equals("autoLogin")) {
                    ck = c;
                }
            }
                String UserId=null;
            if (ck != null) {
                UserId = ck.getValue().split("-")[1];
                User u=userService.findUserById(UserId);
                if (u!=null){
                    String face=u.getUserFace();
                    LaheiP=u.getUserId();
                    String G=u.getG();
                    model.addAttribute("G",G);
                    model.addAttribute("face",face);
                }
                if (u==null){
                    model.addAttribute("check","未激活");
                }else {
                    model.addAttribute("check","已激活");
                }
                List<User> user = userService.findAllUser();
                List<PinLuen> pinluen = userService.findUserP(UserId);
                for (PinLuen p:pinluen){
                    int number=0;
                    List<Hueifu> H=pinLuenService.findH(p.getIdP());
                    for ( Hueifu h:H){
                        if (h.getHC().equals("no")){
                            number++;
                        }
                    }
                    p.setNumber(number);
                }
                model.addAttribute("pinluen", pinluen);
                List<Haoyou> haoyous = haoyouService.findHaoyou(UserId);
                int n=0;
                for (Haoyou haoyou:haoyous){
                    if (haoyou.getLaheiN()==LaheiN){
                        n=1;
                    }
                }
                if (n==0){
                haoyouService.add(LaheiP,LaheiB,LaheiN);}
                model.addAttribute("user", user);
                List<Haoyou> haoyou = haoyouService.findHaoyou(UserId);
                model.addAttribute("haoyou", haoyou);
                List<Lahei> lahei = laheiService.findLahei(UserId);
                model.addAttribute("lahei", lahei);
                List<User>u1 =new ArrayList<User>() {
                };
                List<User>u2 = new ArrayList<User>(){};
                for (User uu:user) {
                    u1.add(uu);
                    for (Haoyou hh : haoyou) {
                        if (uu.getUserId()==hh.getLaheiN()){
                            u1.remove(uu);
                        }
                    }
                }
                for (User uu:u1) {
                    u2.add(uu);
                    for (Lahei ll : lahei) {
                        if (uu.getUserId()==ll.getLaheiN()){
                            u2.remove(uu);
                        }
                    }
                }
                model.addAttribute("user", u2);
            }



        return "alluser";

    }
    @RefreshCSRFToken
    @RequestMapping(value = "/shanchu", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String shanchu(Model model, ServletRequest servletRequest, ServletResponse servletResponse, String LaheiN){
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        String pp=(String) request.getSession().getAttribute("PassWord");
        int LaheiP = 0;


            Cookie[] cookies = request.getCookies();
            Cookie ck = null;
            for (Cookie c : cookies) {
                if (c.getName().equals("autoLogin")) {
                    ck = c;
                }
            }
            if (ck != null) {
                String UserId = ck.getValue().split("-")[1];
                User u=userService.findUserById(UserId);
                if (u!=null){
                    String face=u.getUserFace();
                    LaheiP=u.getUserId();
                    String G=u.getG();
                    model.addAttribute("G",G);
                    model.addAttribute("face",face);
                }
                if (u==null){
                    model.addAttribute("check","未激活");
                }else {
                    model.addAttribute("check","已激活");
                }
                List<User> user = userService.findAllUser();
                List<PinLuen> pinluen = userService.findUserP(UserId);
                for (PinLuen p:pinluen){
                    int number=0;
                    List<Hueifu> H=pinLuenService.findH(p.getIdP());
                    for ( Hueifu h:H){
                        if (h.getHC().equals("no")){
                            number++;
                        }
                    }
                    p.setNumber(number);
                }
                model.addAttribute("pinluen", pinluen);
                laheiService.qlahei(LaheiP,LaheiN);
                haoyouService.dadd(LaheiP,LaheiN);
                model.addAttribute("user", user);
                List<Haoyou> haoyou = haoyouService.findHaoyou(UserId);
                model.addAttribute("haoyou", haoyou);
                List<Lahei> lahei = laheiService.findLahei(UserId);
                model.addAttribute("lahei", lahei);
                List<User>u1 =new ArrayList<User>() {
                };
                List<User>u2 = new ArrayList<User>(){};
                for (User uu:user) {
                    u1.add(uu);
                    for (Haoyou hh : haoyou) {
                        if (uu.getUserId()==hh.getLaheiN()){
                            u1.remove(uu);
                        }
                    }
                }
                for (User uu:u1) {
                    u2.add(uu);
                    for (Lahei ll : lahei) {
                        if (uu.getUserId()==ll.getLaheiN()){
                            u2.remove(uu);
                        }
                    }
                }
                model.addAttribute("user", u2);
            }



        return "alluser";

    }
}
