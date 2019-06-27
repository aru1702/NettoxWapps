package com.nettox.nettoxwapps.DbModel;

public class DbModel_TimeUsage {

    private int _id, lastPhoneSleep, phoneTimeUsage;
    private String lastUpdate;

    public DbModel_TimeUsage(int _id, int lastPhoneSleep, int phoneTimeUsage, String lastUpdate) {
        this._id = _id;
        this.lastPhoneSleep = lastPhoneSleep;
        this.phoneTimeUsage = phoneTimeUsage;
        this.lastUpdate = lastUpdate;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getLastPhoneSleep() {
        return lastPhoneSleep;
    }

    public void setLastPhoneSleep(int lastPhoneSleep) {
        this.lastPhoneSleep = lastPhoneSleep;
    }

    public int getPhoneTimeUsage() {
        return phoneTimeUsage;
    }

    public void setPhoneTimeUsage(int phoneTimeUsage) {
        this.phoneTimeUsage = phoneTimeUsage;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
