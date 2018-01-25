package com.echallansystem.Models;


import java.io.Serializable;

public class LicenceVerification implements Serializable {


    private String licenceholderName;
    private String licenceholderfName;
    private String licenceholderdistrictName;
    private String drivingLicense;
    private String licencetype;
    private String date_expiry;
    private String date_issue;

    public LicenceVerification(){

    }

    public LicenceVerification(String licenceholderName,
                               String  licenceholderfName,
                               String licenceholderdistrictName,
                               String drivingLicense,
                                String licencetype,
                               String date_expiry,
                               String date_issue) {
        this.licenceholderName =licenceholderName;
        this.licenceholderfName = licenceholderfName;
        this.licenceholderdistrictName = licenceholderdistrictName;
        this.drivingLicense = drivingLicense;
        this.licencetype = licencetype;
        this.date_expiry = date_expiry;
        this.date_issue = date_issue;

    }
    public String getLicenceHolderName() {
        return licenceholderName;
    }

    public void setLicenceHolderName(String licenceholderName) {this.licenceholderName = licenceholderName;}

    public String getLicenceHolderfName() {
        return licenceholderfName;
    }

    public void setLicenceHolderfName(String licenceholderfName) {this.licenceholderfName = licenceholderfName;}

    public String getLicenceHolderDistrictName() {
        return licenceholderdistrictName;
    }

    public void setLicenceHolderDistrictName(String licenceholderdistrictName) {this.licenceholderdistrictName = licenceholderdistrictName;}

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public String getlicencetype() {
        return licencetype;
    }

    public void setlicencetype(String licencetype) {
        this.licencetype = licencetype;
    }


    public String getDate_expiry() {
        return date_expiry;
    }

    public void setDate_expiry(String date_expiry) {
        this.date_expiry = date_expiry;
    }

    public String getDate_issue() {
        return date_issue;
    }

    public void setDate_issue(String date_issue) {
        this.date_issue = date_issue;
    }










}
