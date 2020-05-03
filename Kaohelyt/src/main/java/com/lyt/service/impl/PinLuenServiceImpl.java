package com.lyt.service.impl;

import com.lyt.mapper.PinLuenMapper;
import com.lyt.mapper.UserMapper;
import com.lyt.pojo.Hueifu;
import com.lyt.pojo.PinLuen;
import com.lyt.service.PinLuenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PinLuenServiceImpl implements PinLuenService {
    @Autowired
    PinLuenMapper pinLuenMapper;
    @Autowired
    UserMapper userMapper;
    @Override
    public List<PinLuen> findAllPinLuen() {
        return pinLuenMapper.findAllPinLuen();
    }

    @Override
    public List<PinLuen> findPinLuenByUserId(int UserId) {
        return pinLuenMapper.findPinLuenByUserId(UserId);
    }

    @Override
    public void addPinLuenByBAndP(int UserB, String neiron,int UserIdP) {
        pinLuenMapper.addPinLuenByBAndP(UserB,neiron,UserIdP);
    }

    @Override
    public int getUserId(String UserName) {
        return userMapper.getUserId(UserName);
    }

    @Override
    public void addH(int idP,String HN,String UserId) {
        pinLuenMapper.addH(idP,HN,UserId);
    }

    @Override
    public List<Hueifu> findH(int idP) {
        return pinLuenMapper.findH(idP);
    }

    @Override
    public PinLuen findPinLuenById(int idP) {
        return pinLuenMapper.findPinLuenById(idP);
    }

    @Override
    public void dP(int idP) {
        pinLuenMapper.dP(idP);
    }

    @Override
    public void set(int idP) {
        pinLuenMapper.set(idP);
    }

    @Override
    public List<Hueifu> findHR(int UserId) {
        return pinLuenMapper.findHR(UserId);
    }

    @Override
    public List<PinLuen> findPR(int UserId) {
        return pinLuenMapper.findPR(UserId);
    }
}
