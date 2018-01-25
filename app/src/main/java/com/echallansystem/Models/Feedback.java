package com.echallansystem.Models;



public class Feedback {
    private String firstname="";
    private String mobilenumber="";
    private String message="";
    private String feedbacktype="";

    public Feedback(){

    }

    public Feedback(
                               String firstname,
                               String mobilenumber,
                               String message,
                                String feedback) {
        this.firstname =firstname;
        this.mobilenumber = mobilenumber;
        this.message = message;
        this.feedbacktype = feedback;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMobileNumber() {
        return mobilenumber;
    }

    public void setMobileNumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getMessage() {return message;}

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFeedbackType() {return feedbacktype;}
    public void setFeedbackType(String feedbacktype) {
        this.feedbacktype = feedbacktype;
    }


}
