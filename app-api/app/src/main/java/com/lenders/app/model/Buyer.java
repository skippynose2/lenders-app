package com.lenders.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.logging.Logger;

/**
 * Represents a Buyer Entity, an extension of a User, within the lenders application
 *
 * @author Matthew Morrison
 */
public class Buyer extends User{
    
    private static final Logger LOG = Logger.getLogger(Buyer.class.getName());
    
    static final String STRING_FORMAT = """
        Buyer: [%s, num_units=%d, num_deals_complete=%d, num_flips_complete=%d]
        """;

    @JsonProperty("num_units") private int num_units; // number of units under management
    @JsonProperty("num_deals_complete") private int num_deals_complete;
    @JsonProperty("num_flips_complete") private int num_flips_complete;
    // TODO assets/liabilities in doc, prequalified check

    /**
     * Construct a new Buyer entity with the required JSON properties
     * @param id the id of the user
     * @param password the user's password
     * @param first_name first name of the user
     * @param last_name last name of the user
     * @param ssn ssn of the user
     * @param email email of the user
     * @param phone_number phone number of the user
     * @param business_name business name/EIN/TIN, if applicable 
     * @param num_units number of units under management
     * @param num_deals_complete number of deals completed
     * @param num_flips_complete number of flips completed
     */
    public Buyer(@JsonProperty("id") int id, 
                @JsonProperty("password") String password, 
                @JsonProperty("first_name") String first_name, 
                @JsonProperty("last_name") String last_name, 
                @JsonProperty("ssn") String ssn, 
                @JsonProperty("email") String email,
                @JsonProperty("phone_number") String phone_number, 
                @JsonProperty("business_name") String business_name,
                @JsonProperty("num_units") int num_units,
                @JsonProperty("num_deals_complete") int num_deals_complete,
                @JsonProperty("num_flips_complete") int num_flips_complete) {
        super(id, password, first_name, last_name, ssn, email, phone_number, business_name);
        this.num_units = num_units;
        this.num_deals_complete = num_deals_complete;
        this.num_flips_complete = num_flips_complete;
    }

    /**
     * Getters
     */

    public int getNum_units() {
        return num_units;
    }


    public int getNum_deals_complete() {
        return num_deals_complete;
    }


    public int getNum_flips_complete() {
        return num_flips_complete;
    }

    /**
     * Setters
     */

    public void setNum_units(int num_units) {
        this.num_units = num_units;
    }

    public void setNum_deals_complete(int num_deals_complete) {
        this.num_deals_complete = num_deals_complete;
    }

    public void setNum_flips_complete(int num_flips_complete) {
        this.num_flips_complete = num_flips_complete;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT, super.toString(),
        num_units, num_deals_complete,
        num_flips_complete);
    }
     
}
