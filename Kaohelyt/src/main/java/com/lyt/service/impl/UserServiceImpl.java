package com.lyt.service.impl;

import com.lyt.Email.CodeUtil;
import com.lyt.Email.SendEamilUtil;
import com.lyt.mapper.UserMapper;
import com.lyt.pojo.Jubao;
import com.lyt.pojo.PinLuen;
import com.lyt.pojo.User;
import com.lyt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User findUserById(String UserId) {
        return userMapper.findUserById(UserId);
    }

    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }

    @Override
    public String sendEamil(HttpServletRequest request, HttpServletResponse response,String UserEmail) {
        User email=findEmail(UserEmail);
        if (email==null) {
            String toEmail = UserEmail;
            String code;
            SendEamilUtil sendEamilUtil = new SendEamilUtil();
            CodeUtil codeUtil = new CodeUtil();
            code = codeUtil.generateUniqueCode();

            try {
                sendEamilUtil.sendemail(request, response, toEmail, code);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return "已发送，10分钟内有效";
        }else {
            return "你的邮箱已被注册！";
        }

    }

    @Override
    public void sendEamil2(HttpServletRequest request, HttpServletResponse response, String UserEmail) {
        String toEmail=UserEmail;
        SendEamilUtil sendEamilUtil=new SendEamilUtil();
        try {
            sendEamilUtil.sendemail(request,response,toEmail,"<a href='http://111.230.173.74/jihuo.action'>点我激活</a>");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void AddUser(String EmailCode,String UserName, String UserLoginName, String UserPassword1,String UserEmail) {
        userMapper.AddUser(EmailCode,UserName,UserLoginName,UserPassword1,UserEmail);
    }

    @Override
    public List<PinLuen> findUserP(String UserId) {
        return userMapper.findUserP(UserId);
    }

    @Override
    public void ji(String UserId) {
        userMapper.ji(UserId);
    }

    @Override
    public void xiuUser(String UserId,String UserName) {
        userMapper.xiuUser(UserId,UserName);
    }

    @Override
    public User findUserByNameAndPwd(String UserLoginName, String UserPassword) {
        return userMapper.findUserByNameAndPwd(UserLoginName,UserPassword);
    }

    @Override
    public void UserFace(String UserFace, int UserId) {
        userMapper.UserFace(UserFace,UserId);
    }

    @Override
    public User findUserByEmailAndPwd(String userLoginName, String psd) {
        return userMapper.findUserByEmailAndPwd(userLoginName,psd);
    }

    @Override
    public User findEmail(String userEmail) {
        return userMapper.findEmail(userEmail);
    }

    @Override
    public void k(String userName) {
        userMapper.k(userName);
    }

    @Override
    public void dk(String userName) {
        userMapper.dk(userName);
    }

    @Override
    public void addJubao(int JID,String jubaoName, String jubaoN) {
        userMapper.addJubao(JID,jubaoName,jubaoN);
    }

    @Override
    public List<Jubao> fj() {
        return userMapper.fj();
    }

    @Override
    public void df(String jid) {
        userMapper.df(jid);
    }

    @Override
    public void f(String userId) {
        userMapper.f(userId);
    }

    @Override
    public void ff(String userId) {
        userMapper.ff(userId);
    }

    @Override
    public User findUserByName(String userName) {
        return userMapper.findUserByName(userName);
    }

    @Override
    public void sendEamil3(HttpServletRequest request, HttpServletResponse response, String userEmail) {
        String toEmail=userEmail;
        SendEamilUtil sendEamilUtil=new SendEamilUtil();
        try {
            sendEamilUtil.sendemail(request,response,toEmail,"<a href='http://111.230.173.74/xL.action'>点我修改用户名</a>");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void xiuUserL(String userId, String userLoginName) {
        userMapper.xiuUserL(userId,userLoginName);
    }

    @Override
    public void sendEamil4(HttpServletRequest request, HttpServletResponse response, String userEmail) {
        String toEmail=userEmail;
        SendEamilUtil sendEamilUtil=new SendEamilUtil();
        try {
            sendEamilUtil.sendemail(request,response,toEmail,"<a href='http://111.230.173.74/xP.action'>点我修改密码</a>");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void xiuUserP(String userId, String psd, String userPassword) {
        userMapper.xiuUserP(userId,psd,userPassword);
    }

    @Override
    public void sendEamil5(HttpServletRequest request, HttpServletResponse response, String userEmail) {
        String toEmail=userEmail;
        SendEamilUtil sendEamilUtil=new SendEamilUtil();
        try {
            sendEamilUtil.sendemail(request,response,toEmail,"<a href='http://111.230.173.74/xE.action'>点我修改邮箱</a>");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void xiuUserE(String userId, String email) {
        userMapper.xiuUserE(userId,email);
    }

    @Override
    public User findUserByEmail(String userEmail) {
        return userMapper.findUserByEmail(userEmail);
    }


}
