package com.nettox.nettoxwapps.DbModel;

public class DbModel_TimeUsage {

    private int id, last_phone_sleep, phone_time_usage;

    public DbModel_TimeUsage(int id, int last_phone_sleep, int phone_time_usage) {
        this.id = id;
        this.last_phone_sleep = last_phone_sleep;
        this.phone_time_usage = phone_time_usage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLast_phone_sleep() {
        return last_phone_sleep;
    }

    public void setLast_phone_sleep(int last_phone_sleep) {
        this.last_phone_sleep = last_phone_sleep;
    }

    public int getPhone_time_usage() {
        return phone_time_usage;
    }

    public void setPhone_time_usage(int phone_time_usage) {
        this.phone_time_usage = phone_time_usage;
    }
}
