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
    User: [id=%d, first_name=%s, last_name=%s, ssn=%s]
    """;

    @JsonProperty("id") private int id;
    @JsonProperty("first_name") private String first_name;
    @JsonProperty("last_name") private String last_name;
    @JsonProperty("ssn") private String ssn;


    /**
     * Create a new User Entity with the required JSON properties
     * @param id the id of the user
     * @param first_name the first name of the user
     * @param last_name the last name of the user
     * @param ssn the SSN of the user
     */
    public User(@JsonProperty("id") int id,
                @JsonProperty("first_name") String first_name,
                @JsonProperty("last_name") String last_name,
                @JsonProperty("ssn") String ssn) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.ssn = ssn;
    }

    /**
     * Get the id of the user
     * @return the user's id
     */
    public int getId() {
        return id;
    }

    /**
     * Get the user's first name
     * @return the user's first name
     */
    public String getFirstName() {
        return first_name;
    }

    /**
     * Get the user's last name
     * @return the user's last name
     */
    public String getLastName() {
        return last_name;
    }

    /**
     * Get the user's SSN
     * @return the user's SSN
     */
    public String getSsn() {
        return ssn;
    }

    /**
     * Update the user's first name
     * @param first_name the new first name of the user
     */
    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    /**
     * Update the user's last name
     * @param last_name the new last name of the user
     */
    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    /**
     * Update the user's SSN
     * @param ssn the new SSN of the user
     */
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
}
