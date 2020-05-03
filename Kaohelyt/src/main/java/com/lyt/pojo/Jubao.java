package com.lyt.pojo;

import java.io.Serializable;

public class Jubao  implements Serializable {
    private static final long serialVersionUID = -4129442657327503022L;
    @Override
    public String toString() {
        return "Jubao{" +
                "JubaoId=" + JubaoId +
                ", JubaoName='" + JubaoName + '\'' +
                ", JubaoN='" + JubaoN + '\'' +
                ", JD='" + JD + '\'' +
                ", JID='" + JID + '\'' +
                '}';
    }

    private int JubaoId;
    private String JubaoName;
    private String JubaoN;
    private String JD;
    private int JID;
    public int getJubaoId() {
        return JubaoId;
    }

    public void setJubaoId(int jubaoId) {
        JubaoId = jubaoId;
    }

    public String getJubaoName() {
        return JubaoName;
    }

    public void setJubaoName(String jubaoName) {
        JubaoName = jubaoName;
    }

    public String getJubaoN() {
        return JubaoN;
    }

    public void setJubaoN(String jubaoN) {
        JubaoN = jubaoN;
    }

    public Jubao(int jubaoId, String jubaoName, String jubaoN, String JD, int JID) {
        JubaoId = jubaoId;
        JubaoName = jubaoName;
        JubaoN = jubaoN;
        this.JD = JD;
        this.JID = JID;
    }
    public Jubao(){}

    public String getJD() {
        return JD;
    }

    public void setJD(String JD) {
        this.JD = JD;
    }

    public int getJID() {
        return JID;
    }

    public void setJID(int JID) {
        this.JID = JID;
    }
}
