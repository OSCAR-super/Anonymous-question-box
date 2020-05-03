package com.lyt.controller;

import com.lyt.interceptor.RefreshCSRFToken;
import com.lyt.pojo.*;
import com.lyt.service.HaoyouService;
import com.lyt.service.LaheiService;
import com.lyt.service.PinLuenService;
import com.lyt.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Controller
@Scope("prototype")
public class UserController {
    @Autowired
    LaheiService laheiService;
    @Autowired
    UserService userService;
    @Autowired
    HaoyouService haoyouService;
    @Autowired
    PinLuenService pinLuenService;
    @RequestMapping(value = "/fN",  produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String fN(String UserName){
        User u=userService.findUserByName(UserName);

        if (u!=null){

            return "0";
        }else {

            return "1";
        }


    }
    @RequestMapping(value = "/fE",  produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String fE(String UserEmail){
        User u=userService.findUserByEmail(UserEmail);

        if (u!=null){

            return "0";
        }else {

            return "1";
        }


    }
    @RefreshCSRFToken
    @RequestMapping(value = "/showUser", produces = "application/json;charset=UTF-8")
    public String showUser( Model model, ServletRequest servletRequest, ServletResponse servletResponse){
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;

            Cookie[] cookies=request.getCookies();
            Cookie ck=null;
            for (Cookie c:cookies){
                if (c.getName().equals("autoLogin")){
                    ck=c;
                }
            }
            if (ck!=null){
                String UserId=ck.getValue().split("-")[1];
                User user=userService.findUserById(UserId);
                model.addAttribute("user",user);
                List<PinLuen>pinLuens=pinLuenService.findPinLuenByUserId(Integer.parseInt(UserId));
                List<Hueifu>hueifus=pinLuenService.findHR(Integer.parseInt(UserId));
                List<PinLuen>pinLuenss=pinLuenService.findPR(Integer.parseInt(UserId));
                int pn=0;
                int hn=0;
                int ppp=0;
                for (PinLuen pp:pinLuens) {
                    pn++;
                }
                for (Hueifu hh:hueifus) {
                    hn++;
                }
                for (PinLuen pp:pinLuenss) {
                    ppp++;
                }
                model.addAttribute("pp",pn);
                model.addAttribute("hh",hn);
                model.addAttribute("ppp",ppp);

            }

        return "UserX";
    }
    @RefreshCSRFToken
    @RequestMapping(value = "/AllUser", produces = "application/json;charset=UTF-8")
    public String AllUser(String v,Model model, ServletRequest servletRequest, ServletResponse servletResponse){
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        String pp=(String) request.getSession().getAttribute("PassWord");
        if (v==null){
            v="1";
            }
            Cookie[] cookies = request.getCookies();
            Cookie ck = null;
            for (Cookie c : cookies) {
                if (c.getName().equals("autoLogin")) {
                    ck = c;
                    if (v.equals("y")){
                        ck=null;
                    }

                }}
           String UserId=null;
            if (ck != null) {
               UserId = ck.getValue().split("-")[1];
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
                List<Haoyou> haoyou = haoyouService.findHaoyou(UserId);
                model.addAttribute("haoyou", haoyou);
                List<Lahei> lahei = laheiService.findLahei(UserId);
                model.addAttribute("lahei", lahei);
                User u=userService.findUserById(UserId);

                    String y = DigestUtils.md5Hex(DigestUtils.md5Hex(u.getUserPassword() + SystemStaticService.SOLT) + SystemStaticService.SOLT);
                    if (!y.equals(pp)) {
                        request.getSession().removeAttribute("PassWord");
                        return "redirect:/UserLoginOut.action";
                    }

                if (u!=null){
                    String face=u.getUserFace();
                    String G=u.getG();
                    model.addAttribute("G",G);
                    model.addAttribute("face",face);
                }
                if (u==null){
                    model.addAttribute("check","未激活");
                }else {
                    model.addAttribute("check","已激活");
                }
                model.addAttribute("c","1");
                List<User>u1 =new ArrayList<User>() {
                };
                List<User>u2 = new ArrayList<User>(){};
                List<User> user = userService.findAllUser();
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
        if (v.equals("y")){
            List<User> q = userService.findAllUser();
            model.addAttribute("face","123");
            model.addAttribute("user", q);
        }

        return "alluser";
    }
    @RefreshCSRFToken
    @RequestMapping(value = "/dH", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String dH(int IdP,Model model, ServletRequest servletRequest, ServletResponse servletResponse){
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        String pp=(String) request.getSession().getAttribute("PassWord");
        Cookie[] cookies = request.getCookies();
        Cookie ck = null;
        for (Cookie c : cookies) {
            if (c.getName().equals("autoLogin")) {
                ck = c;
            }}
        String UserId=null;
        if (ck != null) {
            UserId = ck.getValue().split("-")[1];

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
            pinLuenService.dP(IdP);
            List<Haoyou> haoyou = haoyouService.findHaoyou(UserId);
            model.addAttribute("haoyou", haoyou);
            List<Lahei> lahei = laheiService.findLahei(UserId);
            model.addAttribute("lahei", lahei);
            User u=userService.findUserById(UserId);
            String y=DigestUtils.md5Hex(DigestUtils.md5Hex(u.getUserPassword()+SystemStaticService.SOLT)+SystemStaticService.SOLT);
            if (!y.equals(pp)){
                request.getSession().removeAttribute("PassWord");
                return "redirect:/UserLoginOut.action";
            }
            if (u!=null){
                String face=u.getUserFace();
                String G=u.getG();
                model.addAttribute("G",G);
                model.addAttribute("face",face);
            }
            if (u==null){
                model.addAttribute("check","未激活");
            }else {
                model.addAttribute("check","已激活");
            }
            List<User>u1 =new ArrayList<User>() {
            };
            List<User>u2 = new ArrayList<User>(){};
            List<User> user = userService.findAllUser();
            for (User uu:user) {
                u1.add(uu);
                for (Haoyou hh : haoyou) {
                    if (uu.getUserName().equals(hh.getLaheiB())){
                        u1.remove(uu);
                    }
                }
            }
            for (User uu:u1) {
                u2.add(uu);
                for (Lahei ll : lahei) {
                    if (uu.getUserName().equals(ll.getLaheiB())){
                        u2.remove(uu);
                    }
                }
            }
            model.addAttribute("user", u2);
        }

        return "alluser";
    }
    @RefreshCSRFToken
    @RequestMapping(value = "/ddH", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String ddH(int IdP,int UserB,Model model, ServletRequest servletRequest, ServletResponse servletResponse){
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        String pp=(String) request.getSession().getAttribute("PassWord");
        Cookie[] cookies = request.getCookies();
        Cookie ck = null;
        for (Cookie c : cookies) {
            if (c.getName().equals("autoLogin")) {
                ck = c;
            }}
        String UserId=null;
        if (ck != null) {
            UserId = ck.getValue().split("-")[1];
            }
            pinLuenService.dP(IdP);
            List<Haoyou> haoyou = haoyouService.findHaoyou(UserId);
            model.addAttribute("haoyou", haoyou);
            List<Lahei> lahei = laheiService.findLahei(UserId);
            model.addAttribute("lahei", lahei);
            User u = userService.findUserById(UserId);
            String y = DigestUtils.md5Hex(DigestUtils.md5Hex(u.getUserPassword() + SystemStaticService.SOLT) + SystemStaticService.SOLT);

        User us=userService.findUserById(UserId);
        String UserIdd= String.valueOf(UserB);
        User uu=userService.findUserById(UserIdd);
        model.addAttribute("UserName",uu.getUserName());
        model.addAttribute("UserId",UserIdd);
        return "redirect:/PinLuen.action";
    }
    @RefreshCSRFToken
    @RequestMapping(value = "/AddH", produces = "application/json;charset=UTF-8")
    public String AddH(int IdP,Model model, ServletRequest servletRequest, ServletResponse servletResponse){
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        Cookie[] cookies = request.getCookies();
        Cookie ck = null;
        for (Cookie c : cookies) {
            if (c.getName().equals("autoLogin")) {
                ck = c;
            }
        }
            String UserId = null;
            if (ck != null) {
                UserId = ck.getValue().split("-")[1];
            }

        User u=userService.findUserById(UserId);
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
        PinLuen pinLuen=pinLuenService.findPinLuenById(IdP);
        List<Hueifu> H=pinLuenService.findH(IdP);
        pinLuenService.set(pinLuen.getIdP());

        model.addAttribute("H",H);
        model.addAttribute("PinLuen",pinLuen);

        return "Hueifu";
    }
    @RefreshCSRFToken
    @RequestMapping(value = "/AddHH", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String AddPinLuen(Model model,int IdP, String HN, ServletRequest servletRequest, ServletResponse servletResponse){
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        Cookie[] cookies = request.getCookies();
        Cookie ck = null;
        for (Cookie c : cookies) {
            if (c.getName().equals("autoLogin")) {
                ck = c;
            }
        }
        String UserId = null;
        if (ck != null) {
            UserId = ck.getValue().split("-")[1];
        }

        User u=userService.findUserById(UserId);
        pinLuenService.addH(IdP,HN,u.getUserName());
        model.addAttribute("IdP",IdP);
        return "redirect:/AddH.action";
    }
    @RefreshCSRFToken
    @RequestMapping(value = "/AddUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String AddUser(HttpServletRequest request,String EmailCode,String UserName,String UserLoginName,String UserPassword1,String UserPassword2,String UserEmail,String yy){
        String e=(String)request.getSession().getAttribute("EmailCode");
        User u=userService.findUserByName(UserName);
        if (u!=null){
            return "no";
        }
        request.getSession().removeAttribute("EmailCode");
        String password = DigestUtils.md5Hex(UserPassword1);
        //此处加密后加盐再进行加密
        String psd=DigestUtils.md5Hex(password+SystemStaticService.SOLT);

        if (yy.equals("lytfuda2020sdl")&&UserPassword1.equals(UserPassword2)&&EmailCode.equalsIgnoreCase(e)){userService.AddUser(EmailCode,UserName,UserLoginName,psd,UserEmail);
            return "chenggong";
        }else {
            return "no";
        }

    }
    public class SystemStaticService {
        public static final String SOLT="wdnmd";

    }
    @RefreshCSRFToken
    @RequestMapping(value = "/jupAddUser", produces = "application/json;charset=UTF-8")
    public String jupAddUser(){
        return "add";
    }
    @RefreshCSRFToken
    @RequestMapping(value = "/xiuUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String xiuUser(Model model, ServletRequest servletRequest, ServletResponse servletResponse,String UserName){
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
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

                userService.xiuUser(UserId,UserName);
            }


        return "c2";
    }
    @RefreshCSRFToken
    @RequestMapping(value = "/xiuUserL", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String xiuUserL(Model model, ServletRequest servletRequest, ServletResponse servletResponse,String UserLoginName){
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
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
            if (u.getUserLoginName().equals(UserLoginName)){
                return "s2";
            }
            userService.xiuUserL(UserId,UserLoginName);
        }


        return "c2";
    }
    @RefreshCSRFToken
    @RequestMapping(value = "/xiuUserE", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String xiuUserE(Model model, ServletRequest servletRequest, ServletResponse servletResponse,String email){
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
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
            if (u.getUserEmail().equals(email)){
                return "s2";
            }
            userService.xiuUserE(UserId,email);
        }


        return "c2";
    }
    @RefreshCSRFToken
    @RequestMapping(value = "/xiuUserP", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String xiuUserP(Model model, ServletRequest servletRequest, ServletResponse servletResponse,String UserP,String UserPassword,String UserPassword2){
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        Cookie[] cookies = request.getCookies();
        Cookie ck = null;
        for (Cookie c : cookies) {
            if (c.getName().equals("autoLogin")) {
                ck = c;
            }
        }
        if (ck != null) {
            String UserId = ck.getValue().split("-")[1];
            if (UserPassword==UserPassword2){
            User u=userService.findUserById(UserId);
            String password = DigestUtils.md5Hex(UserP);
            //此处加密后加盐再进行加密
            String psd=DigestUtils.md5Hex(password+SystemStaticService.SOLT);
            String password2 = DigestUtils.md5Hex(UserPassword);
            //此处加密后加盐再进行加密
            String psd2=DigestUtils.md5Hex(password2+SystemStaticService.SOLT);
            userService.xiuUserP(UserId,psd,psd2);}
        }


        return "c2";
    }
    @RefreshCSRFToken
    @RequestMapping(value = "/xiugai", produces = "application/json;charset=UTF-8")
    public String xiugai(Model model, ServletRequest servletRequest, ServletResponse servletResponse){
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
            Cookie[] cookies=request.getCookies();
            Cookie ck=null;
            for (Cookie c:cookies){
                if (c.getName().equals("autoLogin")){
                    ck=c;
                }
            }
            if (ck!=null){
                String UserId=ck.getValue().split("-")[1];
                User u=userService.findUserById(UserId);
                User user=userService.findUserById(UserId);
                model.addAttribute("userName",user.getUserName());
            }

        return "xiugai_nicheng";
    }

    @RequestMapping(value = "/touxiang", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String touxiang(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        String filename = null;
        int sno=-1;
        String sname=null;
        int kaohename=-1;
        boolean isMultipart= ServletFileUpload.isMultipartContent(request);
        if(isMultipart)
        {
            FileItemFactory factory=new DiskFileItemFactory();
            ServletFileUpload upload=new ServletFileUpload(factory);
            try {
                List<FileItem> items=upload.parseRequest(request);
                Iterator<FileItem> it = items.iterator();
                while(it.hasNext())
                {
                    FileItem item = it.next();
                    String itemname = item.getFieldName();


                    if(item.isFormField()) {

                    }else
                    {
                        filename=item.getName();
                        //String path=request.getSession().getServletContext().getRealPath("upload");
                        String path="C:\\upload";
                        File file=new File(path,filename);
                        item.write(file);
                        request.setAttribute("news", filename+"上传成功,谢谢分享");

                        //System.out.println(filename+"上传成功!!!");

                    }

                }
            } catch (FileUploadException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        String UserFace=filename;
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
        int Id=u.getUserId();
        userService.UserFace(UserFace,Id);
        return null;
    }

    @RequestMapping(value = "/ji" , method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String ji(String UserId){
        userService.ji(UserId);
        return null;
    }
    @RefreshCSRFToken
    @RequestMapping(value = "/UserLoginS", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String UserLoginS(String isAutoLogin,Model model,HttpServletRequest request, HttpServletResponse response,String UserLoginName,String UserPassword,String verifycode){
        String ocode=(String)request.getSession().getAttribute("CHECKCODE_SERVER");
        request.getSession().removeAttribute("CHECKCODE_SERVER");

        if (verifycode.equalsIgnoreCase(ocode)){
            String password = DigestUtils.md5Hex(UserPassword);
            //此处加密后加盐再进行加密
            String psd=DigestUtils.md5Hex(password+SystemStaticService.SOLT);
            String y=DigestUtils.md5Hex(DigestUtils.md5Hex(psd+SystemStaticService.SOLT)+SystemStaticService.SOLT);
            request.getSession().setAttribute("PassWord",y);
            User u=null;
            String[] a = UserLoginName.split("\\.");
            String UserEmial=a[a.length-1];
            if (UserEmial.equals("com")){
                u=userService.findUserByEmailAndPwd(UserLoginName,psd);

            }else {
                u=userService.findUserByNameAndPwd(UserLoginName,psd);

            }

            if (u!=null){
                response.setHeader("Set-Cookie", "cookiename=value; Path=/;Domain=domainvalue;Max-Age=seconds;HTTPOnly");
                int id=u.getUserId();
                Cookie cookie=new Cookie("autoLogin",UserLoginName+"-"+id);
                cookie.setMaxAge(3600);
                cookie.setPath(request.getContextPath()+"/");
                response.addCookie(cookie);
            }
            if (u!=null){
                if (isAutoLogin!=null){
                if (isAutoLogin.equals("true")){
                request.getSession().setAttribute("autoL","yes");}}
                if (u.getF().equals("yes")){
                    model.addAttribute("login_msg","你的账号被封了!");
                    return "redirect:/UserLogin2.action";
                }else {
                return "redirect:/AllUser.action";}
            }else {
                model.addAttribute("login_msg","用户名或密码不正确!");
                return "redirect:/UserLogin2.action";
            }


        }else {
            model.addAttribute("login_msg","验证码不正确!");
            return "redirect:/UserLogin2.action";
        }

        }
    @RefreshCSRFToken
    @RequestMapping(value = "/K", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String K(String UserName, Model model, ServletRequest servletRequest, ServletResponse servletResponse){
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;

        Cookie[] cookies=request.getCookies();
        Cookie ck=null;
        for (Cookie c:cookies){
            if (c.getName().equals("autoLogin")){
                ck=c;
            }
        }
        if (ck!=null){
            String UserId=ck.getValue().split("-")[1];
            userService.k(UserName);
            User user=userService.findUserById(UserId);
            model.addAttribute("user",user);
            List<PinLuen>pinLuens=pinLuenService.findPinLuenByUserId(Integer.parseInt(UserId));
            List<Hueifu>hueifus=pinLuenService.findHR(Integer.parseInt(UserId));
            List<PinLuen>pinLuenss=pinLuenService.findPR(Integer.parseInt(UserId));
            int pn=0;
            int hn=0;
            int ppp=0;
            for (PinLuen pp:pinLuens) {
                pn++;
            }
            for (Hueifu hh:hueifus) {
                hn++;
            }
            for (PinLuen pp:pinLuenss) {
                ppp++;
            }
            model.addAttribute("pp",pn);
            model.addAttribute("hh",hn);
            model.addAttribute("ppp",ppp);
        }

        return "UserX";
    }
    @RefreshCSRFToken
    @RequestMapping(value = "/dK", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String dK(String UserName, Model model, ServletRequest servletRequest, ServletResponse servletResponse){
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;

        Cookie[] cookies=request.getCookies();
        Cookie ck=null;
        for (Cookie c:cookies){
            if (c.getName().equals("autoLogin")){
                ck=c;
            }
        }
        if (ck!=null){
            String UserId=ck.getValue().split("-")[1];
            userService.dk(UserName);
            User user=userService.findUserById(UserId);
            model.addAttribute("user",user);
            List<PinLuen>pinLuens=pinLuenService.findPinLuenByUserId(Integer.parseInt(UserId));
            List<Hueifu>hueifus=pinLuenService.findHR(Integer.parseInt(UserId));
            List<PinLuen>pinLuenss=pinLuenService.findPR(Integer.parseInt(UserId));
            int pn=0;
            int hn=0;
            int ppp=0;
            for (PinLuen pp:pinLuens) {
                pn++;
            }
            for (Hueifu hh:hueifus) {
                hn++;
            }
            for (PinLuen pp:pinLuenss) {
                ppp++;
            }
            model.addAttribute("pp",pn);
            model.addAttribute("hh",hn);
            model.addAttribute("ppp",ppp);
        }

        return "UserX";
    }
    @RefreshCSRFToken
        @RequestMapping(value = "/UserLoginOut", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
        public String UserLoginOut(HttpServletRequest request, HttpServletResponse response){
            request.getSession().removeAttribute("autoL");
            return "redirect:/UserLogin.action";
    }
    @RequestMapping(value = "/showeinvoice", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void showEInvoice(String v,HttpServletRequest request,HttpServletResponse response){
        if (v==null){
            v="1";
        }
        Cookie[] cookies=request.getCookies();
        Cookie ck=null;
        for (Cookie c:cookies){
            if (c.getName().equals("autoLogin")){
                ck=c;
                if (v.equals("y")){
                    ck=null;
                }
            }
        }
        String UserId=null;
        if (ck!=null){
            UserId=ck.getValue().split("-")[1];
        }
        User u=userService.findUserById(UserId);
        String face=null;
        if (u!=null){
            face=u.getUserFace();

        }
        FileInputStream fis = null;
        OutputStream os = null;
        String filepath = "C:\\upload\\"+face;     //path是你服务器上图片的绝对路径
        File file = new File(filepath);
        if(file.exists()){
            try {
                fis = new FileInputStream(file);
                long size = file.length();
                byte[] temp = new byte[(int) size];
                fis.read(temp, 0, (int) size);
                fis.close();
                byte[] data = temp;
                response.setContentType("image/jpg");
                os = response.getOutputStream();
                os.write(data);
                os.flush();
                os.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @RequestMapping(value = "/showeinvoices", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void showEInvoices(String UserId,String UserFace,String v,HttpServletRequest request,HttpServletResponse response){
        if (v==null){
            v="1";
        }
        Cookie[] cookies=request.getCookies();
        Cookie ck=null;
        for (Cookie c:cookies){
            if (c.getName().equals("autoLogin")){
                ck=c;
                if (v.equals("y")){
                    ck=null;
                }
            }
        }
        String UserIdd=null;
        if (ck!=null){
            UserIdd=ck.getValue().split("-")[1];
        }
        User u=userService.findUserById(UserIdd);
        String face=null;
        if (u!=null){
            if (UserFace!=null){
            face=UserFace;}else {
                User uu=userService.findUserById(UserId);
                face=uu.getUserFace();
            }
            if (face.equals("123")){face="01.jpg";}
        }
        FileInputStream fis = null;
        OutputStream os = null;
        String filepath = "C:\\upload\\"+face;     //path是你服务器上图片的绝对路径
        File file = new File(filepath);
        if(file.exists()){
            try {
                fis = new FileInputStream(file);
                long size = file.length();
                byte[] temp = new byte[(int) size];
                fis.read(temp, 0, (int) size);
                fis.close();
                byte[] data = temp;
                response.setContentType("image/jpg");
                os = response.getOutputStream();
                os.write(data);
                os.flush();
                os.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
