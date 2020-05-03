package com.lyt.pojo;

import java.io.Serializable;

public class Hueifu implements Serializable {
    private static final long serialVersionUID = -8898298089004887440L;
    private int HueifuId;
    private int HP;
    private String HN;
    private String HC;
    private String HR;


    @Override
    public String toString() {
        return "Hueifu{" +
                "HueifuId=" + HueifuId +
                ", HP=" + HP +
                ", HN='" + HN + '\'' +
                ", HC='" + HC + '\'' +
                ", HR='" + HR + '\'' +
                '}';
    }

    public int getHueifuId() {
        return HueifuId;
    }

    public void setHueifuId(int hueifuId) {
        HueifuId = hueifuId;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public String getHN() {
        return HN;
    }

    public void setHN(String HN) {
        this.HN = HN;
    }

    public String getHC() {
        return HC;
    }

    public void setHC(String HC) {
        this.HC = HC;
    }

    public Hueifu(int hueifuId, int HP, String HN, String HC,String HR) {
        this.HueifuId = hueifuId;
        this.HP = HP;
        this.HN = HN;
        this.HC = HC;
        this.HR=HR;
    }
    public Hueifu(){
        super();
    }

    public String getHR() {
        return HR;
    }

    public void setHR(String HR) {
        this.HR = HR;
    }
}
