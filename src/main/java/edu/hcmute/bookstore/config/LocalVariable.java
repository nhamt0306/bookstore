package edu.hcmute.bookstore.config;

import java.util.Random;

public class LocalVariable {
    public static final String disableStatus = "Disable";
    public static final String activeStatus = "Active";
    public static String GetOTP() {
        return String.format("%06d", new Random().nextInt(999999));
    }

    //Category message
    public static final String messageDeleteCatSuccess = "Delete category success!";
    public static final String messageCannotFindCat = "Cannot find category with id = ";
}
