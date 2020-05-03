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
public class LaheiController {
    @Autowired
    HaoyouService haoyouService;
    @Autowired
    UserService userService;
    @Autowired
    LaheiService laheiService;
    @Autowired
    PinLuenService pinLuenService;
    @RefreshCSRFToken
    @RequestMapping(value = "/lahei", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String lahei(Model model, ServletRequest servletRequest, ServletResponse servletResponse,String LaheiB,String LaheiN){
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
                String y=DigestUtils.md5Hex(DigestUtils.md5Hex(u.getUserPassword()+ UserController.SystemStaticService.SOLT)+ UserController.SystemStaticService.SOLT);
                if (!y.equals(pp)){
                    request.getSession().removeAttribute("PassWord");
                    return "redirect:/UserLoginOut.action";
                }
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
                List<Lahei> laheia = laheiService.findLahei(UserId);
                int n=0;
                for (Lahei lahei:laheia){
                    if (lahei.getLaheiN()==Integer.parseInt(LaheiN)){
                        n=1;
                    }
                }
                if (n==0){
                laheiService.lahei(LaheiP,LaheiB,LaheiN);
                haoyouService.dadd(LaheiP,LaheiN);}
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
    @RequestMapping(value = "/qlahei", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String qlahei(Model model, ServletRequest servletRequest, ServletResponse servletResponse,String LaheiB,int LaheiN){
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
                laheiService.qlahei(LaheiP, String.valueOf(LaheiN));
                haoyouService.add(LaheiP,LaheiB,LaheiN);
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
