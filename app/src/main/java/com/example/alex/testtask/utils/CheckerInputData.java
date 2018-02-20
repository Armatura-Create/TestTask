package com.example.alex.testtask.utils;

import java.util.regex.Pattern;

public final class CheckerInputData {

    private CheckerInputData(){}

    public static boolean isEmail(String email){
        return Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])").matcher(email).matches();
    }

    public static boolean isCheckPhone(String phone){
        return Pattern.compile("(\\+380\\d{9})").matcher(phone).matches();
    }

    public static boolean isPassword(String password){
        return Pattern.compile("(?=.*[0-9])(?=.*[A-Z])[0-9a-zA-Z]{8,20}").matcher(password).matches();
    }

    public static boolean isSite(String site){
        return Pattern.compile("(^((https?|ftp)://)?([a-z0-9]{1})((\\.[a-z0-9-])|([a-z0-9-]))*\\.([a-z]{2,6})(/?)$)").matcher(site).matches();
    }

}
