package com.intellicloudpps.crimerepoart.Beans;

public class ViewAllCrimeBeans {

    private  int Id;
    private String Title;
    private String Description;
    private String ComplentDate;
    private String Status;
    private String crimedate;
    private String Time;
    private String Category;
    private String Area;
    private String Pincode;


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getComplentDate() {
        return ComplentDate;
    }

    public void setComplentDate(String complentDate) {
        ComplentDate = complentDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getCrimedate() {
        return crimedate;
    }

    public void setCrimedate(String crimedate) {
        this.crimedate = crimedate;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getPincode() {
        return Pincode;
    }

    public void setPincode(String pincode) {
        Pincode = pincode;
    }

    public ViewAllCrimeBeans(int id, String title, String description, String complentDate, String status, String crimedate, String time, String category, String area, String pincode) {
        Id = id;
        Title = title;
        Description = description;
        ComplentDate = complentDate;
        Status = status;
        this.crimedate = crimedate;
        Time = time;
        Category = category;
        Area = area;
        Pincode = pincode;
    }

    public ViewAllCrimeBeans() {

    }

}
