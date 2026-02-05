package com.example.mycontactlist;

import java.util.Calendar;

public class Contact {

    private int contactID;
    private String contactName;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;
    private String cellNumber;
    private String eMail;
    private Calendar birthday;

    // Constructor
    public Contact() {
        contactID = -1;  //new contact creation
        birthday = Calendar.getInstance();
    }

    // getter + setters for contact creation

    public int getContactID() {
        return contactID;
    }

    public void setContactID(int id) {
        contactID = id;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String name) {
        contactName = name;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String address) {
        streetAddress = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String c) {
        city = c;
    }

    public String getState() {
        return state;
    }

    public void setState(String s) {
        state = s;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zip) {
        zipCode = zip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phone) {
        phoneNumber = phone;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cell) {
        cellNumber = cell;
    }

    public String getEmail() {
        return eMail;
    }

    public void setEmail(String email) {
        eMail = email;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar bday) {
        birthday = bday;
    }
}
