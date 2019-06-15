package com.example.pizzaapp;

public class UserIdSession {

    private static String usId;

    public static String getIpAdress() {
        return ipAdress;
    }

    public static void setIpAdress(String ipAdress) {
        UserIdSession.ipAdress = ipAdress;
    }

    private static String ipAdress;

    public static String getUsId() {
        return usId;
    }

    public static void setUsId(String usId) {
        UserIdSession.usId = usId;
    }
}
