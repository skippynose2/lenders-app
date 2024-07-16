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
import com.lenders.app.model.Lender;

/**
 * Implements the methods and functionality for JSON based Lender persistence
 * 
 * @author Matthew Morrison
 */
public class LenderFileDAO implements LenderDAO {

     private static final Logger LOG = Logger.getLogger(LenderFileDAO.class.getName());

     Map<Integer, Lender> lenders;

     private ObjectMapper objectMapper;

     private static int nextId;

     private String filename;

     public LenderFileDAO(@Value("${lenders.file}") String filename, ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();
    }

    /**
     * Generate a new id for a new lender when initially created
     * @return the next id a new user can use
     */
    private synchronized static int getNextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    /**
     * Load all info on the lender file
     *
     * @throws IOException if an error occurs when reading the file
     */
    private void load() throws IOException {
        lenders = new TreeMap<>();
        nextId = 0;

        Lender[] lenderArray = objectMapper.readValue(new File(filename), Lender[].class);

        for (Lender l: lenderArray) {
            lenders.put(l.getId(), l);

            if (l.getId() > nextId) {
                nextId = l.getId();
            }
        }

        ++nextId;

    }

    /**
     * Saves the lender map into the file as an array of JSON objects
     *
     * @return true if the lenders were written successfully
     *
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        Lender[] lenders = getAllLenders();
        objectMapper.writeValue(new File(filename), lenders);
        return true;
    }

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
    @Override
    public Lender createLender(String password, String fn, String ln, String ssn, String email, String number,
            String business_name, float funds_available) throws IOException {
        synchronized (lenders) {
            int id = getNextId();
            Lender newL = new Lender(id, password, fn, ln, ssn, email, number, business_name, funds_available);
            lenders.put(id, newL);
            save();
            return newL;
        }
    }

    /**
     * Create and save a new {@linkplain Lender Lender} to the system
     * @param Lender object containing all information for the new Lender
     * @return new {@linkplain Lender Lender} if successful
     * @throws IOException if there is an issue with storage
     */
    @Override
    public Lender createLender(Lender lender) throws IOException {
        synchronized (lenders) {
            Lender newL = new Lender(lender.getId(), lender.getPassword(), lender.getFirst_name(), lender.getLast_name(), 
            lender.getSsn(), lender.getEmail(), lender.getPhone_number(), lender.getBusiness_name(), lender.getFunds_available());
            lenders.put(newL.getId(), newL);
            save();
            return newL;
        }
    }

    /**
     * Delete a {@linkplain Lender Lender} from the system with their id
     * @param id the id of the lender to delete
     * @return true if lender was deleted, false otherwise
     * @throws IOException if there is an issue with storage
     */
    @Override
    public boolean deleteLender(int id) throws IOException {
        synchronized (lenders) {
            if (lenders.containsKey(id)) {
                lenders.remove(id);
                return save();
            }
            return false;
        }
    }

    /**
     * Update the password for a {@linkplain Lender Lender}
     * @param id the id of the lender
     * @param oldPassword the old password to check
     * @param newPassword the new password to update with
     * @return updated {@linkplain Lender Lender} if successful, null otherwise
     * @throws IOException if there is an issue with storage
     */
    @Override
    public Lender updatePassword(int id, String oldPassword, String newPassword) throws IOException {
        synchronized (lenders) {
            if(!lenders.containsKey(id)) {
                return null;
            }

            Lender l = getLender(id);
            String oldPass = l.getPassword();

            // TODO find way to check if password is wrong vs admin does not exist
            if (!oldPass.equals(oldPassword)) {
                return null;
            }

            l.setPassword(newPassword);
            lenders.put(id, l);
            save();
            return l;
        }
    }

    /**
     * Update a {@linkplain Lender Lender} info, not including sensitive information
     * @param id the id of the lender
     * @param newBuyerInfo a new buyer object with updated information
     * @return updated {@linkplain Lender Lender} if successful, null otherwise
     * @throws IOException if there is an issue with storage
     */
    @Override
    public Lender updateLenderInfo(int id, Lender newLenderInfo) throws IOException {
        synchronized (lenders) {
            if (!lenders.containsKey(id)) {
                return null;
            }

            lenders.put(id, newLenderInfo);
            save();
            return newLenderInfo;
        }
    }

    /**
     * Get all {@linkplain Lender Lenders} from the system
     * @return an array of all lenders, empty if none exist
     */
    @Override
    public Lender[] getAllLenders() {
        ArrayList<Lender> lenderArrayList = new ArrayList<>(lenders.values());

        Lender[] lenderList = new Lender[lenderArrayList.size()];
        lenderArrayList.toArray(lenderList);
        return lenderList;
    }

    /**
     * Get a single {@linkplain Lender Lender} with their id
     * @param id the id of the lender to find
     * @return the {@linkplain Lender Lender} with the respective id
     * @throws IOException if there is an issue with storage
     */
    @Override
    public Lender getLender(int id) throws IOException {
        synchronized (lenders) {
            return lenders.getOrDefault(id, null);
        }
    }
    
}
