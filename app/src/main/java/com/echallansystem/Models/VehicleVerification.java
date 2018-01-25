package com.echallansystem.Models;

import java.io.Serializable;


public class VehicleVerification implements Serializable {



    // Vehicle owner detail's

    private String vehcileownerName;
    private String vehcileownerfName;
    private String registerationYear;
    private String vehicleNumber;


    // Vehicle  detail's
    private String reg_num;
    private String chasis_num;
    private String engine_number;
    private String make_name;
    private String registeration_date;
    private String vehicle_price;
    private String yearofmanufacture;
    private String color;
    private String tokenpaidupto;




    public VehicleVerification() {

    }

    public VehicleVerification(String vehcileownerName,
                String  vehcileownerfName,
                String registerationYear,
                String vehicleNumber) {
        this.vehcileownerName =vehcileownerName;
        this.vehcileownerfName = vehcileownerfName;
        this.registerationYear = registerationYear;
        this.vehicleNumber = vehicleNumber;

        this.reg_num = reg_num;
        this.chasis_num = chasis_num;
        this.engine_number =engine_number;
        this.make_name =make_name;
        this.registeration_date = registeration_date;
        this.vehicle_price =  vehicle_price;
        this.yearofmanufacture = yearofmanufacture;
        this.color = color;
        this.tokenpaidupto =tokenpaidupto;

    }
    public String getVehcileOwnerName() {
        return vehcileownerName;
    }

    public void setVehcileOwnerName(String vehcileownerName) {this.vehcileownerName = vehcileownerName;}



    public String getVehcileOwnerFName() {
        return  vehcileownerfName;
    }

    public void setVehcileOwnerFName(String  vehcileownerfName) {this. vehcileownerfName = vehcileownerfName;}


    public String getRegisterationYear() {
        return registerationYear;
    }

    public void setRegisterationYear(String registerationYear) {this.registerationYear = registerationYear;}

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getReg_num() {
        return  reg_num;
    }

    public void setReg_num(String  reg_num) {this. reg_num = reg_num;}

    public String getChasis_num() {return  chasis_num;}

    public void setChasis_num(String  chasis_num) {this. chasis_num = chasis_num;}

    public String getEngine_number() {return  engine_number;}

    public void SetEngine_number(String  engine_number) {this. engine_number = engine_number;}

    public String getMake_name() {return  make_name;}

    public void SetMake_name(String  make_name) {this.make_name = make_name;}

    public String getRegisteration_date() {return  registeration_date;}

    public void SetRegisteration_date(String  registeration_date) {this.registeration_date = registeration_date;}

    public String getVehicle_price() {return  vehicle_price;}

    public void SetVehicle_price(String  vehicle_price) {this.vehicle_price = vehicle_price;}

    public String getYearofmanufacture() {return  yearofmanufacture;}

    public void SetYearofmanufacture(String  yearofmanufacture) {this.yearofmanufacture = yearofmanufacture;}

    public String getColor() {return  color;}

    public void SetColor(String  color) {this.color = color;}

    public String getTokenpaidupto() {return  tokenpaidupto;}

    public void SetTokenpaidupto(String tokenpaidupto) {this.tokenpaidupto = tokenpaidupto;}








}
