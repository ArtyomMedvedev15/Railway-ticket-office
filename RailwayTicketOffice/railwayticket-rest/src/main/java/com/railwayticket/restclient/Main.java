package com.railwayticket.restclient;

import com.github.javafaker.Faker;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {



        Faker faker = new Faker(new Locale(System.getenv("faker_lang")));

        String name = faker.name().fullName();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        System.out.println("Name:" + name);
        System.out.println("Firstname:" + firstName);
        System.out.println("Lastname:" + lastName);

        System.out.println( System.getenv("faker_lang"));
    }
}
