package com.lenders.app.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lenders.app.model.User;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

/**
 * Implements the classes and functionality for JSON based User persistence
 *
 * @author Matthew Morrison
 */
public class UserFileDAO implements UserDAO{

    private static final Logger LOG = Logger.getLogger(UserFileDAO.class.getName());

    Map<Integer, User> users; // local cache of all users

    private ObjectMapper objectMapper;// Connection between User object
                                      // and JSON text format written
                                      // to the file

    private static int nextId; // nextid to help assign to new users

    private String filename; // json filename to read and write from

    /**
     * Constructor to instantiate the UserFileDAO
     * @param filename the filename containing all user info
     * @param objectMapper the object mapper between User objects and JSON
     * @throws IOException if an error occurs when instantiating the file
     */
    public UserFileDAO(@Value("${users.file}") String filename, ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();
    }

    /**
     * Generate a new id for a new user when initially created
     * @return the next id a new user can use
     */
    private synchronized static int getNextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    /**
     * Load all info on the user file
     *
     * @throws IOException if an error occurs when reading the file
     */
    private void load() throws IOException {
        users = new TreeMap<>();
        nextId = 0;

        User[] userArray = objectMapper.readValue(new File(filename), User[].class);

        for (User user: userArray) {
            users.put(user.getId(), user);

            // TODO is this necessary? test out later and see if its needed
            // TODO or some variation on it
            if (user.getId() > nextId) {
                nextId = user.getId();
            }
        }

        nextId++;
    }

    /**
     * Saves the user map into the file as an array of JSON objects
     *
     * @return true if the users were written successfully
     *
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        User[] users = getUsers();
        objectMapper.writeValue(new File(filename), users);
        return true;
    }


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
    @Override
    public User createUser(String username, String password, String fn, String ln, String ssn) throws IOException {
        synchronized (users) {
            int id = getNextId();
            User newU = new User(id, username, password, fn, ln, ssn);
            users.put(id, newU);
            save();
            return newU;
        }
    }

    /**
     * Delete a {@linkplain User User} from the system with their id
     * @param id the id of the user to delete
     * @return true if the user was deleted, false otherwise
     * @throws IOException if there is an issue with underlying storage
     */
    @Override
    public boolean deleteUser(int id) throws IOException {
        synchronized (users) {
            if(users.containsKey(id)) {
                users.remove(id);
                return save();
            }

            return false;
        }
    }

    /**
     * Generate an array of all {@linkplain User Users} from the system
     * @return an array of all users, can be empty if no users exist
     */
    @Override
    public User[] getUsers() {
        ArrayList<User> userArrayList = new ArrayList<>(users.values());

        User[] userList = new User[userArrayList.size()];
        userArrayList.toArray(userList);
        return userList;
    }

    /**
     * Get a single {@linkplain User User} using their id
     * @param id the id of the user to find
     * @return the {@linkplain User User} of the respective id
     * @throws IOException if there is an issue with underlying storage
     */
    @Override
    public User getUser(int id) throws IOException {
       synchronized (users) {
           return users.getOrDefault(id, null);
       }
    }

    /**
     * Get a single {@linkplain User User} using their username
     * @param username the username of the user to find
     * @return the {@linkplain User User} of the respective username
     * @throws IOException if there is an issue with underlying storage
     */
    @Override
    public User getUser(String username) throws IOException {
        synchronized (users) {
            for(User u : users.values()) {
                String currUsername = u.getUsername();
                if (currUsername.equals(username)) {
                    return u;
                }
            }

            return null;
        }
    }

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
    @Override
    public User updateUsername(int id, String newUsername) throws IOException {
        synchronized (users) {
            if(!users.containsKey(id)) {
                return null;
            }

            User u = getUser(id);
            u.setUsername(newUsername);
            users.put(id, u);
            save();
            return u;
        }
    }

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
    @Override
    public User updatePassword(int id, String newPassword) throws IOException {
        synchronized (users) {
            if(!users.containsKey(id)) {
                return null;
            }

            User u = getUser(id);
            u.setPassword(newPassword);
            users.put(id, u);
            save();
            return u;
        }
    }

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
    @Override
    public User updateName(int id, String newFn, String newLn) throws IOException {
        synchronized (users) {
            if(!users.containsKey(id)) {
                return null;
            }

            User u = getUser(id);
            u.setFirstName(newFn);
            u.setLastName(newLn);
            users.put(id, u);
            save();
            return u;
        }
    }

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
    @Override
    public User updateSSN(int id, String newSsn) throws IOException {
        synchronized (users) {
            if(!users.containsKey(id)) {
                return null;
            }

            User u = getUser(id);
            u.setSsn(newSsn);
            users.put(id, u);
            save();
            return u;
        }
    }

    /**
     * Attempt to log in a user to the system with provided credentials
     * @param username the entered username
     * @param password the entered password
     * @return true if the credentials match a user, false otherwise
     */
    @Override
    public boolean authorizeUser(String username, String password) {
        // TODO better way to authorize than iterate through users?
        // TODO more efficient way for large number of users needed
        for (User u : users.values()) {
            if (u.getUsername().equals(username)) {
                return u.getPassword().equals(password);
            }
        }
        return false;
    }
}
