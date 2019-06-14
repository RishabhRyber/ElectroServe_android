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
