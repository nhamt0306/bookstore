package edu.hcmute.bookstore.dto;

public class EmailRecoveryDTO {
    private String email;

    public EmailRecoveryDTO() {
    }

    public EmailRecoveryDTO(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
