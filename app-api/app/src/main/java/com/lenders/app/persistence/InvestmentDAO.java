package com.lenders.app.persistence;

import com.lenders.app.model.Investment;
import com.lenders.app.model.House;

import java.io.IOException;

/**
 * Defines the interface for the Investment object persistence
 *
 * @author Matthew Morrison
 */
public interface InvestmentDAO {

    /**
     * Create and save an {@linkplain Investment Investment} to the system
     * @param userId the user id associated with the investment
     * @param date the date of the investment
     * @param house the house associated with the investment
     * @param amount the amount of money invested
     * @return new Investment object if successful in creation
     * @throws IOException if there is an issue with storage
     */
    Investment createInvestment(int userId, String date, House house, float amount) throws IOException;

    // TODO find orders based on a substring of the house

    /**
     * Find all investments that were made by a specific user
     * @param userId the user id to search with
     * @return an array of investments that were made by the user, can be empty
     * @throws IOException if there is an issue with storage
     */
    Investment[] findUserInvestments(int userId) throws IOException;

    /**
     * Find all the investments made by a specific user within the past month
     * @param userId the user id to search with
     * @return an array of investments that were made by the user in the past
     * month, can be empty
     * @throws IOException if there is an issue with storage
     */
    Investment[] findLastMonthInvestments(int userId) throws IOException;

    /**
     * Retrieve a specific investments matching a desired id
     * @param id the id that the investment should match
     * @return the investment matching the id or null if it does not exist
     */
    Investment getOrder(int id);
}
