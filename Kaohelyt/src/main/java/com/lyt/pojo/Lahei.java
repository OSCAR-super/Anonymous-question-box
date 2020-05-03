package com.lyt.pojo;

import java.io.Serializable;

public class Lahei  implements Serializable {
    private static final long serialVersionUID = -5252977001223184616L;

    public Lahei(int laheiP, String laheiB, int laheiN) {
        LaheiP = laheiP;
        LaheiB = laheiB;
        LaheiN = laheiN;
    }

    private int LaheiP;
    private String LaheiB;
    private int LaheiN;

    public int getLaheiP() {
        return LaheiP;
    }

    public void setLaheiP(int laheiP) {
        LaheiP = laheiP;
    }

    public String getLaheiB() {
        return LaheiB;
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
    @Override
    public String toString() {
        return "Lahei{" +
                "LaheiP=" + LaheiP +
                ", LaheiB=" + LaheiB +
                ", LaheiN=" + LaheiN +
                '}';
    }
    public Lahei(){}
}
