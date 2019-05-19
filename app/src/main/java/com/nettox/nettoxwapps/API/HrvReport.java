package com.nettox.nettoxwapps.API;

import java.util.Date;

public class HrvReport {

    private int id;
    private Date nowDate;
    private int hrvResult;
    private int emot;

    public HrvReport(int id, Date nowDate, int hrvResult, int emot) {
        this.id = id;
        this.nowDate = nowDate;
        this.hrvResult = hrvResult;
        this.emot = emot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getNowDate() {
        return nowDate;
    }

    public void setNowDate(Date nowDate) {
        this.nowDate = nowDate;
    }

    public int getHrvResult() {
        return hrvResult;
    }

    public void setHrvResult(int hrvResult) {
        this.hrvResult = hrvResult;
    }

    public int getEmot() {
        return emot;
    }

    public void setEmot(int emot) {
        this.emot = emot;
    }
}
