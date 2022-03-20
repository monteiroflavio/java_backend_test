package com.sarabada.entities;

public class ClientUserInfo {
    private int kills;
    private int worldDeaths;
    private String n;
    private int t;
    private String model;
    private String hModel;
    private String gRedTeam;
    private String gBlueTeam;
    private int c1;
    private int c2;
    private int hc;
    private int w;
    private int l;
    private int tt;
    private int tl;

    public ClientUserInfo(int kills, int worldDeaths, String n, int t, String model, String hModel,
                          String gRedTeam, String gBlueTeam, int c1, int c2, int hc, int w, int l,
                          int tt, int tl) {
        this.kills = kills;
        this.worldDeaths = worldDeaths;
        this.n = n;
        this.t = t;
        this.model = model;
        this.hModel = hModel;
        this.gRedTeam = gRedTeam;
        this.gBlueTeam = gBlueTeam;
        this.c1 = c1;
        this.c2 = c2;
        this.hc = hc;
        this.w = w;
        this.l = l;
        this.tt = tt;
        this.tl = tl;
    }

    public ClientUserInfo() { }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getWorldDeaths() {
        return worldDeaths;
    }

    public void setWorldDeaths(int worldDeaths) {
        this.worldDeaths = worldDeaths;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String gethModel() {
        return hModel;
    }

    public void sethModel(String hModel) {
        this.hModel = hModel;
    }

    public String getgRedTeam() {
        return gRedTeam;
    }

    public void setgRedTeam(String gRedTeam) {
        this.gRedTeam = gRedTeam;
    }

    public String getgBlueTeam() {
        return gBlueTeam;
    }

    public void setgBlueTeam(String gBlueTeam) {
        this.gBlueTeam = gBlueTeam;
    }

    public int getC1() {
        return c1;
    }

    public void setC1(int c1) {
        this.c1 = c1;
    }

    public int getC2() {
        return c2;
    }

    public void setC2(int c2) {
        this.c2 = c2;
    }

    public int getHc() {
        return hc;
    }

    public void setHc(int hc) {
        this.hc = hc;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getL() {
        return l;
    }

    public void setL(int l) {
        this.l = l;
    }

    public int getTt() {
        return tt;
    }

    public void setTt(int tt) {
        this.tt = tt;
    }

    public int getTl() {
        return tl;
    }

    public void setTl(int tl) {
        this.tl = tl;
    }

    public boolean equals (ClientUserInfo clientUserInfo) {
        return this.kills == clientUserInfo.getKills()
            && this.worldDeaths == clientUserInfo.getWorldDeaths()
            && this.n == clientUserInfo.getN()
            && this.t == clientUserInfo.getT()
            && this.model == clientUserInfo.gethModel()
            && this.hModel == clientUserInfo.gethModel()
            && this.gRedTeam == clientUserInfo.getgRedTeam()
            && this.gBlueTeam == clientUserInfo.getgBlueTeam()
            && this.c1 == clientUserInfo.getC1()
            && this.c2 == clientUserInfo.getC2()
            && this.hc == clientUserInfo.getHc()
            && this.w == clientUserInfo.getW()
            && this.l == clientUserInfo.getL()
            && this.tt == clientUserInfo.getTt()
            && this.tl == clientUserInfo.getTl();
    }
}
