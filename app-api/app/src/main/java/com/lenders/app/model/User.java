package com.lenders.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.logging.Logger;

/**
 * Represents a User Entity within the lenders application
 *
 * @author Matthew Morrison
 */
public class User {
    private static final Logger LOG = Logger.getLogger(House.class.getName());

    static final String STRING_FORMAT = """
        User: [id=%d, first_name=%s, last_name=%s, ssn=%s, 
        email=%s, phone_number=%s, business_name=%s]
        """;

    @JsonProperty("id") private int id;
    @JsonProperty("password") private String password;
    @JsonProperty("first_name") private String first_name;
    @JsonProperty("last_name") private String last_name;
    @JsonProperty("ssn") private String ssn;
    @JsonProperty("email") private String email;
    @JsonProperty("phone_number") private String phone_number;
    @JsonProperty("business_name") private String business_name;

    
    /**
     * Create a new User Entity with the required JSON Properties
     * @param id the id of the user
     * @param password the user's password
     * @param first_name first name of the user
     * @param last_name last name of the user
     * @param ssn ssn of the user
     * @param email email of the user
     * @param phone_number phone number of the user
     * @param business_name business name/EIN/TIN, if applicable 
     */
    public User(int id, String password, String first_name, String last_name, String ssn, String email,
            String phone_number, String business_name) {
        this.id = id;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.ssn = ssn;
        this.email = email;
        this.phone_number = phone_number;
        this.business_name = business_name;
    }

    /**
     * Getters
     */
    
    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getSsn() {
        return ssn;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getBusiness_name() {
        return business_name;
    }

    /**
     * Setters
     */

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT, id, first_name, last_name, ssn,
        email, phone_number, business_name);
    }

}
