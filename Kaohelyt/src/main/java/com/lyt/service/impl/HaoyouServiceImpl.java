package com.lyt.service.impl;

import com.lyt.mapper.HaoyouMapper;
import com.lyt.pojo.Haoyou;
import com.lyt.pojo.User;
import com.lyt.service.HaoyouService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HaoyouServiceImpl implements HaoyouService {
    @Autowired
    HaoyouMapper haoyouMapper;
    @Override
    public void add(int LaheiP, String LaheiB,int LaheiN) {
        haoyouMapper.add(LaheiP,LaheiB,LaheiN);
    }
    @Override
    public void dadd(int LaheiP, String LaheiN) {
        haoyouMapper.dadd(LaheiP,LaheiN);
    }

    @Override
    public List<Haoyou> findHaoyou(String UserId) {
        return haoyouMapper.findHaoyou(UserId);
    }
}
