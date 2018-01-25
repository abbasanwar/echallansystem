package com.echallansystem.Models;


public class Challan {
    private String firstname="";
    private String lastname="";
    private String cnic;
    private String licence;
    private String location="";
    private String mobilenumber="";
    private String fine="";
    private String datetime="";
    private String violationtype="";

    public Challan(){

    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public String getFine() {
        return fine;
    }

    public void setFine(String fine) {
        this.fine = fine;
    }

    public String getMobileNumber() {
        return mobilenumber;
    }

    public void setMobileNumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }


    public String getDateTime() {
        return datetime;
    }

    public void setDateTime(String datetime) {
        this.datetime = datetime;
    }


    public String getViolationtype() {
        return violationtype;
    }

    public void setViolationtype(String violationtype) {
        this.violationtype = violationtype;
    }


}
