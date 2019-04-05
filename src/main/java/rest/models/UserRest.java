package main.java.rest.models;

import main.java.models.bean.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserRest {

    @Email
    @NotNull
    private String email;

    @NotNull
    private String username;

    @NotNull
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        User user = new User();

        user.setEmail(this.getEmail());
        user.setUsername(this.getUsername());
        user.setPassword(this.getPassword());

        return user;
    }
}
