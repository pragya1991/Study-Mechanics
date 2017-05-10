package de.uni_saarland.studymechanics.utils;

/**
 * Created by asus on 5/10/17.
 */

public class StringUtils {
    public static boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    public static boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
}
