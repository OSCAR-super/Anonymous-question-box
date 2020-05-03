package com.lyt.service;

import com.lyt.pojo.Lahei;
import com.lyt.pojo.User;

import java.util.List;

public interface LaheiService {
    void lahei(int LaheiP, String LaheiB, String LaheiN);
    void qlahei(int LaheiP, String LaheiN);
    List<Lahei> findLahei(String UserId);
}
