package com.lyt.controller;

import com.lyt.interceptor.RefreshCSRFToken;
import com.lyt.pojo.Lahei;
import com.lyt.pojo.PinLuen;
import com.lyt.pojo.User;
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
import java.util.List;

@Controller
@Scope("prototype")
public class PinLuenController {
    @Autowired
    PinLuenService pinLuenService;
    @Autowired
    UserService userService;
    @Autowired
    LaheiService laheiService;
    @RefreshCSRFToken
    @RequestMapping(value = "/PinLuen", produces = "application/json;charset=UTF-8")
    public String PinLuen(String UserId,String UserName,Model model, ServletRequest servletRequest, ServletResponse servletResponse){
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        Cookie[] cookies = request.getCookies();
        Cookie ck = null;
        for (Cookie c : cookies) {
            if (c.getName().equals("autoLogin")) {
                ck = c;
            }
        }
        String UserIdd=null;
        if (ck != null) {
            UserIdd = ck.getValue().split("-")[1];
        }
        User u=userService.findUserById(UserIdd);
        if (u!=null){
        if (u.getUserJ().equals("no")){
            model.addAttribute("email","未激活");
        }else {
            model.addAttribute("email","已激活");
        }
        }

        if (u!=null){
            if (u.getG().equals("no")){
                model.addAttribute("G","未激活");
            }else {
                model.addAttribute("G","已激活");
            }
        }
        User uu=userService.findUserById(UserId);
        List<PinLuen> pinluen=pinLuenService.findPinLuenByUserId(Integer.parseInt(UserId));
        String K=uu.getK();
        List<Lahei> lahei=laheiService.findLahei(UserId);
        for (Lahei ll:lahei){
            if (ll.getLaheiN()==Integer.parseInt(UserIdd)){
                K="yes";
            }
        }
        model.addAttribute("UserId",uu.getUserId());
        model.addAttribute("K",K);
        model.addAttribute("pinluen",pinluen);
        return "pinluen";
    }
    @RefreshCSRFToken
    @RequestMapping(value = "/AddPinLuen", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String AddPinLuen(Model model,int UserB, String neiron, ServletRequest servletRequest){
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
        pinLuenService.addPinLuenByBAndP(UserB,neiron,us.getUserId());
        String UserId= String.valueOf(UserB);
        User u=userService.findUserById(UserId);
        model.addAttribute("UserName",u.getUserName());
        model.addAttribute("UserId",UserId);
        return "redirect:/PinLuen.action";
    }
}
