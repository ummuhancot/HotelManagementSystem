package com.tpe.domain;

import javax.persistence.Embeddable;

//10
@Embeddable//gömüle bilir demek ayrı bi tablo istemioyrum ama guest clasta istiyorum
public class Address {


    private String street;

    private String city;

    private String country;

    private String zipcode;


    public Address() {
    }

    public Address(String street, String city, String country, String zipcode) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
