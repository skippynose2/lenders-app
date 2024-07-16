package com.lenders.app.model;

import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a Admin Entity, an extension of a User, within the lenders application
 *
 * @author Matthew Morrison
 */
public class Admin extends User {

    private static final Logger LOG = Logger.getLogger(Admin.class.getName());

    static final String STRING_FORMAT = """
        Admin: [id=%d, email=%s, phone_number=%s]
        """;

    public Admin(@JsonProperty("id") int id, 
                @JsonProperty("password") String password, 
                @JsonProperty("email") String email,
                @JsonProperty("phone_number") String phone_number) {
        super(id, password, null, null, null, email, phone_number, null);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT, super.getId(),
        super.getEmail(), super.getPhone_number());
    }
}
