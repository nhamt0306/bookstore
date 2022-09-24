package edu.hcmute.bookstore.mapper;

public class SignInForm {
    private String user_username;
    private String user_password;

    public SignInForm() {
    }

    public SignInForm(String username, String password) {
        this.user_username = username;
        this.user_password = password;
    }

    public String getUsername() {
        return user_username;
    }

    public void setUsername(String username) {
        this.user_username = username;
    }

    public String getPassword() {
        return user_password;
    }

    public void setPassword(String password) {
        this.user_password = password;
    }
}
