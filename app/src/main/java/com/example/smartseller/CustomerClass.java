package com.example.smartseller;

public class CustomerClass {
    String name,email,contactNumber,aadharNumber,address;
    Boolean usingIOTDevice;


    public CustomerClass(){

    }

    public CustomerClass(String name, String email, String contactNumber, String aadharNumber, String address, Boolean usingIOTDevice) {
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.aadharNumber = aadharNumber;
        this.address = address;
        this.usingIOTDevice = usingIOTDevice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getUsingIOTDevice() {
        return usingIOTDevice;
    }

    public void setUsingIOTDevice(Boolean usingIOTDevice) {
        this.usingIOTDevice = usingIOTDevice;
    }

    @Override
    public String toString() {
        return "CustomerClass{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", aadharNumber='" + aadharNumber + '\'' +
                ", address='" + address + '\'' +
                ", usingIOTDevice=" + usingIOTDevice +
                '}';
    }
}
