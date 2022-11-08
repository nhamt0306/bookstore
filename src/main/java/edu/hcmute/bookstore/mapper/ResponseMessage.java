package edu.hcmute.bookstore.mapper;

public class ResponseMessage {
    private String message;
    private String success;

    public ResponseMessage() {

    }

    public ResponseMessage(String message, String success) {
        this.message = message;
        this.success = success;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
