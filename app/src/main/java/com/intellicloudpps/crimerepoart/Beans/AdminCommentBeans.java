package com.intellicloudpps.crimerepoart.Beans;

public class AdminCommentBeans {
    int id;
    String  crimeid;
    String date;
    String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String  getCrimeid() {
        return crimeid;
    }

    public void setCrimeid(String  crimeid) {
        this.crimeid = crimeid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public AdminCommentBeans(int id, String  crimeid, String date, String comment) {
        this.id = id;
        this.crimeid = crimeid;
        this.date = date;
        this.comment = comment;
    }

    public AdminCommentBeans() {
    }
}
