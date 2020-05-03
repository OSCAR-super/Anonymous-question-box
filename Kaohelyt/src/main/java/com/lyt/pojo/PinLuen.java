package com.lyt.pojo;

import java.io.Serializable;

public class PinLuen  implements Serializable {

    private static final long serialVersionUID = 3436661817208280021L;
    private int IdP;
    private int UserB;
    private String neiron;
    private String de;
    private int number;

    public PinLuen(){}

    public PinLuen(int idP, int userB,String neiron,String de,int number) {
        this.IdP = idP;
        this.UserB = userB;
        this.neiron=neiron;
        this.de=de;
        this.number=number;
    }

    public int getIdP() {
        return IdP;
    }

    public void setIdP(int idP) {
        IdP = idP;
    }

    public int getUserB() {
        return UserB;
    }

    public void setUserB(int userB) {
        UserB = userB;
    }

    public String getNeiron() {
        return neiron;
    }

    public void setNeiron(String neiron) {
        this.neiron = neiron;
    }

    @Override
    public String toString() {
        return "PinLuen{" +
                "IdP=" + IdP +
                ", UserB=" + UserB +
                ", neiron='" + neiron + '\'' +
                ", de='" + de + '\'' +
                '}';
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
