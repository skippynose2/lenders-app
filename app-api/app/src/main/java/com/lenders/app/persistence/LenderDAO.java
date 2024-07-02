package com.lenders.app.persistence;

import java.io.IOException;

import com.lenders.app.model.Lender;

/**
 * Defines the interface and methods for a Lender object persistence
 * 
 * @author Matthew Morrison
 */
public interface LenderDAO {
    
    /**
     * Create and save a new {@linkplain Lender Lender} to the system
     * @param password password of the lender account
     * @param fn first name of the lender
     * @param ln last name of the lender
     * @param ssn ssn of the lender
     * @param email email of the lender
     * @param number phone number of the lender
     * @param business_name business name of the lender (can be null)
     * @param funds_available funds lender has available
     * @return new {@linkplain Lender Lender} if successful
     * @throws IOException if there is an issue with storage
     */
    Lender createLender(String password, String fn, String ln,
    String ssn, String email, String number, String business_name,
    float funds_available) throws IOException;

    /**
     * Delete a {@linkplain Lender Lender} from the system with their id
     * @param id the id of the lender to delete
     * @return true if lender was deleted, false otherwise
     * @throws IOException if there is an issue with storage
     */
    boolean deleteLender(int id) throws IOException;

    /**
     * Update the password for a {@linkplain Lender Lender}
     * @param id the id of the lender
     * @param oldPassword the old password to check
     * @param newPassword the new password to update with
     * @return updated {@linkplain Lender Lender} if successful, null otherwise
     * @throws IOException if there is an issue with storage
     */
    Lender updatePassword(int id, String oldPassword, String newPassword) throws IOException;

    /**
     * Update a {@linkplain Lender Lender} info, not including sensitive information
     * @param id the id of the lender
     * @param newBuyerInfo a new buyer object with updated information
     * @return updated {@linkplain Lender Lender} if successful, null otherwise
     * @throws IOException if there is an issue with storage
     */
    Lender updateLenderInfo(int id, Lender newLenderInfo) throws IOException;
}
