package com.lenders.app.persistence;

import com.lenders.app.model.User;

import java.io.IOException;

/**
 * Defines the interface and methods for the User object persistence
 *
 * @author Matthew Morrison
 */
public interface UserDAO {

    /**
     * Create and save a new {@linkplain User User} to the system
     * @param username the username of the user
     * @param password the password of the user
     * @param fn the first name of the user
     * @param ln the last name of the user
     * @param ssn the ssn of the user
     * The id of the User object is assigned uniquely when a new User is created
     * @return new {@linkplain User User} if successful
     * @throws IOException if there is an issue with storage
     */
    User createUser(String username, String password, String fn, String ln, String ssn) throws IOException;

    /**
     * Delete a {@linkplain User User} from the system with their id
     * @param id the id of the user to delete
     * @return true if the user was deleted, false otherwise
     * @throws IOException if there is an issue with underlying storage
     */
    boolean deleteUser(int id) throws IOException;

    /**
     * Generate an array of all {@linkplain User Users} from the system
     * @return an array of all users, can be empty if no users exist
     */
    User[] getUsers();

    /**
     * Get a single {@linkplain User User} using their id
     * @param id the id of the user to find
     * @return the {@linkplain User User} of the respective id
     * @throws IOException if there is an issue with underlying storage
     */
    User getUser(int id) throws IOException;

    /**
     * Get a single {@linkplain User User} using their username
     * @param username the username of the user to find
     * @return the {@linkplain User User} of the respective username
     * @throws IOException if there is an issue with underlying storage
     */
    User getUser(String username) throws IOException;

    /**
     * Updates and saves a new username for a {@linkplain User User}
     *
     * @param id the id of the user to update
     * @param newUsername the new username to update to
     *
     * @return updated {@linkplain User User} if successful, null if
     * {@linkplain User User} could not be found
     *
     * @throws IOException if underlying storage cannot be accessed
     */
    User updateUsername(int id, String newUsername) throws IOException;

    /**
     * Updates and saves a new password for a {@linkplain User User}
     *
     * @param id the id of the user to update
     * @param newPassword the new password to update to
     *
     * @return updated {@linkplain User User} if successful, null if
     * {@linkplain User User} could not be found
     *
     * @throws IOException if underlying storage cannot be accessed
     */
    User updatePassword(int id, String newPassword) throws IOException;

    /**
     * Updates and saves a new name for a {@linkplain User User}
     *
     * @param id the id of the user to update
     * @param newFn the new first name to update to
     * @param newLn the new last name to update to
     *
     * @return updated {@linkplain User User} if successful, null if
     * {@linkplain User User} could not be found
     *
     * @throws IOException if underlying storage cannot be accessed
     */
    User updateName(int id, String newFn, String newLn) throws IOException;

    /**
     * Updates and saves a new SSN for a {@linkplain User User}
     *
     * @param id the id of the user to update
     * @param newSsn the new SSN to update to
     *
     * @return updated {@linkplain User User} if successful, null if
     * {@linkplain User User} could not be found
     *
     * @throws IOException if underlying storage cannot be accessed
     */
    User updateSSN(int id, String newSsn) throws IOException;

    /**
     * Attempt to log in a user to the system with provided credentials
     * @param username the entered username
     * @param password the entered password
     * @return true if the credentials match a user, false otherwise
     */
    boolean authorizeUser(String username, String password);
}
