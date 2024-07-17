package com.lenders.app.persistence;

import java.io.IOException;
import java.util.ArrayList;

import com.lenders.app.model.Investment;

/**
 * Defines the interface and methods for the Investment object persistence
 * 
 * @author Matthew Morrison
 */
public interface InvestmentDAO {
    
    /**
     * Create and save a new {@linkplain Investment investment} to the system
     * @param user_id the user id associated with the investment
     * @param house_id the house id associated with the investment
     * @param date the date of the investment
     * @param money_invested the amount of money invested 
     * @return new {@linkplain Investment investment} object if successful
     * @throws IOException if there is an error with storage
     */
    Investment createInvestment(int user_id, int house_id, String date, float money_invested) throws IOException;

    /**
     * Create and save a new {@linkplain Investment investment} to the system using an investment object
     * @param investment the investment object to save
     * @return the investment object if successful
     * @throws IOException if there is an error with storage
     */
    Investment createInvestment(Investment investment) throws IOException;

    /**
     * Accept an investment and officially add it to the system
     * @param id the id of the investment
     * @return the investment object if successful
     * @throws IOException if there is an error with storage
     */
    Investment acceptInvestment(int id) throws IOException;

    /**
     * Remove an investment from storage
     * @param id the id of the investment
     * @return true if successful, false otherwise
     */
    boolean removeInvestment(int id) throws IOException;

    /**
     * Get every investment object
     * @return an array of all investments, can be empty if none
     */
    Investment[] getInvestments();

    /**
     * Get all the investments associated with a specific user
     * @param user_id the user id to search for
     * @return an array of all investments made by the user
     */
    ArrayList<Investment> getUserInvestments(int user_id);

    /**
     * Get all investments associated with a specific housing unit
     * @param house_id the house id to search for
     * @return an array of all investments with the house unit
     */
    ArrayList<Investment> getHouseInvestments(int house_id);

    /**
     * Get a specific investment with its id
     * @param id the investment id
     * @return the investment of the respective id
     * @throws IOException if there is an error with storage
     */
    Investment getInvestment(int id) throws IOException;

    /**
     * Update an already existing investment
     * @param investment the updated investment object
     * @return the updated investment object
     * @throws IOException if there is an error with storage
     */
    Investment updateInvestment(Investment investment) throws IOException;
}
