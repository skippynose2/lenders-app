package com.lenders.app.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Tests for the User Model Class
 *
 * @author Matthew Morrison
 */
@Tag("Model-Tier")
public class UserTest {

    private User testUser;


    @BeforeEach
    public void userSetUp() {
        testUser = new User(1000, "username", "1234", "Hello", "World", "000-00-0000");
    }


    @Test
    public void testNewUser() {
        // Expected values
        int id = 1001;
        String username = "username";
        String password = "pass";
        String fn = "World";
        String ln = "Hello";
        String ssn = "123-45-6789";

        User u = new User(id, username, password, fn, ln, ssn);

        assertEquals(id, u.getId());
        assertEquals(username, u.getUsername());
        assertEquals(password, u.getPassword());
        assertEquals(fn, u.getFirstName());
        assertEquals(ln, u.getLastName());
        assertEquals(ssn, u.getSsn());
    }

    @Test
    public void testNewUsername() {
        String new_username = "myusername";

        testUser.setUsername(new_username);

        assertEquals(new_username, testUser.getUsername());
    }

    @Test
    public void testNewPassword() {
        String new_pass = "023124";

        testUser.setPassword(new_pass);

        assertEquals(new_pass, testUser.getPassword());
    }

    @Test
    public void testNewFirstName() {
        String new_fn = "John";

        testUser.setFirstName(new_fn);

        assertEquals(new_fn, testUser.getFirstName());
    }

    @Test
    public void testNewLastName() {
        String new_ln = "Doe";
        testUser.setLastName(new_ln);

        assertEquals(new_ln, testUser.getLastName());
    }

    @Test
    public void testNewSsn() {
        String new_ssn = "111-11-1111";
        testUser.setSsn(new_ssn);

        assertEquals(new_ssn, testUser.getSsn());
    }

    @Test
    public void testToString() {
        String expected_string = String.format(User.STRING_FORMAT,
                testUser.getId(), testUser.getFirstName(),
                testUser.getLastName(), testUser.getSsn());

        String actual_string = testUser.toString();

        assertEquals(expected_string, actual_string);
    }
}
