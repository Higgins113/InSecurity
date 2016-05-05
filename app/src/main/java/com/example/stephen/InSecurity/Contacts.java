package com.example.stephen.InSecurity;

/**
 * Created by Stephen on 24-04-2016.
 */
public class Contacts {

    private String power,temp,alarm;

    public Contacts(String power,String temp,String alarm)
    {
        this.power=power;
        this.temp=temp;
        this.alarm=alarm;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

   public String getTemp() {
       return temp;
  }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getAlarm() {
        return alarm;
    }

    public void setAlarm(String alarm) {
        this.alarm = alarm;
    }
}
