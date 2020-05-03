package com.lyt.service;

import com.lyt.pojo.Haoyou;
import com.lyt.pojo.User;

import java.util.List;

public interface HaoyouService {
    void add(int LaheiP, String LaheiB, int LaheiN);
    void dadd(int LaheiP, String LaheiN);
    List<Haoyou> findHaoyou(String UserId);
}
