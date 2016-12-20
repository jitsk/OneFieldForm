package com.android.onefieldform;

/**
 * Constant class for defining constants
 *
 * @author jithin
 * @version 1.0
 */
class Constants {
// --Commented out by Inspection START (8/12/16 11:55 AM):
//    //Patterns for validation
    public static final String USERNAME_PATTERN = "^[a-zA-Z0-9_-]{3,100}$";
// --Commented out by Inspection STOP (8/12/16 11:55 AM)
    public static final String PASSWORD_PATTERN =
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,100})";
    public static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


}