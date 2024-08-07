package com.lenders.app.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lenders.app.model.Buyer;

/**
 * Implements the methods and functionality for JSON based Buyer persistence
 * 
 * @author Matthew Morrison
 */
public class BuyerFileDAO implements BuyerDAO {

    private static final Logger LOG = Logger.getLogger(BuyerFileDAO.class.getName());
    
    Map<Integer, Buyer> buyers;

    private ObjectMapper objectMapper;

    private static int nextId;

    private String filename;

    public BuyerFileDAO(@Value("${buyers.file}") String filename, ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();
    }

        /**
     * Generate a new id for a new buyer when initially created
     * @return the next id a new user can use
     */
    private synchronized static int getNextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    /**
     * Load all info on the buyer file
     *
     * @throws IOException if an error occurs when reading the file
     */
    private void load() throws IOException {
        buyers = new TreeMap<>();
        nextId = 0;

        Buyer[] buyerArray = objectMapper.readValue(new File(filename), Buyer[].class);

        for (Buyer buyer: buyerArray) {
            buyers.put(buyer.getId(), buyer);

            if (buyer.getId() > nextId) {
                nextId = buyer.getId();
            }
        }

        ++nextId;

    }

    /**
     * Saves the user map into the file as an array of JSON objects
     *
     * @return true if the buyers were written successfully
     *
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        Buyer[] buyers = getAllBuyers();
        objectMapper.writeValue(new File(filename), buyers);
        return true;
    }

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
    @Override
    public Buyer createBuyer(String password, String fn, String ln, String ssn, String email, String number,
            String business_name, int num_units, int num_deals_complete, int num_flips_complete) throws IOException {
        synchronized (buyers) {
            int id = getNextId();
            Buyer newB = new Buyer(id, password, fn, ln, ssn, email, number, business_name, num_units, num_deals_complete, num_flips_complete);
            buyers.put(id, newB);
            save();
            return newB;
        }
    }

    /**
     * Create and save a new {@linkplain Buyer Buyer} to the system
     * @param Buyer object containing all information for the new Buyer
     * @return new {@linkplain Buyer Buyer} if successful
     * @throws IOException if there is an issue with storage
     */
    @Override
    public Buyer createBuyer(Buyer buyer) throws IOException {
        synchronized (buyers) {
            Buyer b = new Buyer(buyer.getId(), buyer.getPassword(), buyer.getFirstName(), buyer.getLast_name(), buyer.getSsn(), 
            buyer.getEmail(), buyer.getPhone_number(), buyer.getBusiness_name(), buyer.getNum_units(), buyer.getNum_deals_complete(), buyer.getNum_flips_complete());
            buyers.put(b.getId(), b);
            save();
            return b;
        }
    }

    /**
     * Delete a {@linkplain Buyer Buyer} from the system with their id
     * @param id the id of the buyer to delete
     * @return true if buyer was deleted, false otherwise
     * @throws IOException if there is an issue with storage
     */
    @Override
    public boolean deleteBuyer(int id) throws IOException {
        synchronized (buyers) {
            if (buyers.containsKey(id)) {
                buyers.remove(id);
                return save();
            }
            return false;
        }
    }

    /**
     * Update the password for a {@linkplain Buyer Buyer}
     * @param id the id of the buyer
     * @param oldPassword the old password to check
     * @param newPassword the new password to update with
     * @return updated {@linkplain Buyer Buyer} if successful, null otherwise
     * @throws IOException if there is an issue with storage
     */
    @Override
    public Buyer updatePassword(int id, String oldPassword, String newPassword) throws IOException {
        synchronized (buyers) {
            if(!buyers.containsKey(id)) {
                return null;
            }

            Buyer b = getBuyer(id);
            String oldPass = b.getPassword();

            // TODO find way to check if password is wrong vs admin does not exist
            if (!oldPass.equals(oldPassword)) {
                return null;
            }

            b.setPassword(newPassword);
            buyers.put(id, b);
            save();
            return b;
        }
    }

    /**
     * Update a {@linkplain Buyer Buyer} info, not including sensitive information
     * @param id the id of the buyer
     * @param newBuyerInfo a new buyer object with updated information
     * @return updated {@linkplain Buyer Buyer} if successful, null otherwise
     * @throws IOException if there is an issue with storage
     */
    @Override
    public Buyer updateBuyerInfo(int id, Buyer newBuyerInfo) throws IOException {
        synchronized (buyers) {
            if (!buyers.containsKey(id)) {
                return null;
            }

            buyers.put(id, newBuyerInfo);
            save();
            return newBuyerInfo;
        }
    }

    /**
     * Get all {@linkplain Buyer Buyers} from the system
     * @return an array of all buyers, empty if none exist
     */
    @Override
    public Buyer[] getAllBuyers() {
        ArrayList<Buyer> buyerArrayList = new ArrayList<>(buyers.values());

        Buyer[] buyerList = new Buyer[buyerArrayList.size()];
        buyerArrayList.toArray(buyerList);
        return buyerList;
    }

    /**
     * Get a single {@linkplain Buyer Buyer} with their id
     * @param id the id of the buyer to find
     * @return the {@linkplain Buyer buyer} with the respective id
     * @throws IOException if there is an issue with storage
     */
    @Override
    public Buyer getBuyer(int id) throws IOException {
        synchronized (buyers) {
            return buyers.getOrDefault(id, null);
        }
    }
    
}
