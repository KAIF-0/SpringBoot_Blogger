package com.blogger.entity;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ExternalUserEntity {
    
    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;


    @Data
    @NoArgsConstructor
    public static class Address{
        private String street;
        private String suite;
        private String city;
        private String zipcode;
        private Geo geo;
    }

    @Data
    @NoArgsConstructor
    public static class Company{
        private String name;
        private String catchPhrase;
        private String bs;
    }


    @Data
    @NoArgsConstructor
    public static class Geo{
        private String lat;
        private String lng;
    }
}


