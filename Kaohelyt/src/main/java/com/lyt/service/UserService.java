package com.lyt.service;

import com.lyt.pojo.Jubao;
import com.lyt.pojo.PinLuen;
import com.lyt.pojo.User;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {
    User findUserById(String UserId);
    List<User> findAllUser();
    String sendEamil(HttpServletRequest request, HttpServletResponse response, String UserEmail);
    void sendEamil2(HttpServletRequest request, HttpServletResponse response, String UserEmail);
    void AddUser(String EmailCode, String UserName, String UserLoginName, String UserPassword1, String UserEmail);
    List<PinLuen>findUserP(String UserId);
    void ji(String UserId);
    void xiuUser(String UserId, String UserName);
    User findUserByNameAndPwd(String UserLoginName, String UserPassword);
    void UserFace(String UserFace, int UserId);

    User findUserByEmailAndPwd(String userLoginName, String psd);

    User findEmail(String userEmail);

    void k(String userName);

    void dk(String userName);

    void addJubao(int JID, String jubaoName, String jubaoN);

    List<Jubao> fj();

    void df(String jid);

    void f(String userId);

    void ff(String userId);

    User findUserByName(String userName);

    void sendEamil3(HttpServletRequest request, HttpServletResponse response, String userEmail);

    void xiuUserL(String userId, String userLoginName);

    void sendEamil4(HttpServletRequest request, HttpServletResponse response, String userEmail);

    void xiuUserP(String userId, String psd, String userPassword);

    void sendEamil5(HttpServletRequest request, HttpServletResponse response, String userEmail);

    void xiuUserE(String userId, String email);

    User findUserByEmail(String userEmail);
}
