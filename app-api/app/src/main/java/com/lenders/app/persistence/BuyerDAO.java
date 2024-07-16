package com.lenders.app.persistence;

import java.io.IOException;

import com.lenders.app.model.Buyer;

/**
 * Defines the interface and methods for a Buyer object persistence
 * 
 * @author Matthew Morrison
 */
public interface BuyerDAO {
    
    /**
     * Create and save a new {@linkplain Buyer Buyer} to the system
     * @param password password of the buyer account
     * @param fn first name of the buyer
     * @param ln last name of the buyer
     * @param ssn ssn of the buyer
     * @param email email of the buyer
     * @param number phone number of the buyer
     * @param business_name business name of the buyer (can be null)
     * @param num_units number of units under management
     * @param num_deals_complete number of deals completed
     * @param num_flips_complete number of flips completed
     * @return new {@linkplain Buyer Buyer} if successful
     * @throws IOException if there is an issue with storage
     */
    Buyer createBuyer(String password, String fn, 
    String ln, String ssn, String email, String number, 
    String business_name, int num_units, int num_deals_complete,
    int num_flips_complete) throws IOException;

    /**
     * Create and save a new {@linkplain Buyer Buyer} to the system
     * @param Buyer object containing all information for the new Buyer
     * @return new {@linkplain Buyer Buyer} if successful
     * @throws IOException if there is an issue with storage
     */
    Buyer createBuyer(Buyer Buyer) throws IOException;

    /**
     * Get all {@linkplain Buyer Buyers} from the system
     * @return an array of all buyers, empty if none exist
     */
    Buyer[] getAllBuyers();

    /**
     * Get a single {@linkplain Buyer Buyer} with their id
     * @param id the id of the buyer to find
     * @return the {@linkplain Buyer buyer} with the respective id
     * @throws IOException if there is an issue with storage
     */
    Buyer getBuyer(int id) throws IOException;

    /**
     * Delete a {@linkplain Buyer Buyer} from the system with their id
     * @param id the id of the buyer to delete
     * @return true if buyer was deleted, false otherwise
     * @throws IOException if there is an issue with storage
     */
    boolean deleteBuyer(int id) throws IOException;

    /**
     * Update the password for a {@linkplain Buyer Buyer}
     * @param id the id of the buyer
     * @param oldPassword the old password to check
     * @param newPassword the new password to update with
     * @return updated {@linkplain Buyer Buyer} if successful, null otherwise
     * @throws IOException if there is an issue with storage
     */
    Buyer updatePassword(int id, String oldPassword, String newPassword) throws IOException;

    /**
     * Update a {@linkplain Buyer Buyer} info, not including sensitive information
     * @param id the id of the buyer
     * @param newBuyerInfo a new buyer object with updated information
     * @return updated {@linkplain Buyer Buyer} if successful, null otherwise
     * @throws IOException if there is an issue with storage
     */
    Buyer updateBuyerInfo(int id, Buyer newBuyerInfo) throws IOException;
}
