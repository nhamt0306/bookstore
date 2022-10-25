package edu.hcmute.bookstore.dto;

public class AuthorDTO {
    private String autName;
    private String autPhone;
    private String autEmail;
    private String autBiography;
    private String autAddress;

    public AuthorDTO(String autName, String autPhone, String autEmail, String autBiography, String autAddress) {
        this.autName = autName;
        this.autPhone = autPhone;
        this.autEmail = autEmail;
        this.autBiography = autBiography;
        this.autAddress = autAddress;
    }

    public String getAutName() {
        return autName;
    }

    public void setAutName(String autName) {
        this.autName = autName;
    }

    public String getAutPhone() {
        return autPhone;
    }

    public void setAutPhone(String autPhone) {
        this.autPhone = autPhone;
    }

    public String getAutEmail() {
        return autEmail;
    }

    public void setAutEmail(String autEmail) {
        this.autEmail = autEmail;
    }

    public String getAutBiography() {
        return autBiography;
    }

    public void setAutBiography(String autBiography) {
        this.autBiography = autBiography;
    }

    public String getAutAddress() {
        return autAddress;
    }

    public void setAutAddress(String autAddress) {
        this.autAddress = autAddress;
    }
}
