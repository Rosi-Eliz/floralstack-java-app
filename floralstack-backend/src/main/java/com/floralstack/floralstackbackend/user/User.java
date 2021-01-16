package com.floralstack.floralstackbackend.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class User {
    private final Integer id;
    @NotBlank
    private final String firstName;
    @NotBlank
    private final String lastName;
    private final Date birthDate;
    @NotBlank
    private final String userRole;
    @NotBlank
    private final String email;
    @NotBlank
    private final String password;


    public User(@JsonProperty("id") Integer id,
                @JsonProperty("first_name") String firstName,
                @JsonProperty("last_name") String lastName,
                @JsonProperty("birth_date") Date birthDate,
                @JsonProperty("user_role") String userRole,
                @JsonProperty("email") String email,
                @JsonProperty("password")String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.userRole = userRole;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getUserRole() {
        return userRole;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", userRole='" + userRole + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
