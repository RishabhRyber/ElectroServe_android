package com.example.smartseller;

import android.provider.ContactsContract;

public class SellerClass {
    String name, nameWorkCenter, address,areOfService;
    String panCard,GSTNumber;
    String aadharNumber;
    Boolean homeService, offerServiceAtCompanyWarehouse;
    String contactNumber;
    String email;

    public  SellerClass(){

    }

    public SellerClass(String name, String nameWorkCenter, String address, String areOfService, String panCard, String GSTNumber, String aadharNumber, Boolean homeService, Boolean offerServiceAtCompanyWarehouse, String contactNumber, String email) {
        this.name = name;
        this.nameWorkCenter = nameWorkCenter;
        this.address = address;
        this.areOfService = areOfService;
        this.panCard = panCard;
        this.GSTNumber = GSTNumber;
        this.aadharNumber = aadharNumber;
        this.homeService = homeService;
        this.offerServiceAtCompanyWarehouse = offerServiceAtCompanyWarehouse;
        this.contactNumber = contactNumber;
        this.email = email;
    }
}
