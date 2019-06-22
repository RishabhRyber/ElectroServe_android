package com.example.smartseller;

public class DeviceStatus {
    public String deviceID;
    String message;
    int Days,Hours,Minutes;
    Boolean State,USBstatus;

    public DeviceStatus() {
        this.deviceID = "";
        this.message = "";
        Days = 0;
        Hours = 0;
        Minutes = 0;
        State = false;
        this.USBstatus = false;

    }

    public DeviceStatus(String deviceID, String message, int days, int hours, int minutes, Boolean state, Boolean USBstatus) {
        this.deviceID = deviceID;
        this.message = message;
        Days = days;
        Hours = hours;
        Minutes = minutes;
        State = state;
        this.USBstatus = USBstatus;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getDays() {
        return Days;
    }

    public void setDays(int days) {
        Days = days;
    }

    public int getHours() {
        return Hours;
    }

    public void setHours(int hours) {
        Hours = hours;
    }

    public int getMinutes() {
        return Minutes;
    }

    public void setMinutes(int minutes) {
        Minutes = minutes;
    }

    public Boolean getState() {
        return State;
    }

    public void setState(Boolean state) {
        State = state;
    }

    public Boolean getUSBstatus() {
        return USBstatus;
    }

    public void setUSBstatus(Boolean USBstatus) {
        this.USBstatus = USBstatus;
    }
}
