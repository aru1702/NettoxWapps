package com.nettox.nettoxwapps.DbModel;

public class DbModel_HrvData {

    private int id, hrv_result, bpm_avg, emot;
    private String hrv_time, comment;

    public DbModel_HrvData(int id, int hrv_result, int bpm_avg, String hrv_time, String comment, int emot) {
        this.id = id;
        this.hrv_result = hrv_result;
        this.bpm_avg = bpm_avg;
        this.emot = emot;
        this.hrv_time = hrv_time;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHrv_result() {
        return hrv_result;
    }

    public void setHrv_result(int hrv_result) {
        this.hrv_result = hrv_result;
    }

    public int getBpm_avg() {
        return bpm_avg;
    }

    public void setBpm_avg(int bpm_avg) {
        this.bpm_avg = bpm_avg;
    }

    public int getEmot() {
        return emot;
    }

    public void setEmot(int emot) {
        this.emot = emot;
    }

    public String getHrv_time() {
        return hrv_time;
    }

    public void setHrv_time(String hrv_time) {
        this.hrv_time = hrv_time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
