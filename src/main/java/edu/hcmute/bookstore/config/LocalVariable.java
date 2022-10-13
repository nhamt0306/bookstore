package edu.hcmute.bookstore.config;

import java.util.Random;

public class LocalVariable {
    public static final String disableStatus = "Disable";
    public static String GetOTP() {
        return String.format("%06d", new Random().nextInt(999999));
    }
}
