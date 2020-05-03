package com.lyt.mapper;

import com.lyt.pojo.Jubao;
import com.lyt.pojo.PinLuen;
import com.lyt.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User findUserById(String UserId);
    List<User> findAllUser();
    void AddUser(@Param("EmailCode") String EmailCode, @Param("UserName") String UserName, @Param("UserLoginName") String UserLoginName, @Param("UserPassword1") String UserPassword1, @Param("UserEmail") String UserEmail);
    List<PinLuen>findUserP(@Param("UserId") String UserId);
    void ji(@Param("UserId") String UserId);
    void xiuUser(@Param("UserId") String UserId, @Param("UserName") String UserName);
    void UserFace(@Param("UserFace") String UserFace, @Param("UserId") int UserId);
    User findUserByNameAndPwd(@Param("UserLoginName") String UserLoginName, @Param("UserPassword") String UserPassword);
    int getUserId(@Param("UserName") String UserName);

    User findUserByEmailAndPwd(@Param("UserLoginName") String userLoginName, @Param("UserPassword") String psd);

    User findEmail(@Param("UserEmail") String userEmail);

    void k(@Param("UserName") String userName);

    void dk(@Param("UserName") String userName);

    void addJubao(@Param("JID") int JID, @Param("UserName") String jubaoName, @Param("UserN") String jubaoN);

    List<Jubao> fj();

    void df(@Param("jid") String jid);

    void f(@Param("UserName") String userName);

    void ff(@Param("UserName") String userName);

    User findUserByName(@Param("UserName") String userName);

    void xiuUserL(@Param("UserId") String userId, @Param("UserL") String userLoginName);

    void xiuUserP(@Param("UserId") String userId, @Param("UserP") String psd, @Param("UserP1") String userPassword);

    void xiuUserE(@Param("UserId") String userId, @Param("UserE") String email);

    User findUserByEmail(@Param("UserEmail") String userEmail);
}
