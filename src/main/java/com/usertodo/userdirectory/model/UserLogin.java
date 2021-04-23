package com.usertodo.userdirectory.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String username;
    private String password;

    //Relations

    //UserLogin to UserSignup relation
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "profile_id")
    private UserSignUp userSignUp;

    //UserLogin to UserDetails relation
    @OneToMany(mappedBy = "userLogin",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    private List<UserDetails> userDetails = new ArrayList<>();


    //Getters and Setters


    public UserSignUp getUserSignUp() {
        return userSignUp;
    }

    public void setUserSignUp(UserSignUp userSignUp) {
        this.userSignUp = userSignUp;
    }

    public List<UserDetails> getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(List<UserDetails> userDetails) {
        this.userDetails = userDetails;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
