package edu.hcmute.bookstore.config;

import java.util.Random;

public class LocalVariable {
    public static final String disableStatus = "Disable";
    public static final String activeStatus = "Active";
    public static String GetOTP() {
        return String.format("%06d", new Random().nextInt(999999));
    }
    public static final int OrderPagingLimit = 9000;

    //Category message
    public static final String messageDeleteCatSuccess = "Delete category success!";
    public static final String messageCannotFindCat = "Cannot find category with id = ";

    // User
    public static final String messageCannotFindUser = "Cannot find user with id = ";
    //Status deliver message
    public static final String cancelMessage = "CANCELED"; // Đã hủy
    public static final String doneMessage = "DONE"; // Hoàn tất
    public static final String pendingMessage = "PENDING"; // Đợi thanh toán
    public static final String paidMessage = "PAID"; // Đã thanh toán
    public static final String deliveringMessage = "DELIVERING"; // Đang vận chuyển
}
