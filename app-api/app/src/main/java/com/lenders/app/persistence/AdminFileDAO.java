package com.lenders.app.persistence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;
import java.io.File;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lenders.app.model.Admin;

/**
 * Implements the methods and functionality for JSON based Admin persistence
 * 
 * @author Matthew Morrison
 */
public class AdminFileDAO implements AdminDAO {


    private static final Logger LOG = Logger.getLogger(AdminFileDAO.class.getName());
    
    Map<Integer, Admin> admins;

    private ObjectMapper objectMapper;

    private static int nextId;

    private String filename;

    public AdminFileDAO(@Value("${admins.file}") String filename, ObjectMapper objectMapper) throws IOException{
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();
    }

    /**
     * Generate a new id for a new admin when initially created
     * @return the next id a new user can use
     */
    private synchronized static int getNextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    /**
     * Load all info on the admin file
     *
     * @throws IOException if an error occurs when reading the file
     */
    private void load() throws IOException {
        admins = new TreeMap<>();
        nextId = 0;

        Admin[] adminArray = objectMapper.readValue(new File(filename), Admin[].class);

        for (Admin admin: adminArray) {
            admins.put(admin.getId(), admin);

            if (admin.getId() > nextId) {
                nextId = admin.getId();
            }
        }

        ++nextId;

    }

    /**
     * Saves the user map into the file as an array of JSON objects
     *
     * @return true if the admins were written successfully
     *
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        Admin[] admins = getAllAdmins();
        objectMapper.writeValue(new File(filename), admins);
        return true;
    }

    /**
     * Create and save a new {@linkplain Admin Admin} to the system
     * @param password the password of the admin
     * @param email the email of the admin account
     * @param phone_number the phone numeber of the admin accont
     * The id of the Admin object is assigned uniquely when a new Admin is created
     * @return new {@linkplain Admin Admin} if successful
     * @throws IOException if there is an issue with storage
     */
    @Override
    public Admin createAdmin(String password, String email, String phone_number) throws IOException {

        synchronized (admins) {
            int id = getNextId();
            Admin newAdmin = new Admin(id, password, email, phone_number);
            admins.put(id, newAdmin);
            save();
            return newAdmin;
        }
    }

    /**
     * Delete an {@linkplain Admin Admin} from the system with their id
     * @param id the id of the admin to delete
     * @return true if admin was deleted, false otherwise
     * @throws IOException if there is an issue with storage
     */
    @Override
    public boolean deleteAdmin(int id) throws IOException {

        synchronized (admins) {
            if (admins.containsKey(id)) {
                admins.remove(id);
                return save();
            }
            return false;
        }
    }

        /**
     * Get all {@linkplain Admin Admins} from the ststem
     * @return an array of all admins, empty if none exist (should never happen though)
     */
    @Override
    public Admin[] getAllAdmins() {
        ArrayList<Admin> adminArrayList = new ArrayList<>(admins.values());

        Admin[] adminList = new Admin[adminArrayList.size()];
        adminArrayList.toArray(adminList);
        return adminList;

    }

    /**
     * Get a single {@linkplain Admin Admin} with their id
     * @param id the id of the admin to find
     * @return the {@linkplain Admin Admin} with the respective id
     * @throws IOException if there is an issue with storage
     */
    @Override
    public Admin getAdmin(int id) throws IOException {
        synchronized (admins) {
            return admins.getOrDefault(id, null);
        }
    }

    /**
     * Update the password for an {@linkplain Admin Admin}
     * @param id the id of the admin
     * @param oldPassword the old password to check
     * @param newPassword the new password to update with
     * @return updated {@linkplain Admin Admin} if successful, null otherwise
     * @throws IOException if there is an issue with storage
     */
    @Override
    public Admin updatePassword(int id, String oldPassword, String newPassword) throws IOException {
        synchronized (admins) {
            if(!admins.containsKey(id)) {
                return null;
            }

            Admin a = getAdmin(id);
            String oldPass = a.getPassword();

            // TODO find way to check if password is wrong vs admin does not exist
            if (!oldPass.equals(oldPassword)) {
                return null;
            }

            a.setPassword(newPassword);
            admins.put(id, a);
            save();
            return a;
        }
    }

    /**
     * Update the email for an {@linkplain Admin Admin}
     * @param id the id of the admin
     * @param newEmail the new email to update with
     * @return updated {@linkplain Admin Admin} if successful, null otherwise
     * @throws IOException if there is an issue with storage
     */
    @Override
    public Admin updateEmail(int id, String newEmail) throws IOException {
        synchronized (admins) {
            if(!admins.containsKey(id)) {
                return null;
            }

            Admin a = getAdmin(id);

            a.setEmail(newEmail);
            admins.put(id, a);
            save();
            return a;
        }
    }

    /**
     * Update the phone number for an {@linkplain Admin Admin}
     * @param id the id of the admin
     * @param newNumber the new phone number to update with
     * @return updated {@linkplain Admin Admin} if successful, null otherwise
     * @throws IOException if there is an issue with storage
     */
    @Override
    public Admin updatePhoneNum(int id, String newNumber) throws IOException {
        synchronized (admins) {
            if(!admins.containsKey(id)) {
                return null;
            }

            Admin a = getAdmin(id);

            a.setPhone_number(newNumber);
            admins.put(id, a);
            save();
            return a;
        }
    }

    @Override
    public Admin updateAdminInfo(int id, Admin updatedAdmin) throws IOException {
        synchronized (admins) {
            if(!admins.containsKey(id)) {
                return null;
            }

            admins.put(id, updatedAdmin);
            save();
            return updatedAdmin;
        }
    }
    
}
