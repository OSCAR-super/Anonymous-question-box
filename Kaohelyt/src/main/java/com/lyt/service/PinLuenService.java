package com.lyt.service;

import com.lyt.pojo.Hueifu;
import com.lyt.pojo.PinLuen;

import java.util.List;

public interface PinLuenService {
    List<PinLuen> findAllPinLuen();
    List<PinLuen>findPinLuenByUserId(int UserId);
    void addPinLuenByBAndP(int UserB, String neiron, int UserIdP);
    int getUserId(String UserName);

    void addH(int idP, String HN, String UserId);

    List<Hueifu> findH(int idP);

    PinLuen findPinLuenById(int idP);

    void dP(int idP);

    void set(int idP);

    List<Hueifu> findHR(int UserId);

    List<PinLuen> findPR(int UserId);
}
