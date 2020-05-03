package com.lyt.service.impl;

import com.lyt.mapper.LaheiMapper;
import com.lyt.pojo.Lahei;
import com.lyt.pojo.User;
import com.lyt.service.LaheiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaheiServiceImpl implements LaheiService {
    @Autowired
    LaheiMapper laheiMapper;
    @Override
    public void lahei(int LaheiP, String LaheiB,String LaheiN) {
        laheiMapper.lahei(LaheiP,LaheiB,LaheiN);
    }
    @Override
    public void qlahei(int LaheiP, String LaheiN) {
        laheiMapper.qlahei(LaheiP,LaheiN);
    }
    @Override
    public List<Lahei> findLahei(String UserId) {
        return laheiMapper.findLahei(UserId);
    }
}
