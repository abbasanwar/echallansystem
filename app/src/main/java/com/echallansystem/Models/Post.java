package com.echallansystem.Models;

import java.io.Serializable;


public class Post implements Serializable {
    private String distName,myName,mySurname,myLicense;
    public Post() {
    }

    public Post(String distName, String myName, String mySurname, String myLicense) {
       this.distName = distName;
        this.myLicense = myLicense;
        this.myName = myName;
        this.mySurname = mySurname;
    }
    public String getDistName() {
        return distName;
    }

    public void setDistName(String distName) {
        this.distName = distName;
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public String getMySurname() {
        return mySurname;
    }

    public void setMySurname(String mySurname) {
        this.mySurname = mySurname;
    }

    public String getMyLicense() {
        return myLicense;
    }

    public void setMyLicense(String myLicense) {
        this.myLicense = myLicense;
    }

}
