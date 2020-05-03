package com.lyt.pojo;

import java.io.Serializable;

public class Haoyou  implements Serializable {
    private static final long serialVersionUID = 6002920728096708265L;
    private int LaheiP;
    private String LaheiB;
    private int LaheiN;
    public int getLaheiP() {
        return LaheiP;
    }

    @Override
    public String toString() {
        return "Haoyou{" +
                "LaheiP=" + LaheiP +
                ", LaheiB=" + LaheiB +
                ", LaheiN=" + LaheiN +
                '}';
    }

    public void setLaheiP(int laheiP) {
        LaheiP = laheiP;
    }

    public String getLaheiB() {
        return LaheiB;
    }

    public Haoyou(int laheiP, String laheiB, int laheiN) {
        LaheiP = laheiP;
        LaheiB = laheiB;
        LaheiN = laheiN;
    }

    public void setLaheiB(String laheiB) {
        LaheiB = laheiB;
    }



    public int getLaheiN() {
        return LaheiN;
    }

    public void setLaheiN(int laheiN) {
        LaheiN = laheiN;
    }
    public Haoyou(){}
}
