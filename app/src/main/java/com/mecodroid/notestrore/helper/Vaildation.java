package com.mecodroid.notestrore.helper;

import android.text.TextUtils;
import android.util.Patterns;

public class Vaildation {

    // This regex must accept Arabic letters,English letters, spaces and numbers
    public static final String USERNAME_PATERN = "^[\\u0621-\\u064Aa-zA-Z\\d\\-_\\s]{3,25}+$";

    public static boolean isValidName(String name) {

        return name.matches(USERNAME_PATERN);
    }

    public static boolean isValidPhone(String phone) {

        return phone.length() == 13 && TextUtils.isDigitsOnly(phone) && !TextUtils.isEmpty(phone);
    }

    public static boolean isValidPassword(String password) {

        return password.length() >= 5 && !TextUtils.isEmpty(password);
    }

    public static boolean isValidContent(String content) {

        return !TextUtils.isEmpty(content);
    }

    public static boolean isIdenticalPassword(String passwordConfirm, String fullConfirmPassword) {

        return passwordConfirm.equals(fullConfirmPassword) && !TextUtils.isEmpty(passwordConfirm)
                && !TextUtils.isEmpty(fullConfirmPassword);
    }

    public static boolean isValidEmail(String email) {

        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && !TextUtils.isEmpty(email);
    }


    public static boolean isEmptyField(String text) {

        return !TextUtils.isEmpty(text);
    }


}
