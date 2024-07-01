package com.lenders.app.model;

import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a Lender Entity, an extension of a User, within the lenders application
 *
 * @author Matthew Morrison
 */
public class Lender extends User{

    private static final Logger LOG = Logger.getLogger(Lender.class.getName());
    
    static final String STRING_FORMAT = """
        Lender: [%s, funds_available=%.2f]
        """;

    @JsonProperty("funds_available") private float funds_available;

    // TODO how long to pipe funds? intended investment term? Ask about this
    
    /**
     * Construct a new Lender entity with the required JSON properties
     * @param id the id of the user
     * @param password the user's password
     * @param first_name first name of the user
     * @param last_name last name of the user
     * @param ssn ssn of the user
     * @param email email of the user
     * @param phone_number phone number of the user
     * @param business_name business name/EIN/TIN, if applicable 
     * @param funds_available
     */
    public Lender(@JsonProperty("id") int id, 
                @JsonProperty("password") String password, 
                @JsonProperty("first_name") String first_name, 
                @JsonProperty("last_name") String last_name, 
                @JsonProperty("ssn") String ssn, 
                @JsonProperty("email") String email,
                @JsonProperty("phone_number") String phone_number, 
                @JsonProperty("business_name") String business_name,
                @JsonProperty("funds_available") float funds_available) {
        super(id, password, first_name, last_name, ssn, email, phone_number, business_name);
        this.funds_available = funds_available;
    }

    /**
     * Getters
     */

    public float getFunds_available() {
        return funds_available;
    }

    /**
     * Setters
     */

    public void setFunds_available(float funds_available) {
        this.funds_available = funds_available;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT, super.toString(),
        funds_available);
    }
    
}
