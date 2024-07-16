package com.lenders.app.persistence;

import java.io.IOException;

import com.lenders.app.model.Admin;

/**
 * Defines the interface and methods for an Admin object persistence
 * 
 * @author Matthew Morrison
 */
public interface AdminDAO {

    /**
     * Create and save a new {@linkplain Admin Admin} to the system
     * @param password the password of the admin
     * @param email the email of the admin account
     * @param phone_number the phone numeber of the admin accont
     * The id of the Admin object is assigned uniquely when a new Admin is created
     * @return new {@linkplain Admin Admin} if successful
     * @throws IOException if there is an issue with storage
     */
    Admin createAdmin(String password, String email, String phone_number) throws IOException;

    /**
     * Delete an {@linkplain Admin Admin} from the system with their id
     * @param id the id of the admin to delete
     * @return true if admin was deleted, false otherwise
     * @throws IOException if there is an issue with storage
     */
    boolean deleteAdmin(int id) throws IOException;

    /**
     * Get all {@linkplain Admin Admins} from the ststem
     * @return an array of all admins, empty if none exist (should never happen though)
     */
    Admin[] getAllAdmins();

    /**
     * Get a single {@linkplain Admin Admin} with their id
     * @param id the id of the admin to find
     * @return the {@linkplain Admin Admin} with the respective id
     * @throws IOException if there is an issue with storage
     */
    Admin getAdmin(int id) throws IOException;

    /**
     * Update the password for an {@linkplain Admin Admin}
     * @param id the id of the admin
     * @param oldPassword the old password to check
     * @param newPassword the new password to update with
     * @return updated {@linkplain Admin Admin} if successful, null otherwise
     * @throws IOException if there is an issue with storage
     */
    Admin updatePassword(int id, String oldPassword, String newPassword) throws IOException;

    /**
     * Update the email for an {@linkplain Admin Admin}
     * @param id the id of the admin
     * @param newEmail the new email to update with
     * @return updated {@linkplain Admin Admin} if successful, null otherwise
     * @throws IOException if there is an issue with storage
     */
    Admin updateEmail(int id, String newEmail) throws IOException;

    /**
     * Update the phone number for an {@linkplain Admin Admin}
     * @param id the id of the admin
     * @param newNumber the new phone number to update with
     * @return updated {@linkplain Admin Admin} if successful, null otherwise
     * @throws IOException if there is an issue with storage
     */
    Admin updatePhoneNum(int id, String newNumber) throws IOException;


}